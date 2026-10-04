package mini_bank;

public class rekeninggiro extends rekening {
    private double batasoverdraft;

    public rekeninggiro(String nomer, String nama, double saldoawal, String pinawal, double batasoverdraft) {
        super(nomer, nama, saldoawal, pinawal);
        this.batasoverdraft = batasoverdraft;
    }

    public double getbatasoverdraft() {
        return batasoverdraft;
    }
}