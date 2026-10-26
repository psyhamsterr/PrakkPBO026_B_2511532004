package mini_bank;

//pertemuan 2
public class Transaksi {
    private String idTransaksi;
    private String jenis;
    private double nominal;

    //pertemuan 2
    public Transaksi(String id, String jenis, double nominal) {
        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    //pertemuan 3
    public String getIdTransaksi() { return idTransaksi; }

    //pertemuan 3
    public String getJenis() { return jenis; }

    //pertemuan 3
    public double getNominal() { return nominal; }

    //pertemuan 2
    public void cetakDetail() {
        System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + nominal);
    }
}
