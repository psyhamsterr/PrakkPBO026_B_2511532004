package mini_bank;
public class transaksi {
    private String idTransaksi;
    private String jenis;
    private double nominal;

    public transaksi(String id, String jenis, double nominal) {
        this. idTransaksi = id;
        this. jenis = jenis;
        this.nominal = nominal;
    }
    public String getidTransaksi(){return  idTransaksi;}
    public String getjenis(){return  jenis;}
    public double getnominal(){return  nominal;}
    public void cetakDetail() {
            System.out.println("ID: "+ idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" +nominal);
        }
    }
