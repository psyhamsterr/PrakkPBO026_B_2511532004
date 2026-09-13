package mini_bank;

public class rekening {
    String nomerrekening;
    String namapemilik;
    double saldo;
    
    public rekening(String nomer, String nama, double saldo) {
        nomerrekening = nomer;
        namapemilik = nama;
        this.saldo = saldo; // FIX: Tambahkan kata kunci 'this'
        System.out.println("Rekening atas nama: " + namapemilik + " berhasil dibuat dengan saldo Rp" + this.saldo);
    }

    public void setortunai(double nominal) {
        if (nominal > 0) {
            saldo += nominal;
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        } else {
            System.out.println("Gagal, nominal setor harus lebih dari 0"); 
        }
    }

    public void tariktunai(double nominal) {
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal : Minimal nominal penarikan Rp10.000");
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
        } else {
            saldo -= nominal;
            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        }
    }
    
    public void cekinformasi() {
        System.out.println("\n-- INFORMASI REKENING --");
        System.out.println("No. Rekening : " + nomerrekening);
        System.out.println("Nama Pemilik : " + namapemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
    }
}