package shop.app;

import java.util.List;
import shop.StudentInfo;
import shop.model.DigitalProduct;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.notification.CustomerContact;
import shop.notification.EmailChannel;
import shop.notification.NotificationChannel;
import shop.notification.NotificationService;
import shop.notification.SmsChannel;
import shop.notification.WhatsAppChannel;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;
import shop.payment.EWalletPayment;
import shop.payment.RefundablePayment;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.ShippingService;

public class TugasApp {
    public static void main(String[] args) {
        System.out.println("Tugas oleh " + StudentInfo.NAME + " (" + StudentInfo.NIM + ")");
        System.out.println();

        System.out.println("== T1: Total dengan biaya admin (Ekspres) ==");
        
        // Diubah dari List<Object> menjadi List<Product>
        List<Product> cart = List.of(
            new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000L, 300),
            new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000L, 400),
            new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000L, "https://toko-umkm.example/unduh/ebk-003")
        );

        ShippingService shipping = new ExpressShipping();
        CheckoutService checkout = new CheckoutService();

        EWalletPayment ewallet = new EWalletPayment("DompetKu", 1_000_000L);
        BankTransferPayment transfer = new BankTransferPayment("Bank Nagari");
        CreditCardPayment creditCard = new CreditCardPayment("Siti Rahma", 1_000_000L);

        long totalEwallet = checkout.grandTotal(cart, shipping, ewallet);
        long totalTransfer = checkout.grandTotal(cart, shipping, transfer);
        long totalCard = checkout.grandTotal(cart, shipping, creditCard);

        System.out.println("E-Wallet DompetKu : Rp" + totalEwallet + " (admin Rp" + ewallet.adminFee(totalEwallet) + ")");
        System.out.println("Transfer Bank Nagari : Rp" + totalTransfer + " (admin Rp" + transfer.adminFee(totalTransfer) + ")");
        System.out.println("Kartu Kredit (Siti Rahma) : Rp" + totalCard + " (admin Rp" + creditCard.adminFee(totalCard) + ")");

        System.out.println("Kebijakan kartu kredit : " + creditCard.policy());
        System.out.println("Kebijakan e-wallet : " + ((RefundablePayment) ewallet).policy());

        System.out.println("\n== T2: Notifikasi status pesanan ==");

        NotificationService service = new NotificationService();
        service.register(new EmailChannel());
        // Menambahkan parameter StudentInfo.SMS_MAX_LENGTH
        service.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        service.register(new WhatsAppChannel());
        
        service.register(new NotificationChannel() {
            @Override
            public String channelName() {
                return "LOG";
            }

            @Override
            public boolean send(CustomerContact contact, String message) {
                System.out.println("[LOG] " + contact.getName() + ": " + message);
                return true;
            }
        });

        String message = "Pesanan ORD-0001 sudah dikirim dengan kurir Ekspres. Nomor resi akan muncul di aplikasi dalam 1x24 jam. Terima kasih telah berbelanja produk UMKM Sumatera Barat di toko kami!";

        CustomerContact siti = new CustomerContact("Siti Rahma", "siti@example.com", "081234567890");
        int countSiti = service.broadcast(siti, message);
        System.out.println("Terkirim ke Siti lewat " + countSiti + " kanal");

        CustomerContact budi = new CustomerContact("Budi", "", "+6281298765432");
        int countBudi = service.broadcast(budi, message);
        System.out.println("Terkirim ke Budi lewat " + countBudi + " kanal");
    }
}