package mini_bank;
public class transaksi {
    String idTransaksi;
    String jenis;
    double nominal;

    public transaksi(String id, String jenis, double nominal) {
        this. idTransaksi = id;
        this. jenis = jenis;
        this.nominal = nominal;
    }
    public void cetakDetail() {
            System.out.println("ID: "+ idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" +nominal);
        }
    }
