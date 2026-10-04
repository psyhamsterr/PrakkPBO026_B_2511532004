package mini_bank;

public class rekeningtabungan extends rekening {

    private  double sukubunga;
    public  rekeningtabungan(String nomer, String nama, double saldoawal, String pinawal, double sukubunga){
        super(nomer, nama, saldoawal, pinawal);
        this.sukubunga = sukubunga;
    }
    public  void  tambahbungaakhirbulan(){
        double nominalbunga = saldo * (sukubunga/100);
        saldo += nominalbunga;

        String idTRX = "TRX-B-" + System.currentTimeMillis();
        riwayTransaksi.add(new transaksi(idTRX, "bunga", nominalbunga));
        System.out.println("Bunga " + sukubunga + "% berhasil di tambahkan: Rp" + nominalbunga);
    }
}
