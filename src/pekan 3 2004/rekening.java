package mini_bank;

import java.util.ArrayList;

public class rekening {
    private String nomerrekening;
    private String namapemilik;
    private double saldo;
    private String pin;
    public double totaltarik;
    public double totalsetor;
    public double totaltransaksi;
    public double tariktransaksi;
    public double setortransaksi;
    public double salahcounter;
    private int salah = 0;
    private boolean blokir = false;
    ArrayList<transaksi> riwayTransaksi = new ArrayList<>();

    public rekening(String nomer, String nama, double saldo, String pinawal) {
        this.nomerrekening = nomer;
        this.namapemilik = nama;
        this.saldo = saldo;

        if (pinawal != null && pinawal.length() == 6) {
            this.pin = pinawal;
        } else {
            System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default (123456).");
            this.pin = "123456";
        }

        this.riwayTransaksi = new ArrayList<>();
        System.out.println("Rekening atas nama: " + this.namapemilik + " berhasil dibuat dengan saldo Rp" + this.saldo);        
    }
    
    public String getnomerrekening() {return nomerrekening;}
    public String getnamapemilik() {return namapemilik;}

    public boolean otentikasi(String pinYangDiinput) {
        if (blokir) {
            System.out.println("Akun diblokir!");
            return false;
        }
        if (this.pin.equals(pinYangDiinput)) {
            salah = 0;
            return true;
        }
        salah++;
        if (salah == 3) {
            blokir = true;
            System.out.println("PIN salah 3 kali. Akun diblokir!");
        } else {
            System.out.println("PIN salah! Percobaan ke-" + salah);
        }
        return false;
    }   

    public void setortunai(double nominal) {
        if (nominal > 0) {
            saldo += nominal;
            totalsetor += nominal;
            setortransaksi += nominal;
            String idTrx = "TRX-S-"+ System.currentTimeMillis();
            transaksi trxBaru = new transaksi(idTrx, "Kredit", nominal);    
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
            riwayTransaksi.add(trxBaru);
        } else {
            System.out.println("Gagal, nominal setor harus lebih dari 0"); 
        }
        totaltransaksi +=1;
    }

    public void tariktunai(double nominal) {
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal : Minimal nominal penarikan Rp10.000");
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
        } else {
            saldo -= nominal;
            totaltarik += nominal;
            tariktransaksi += nominal;
            String idTrx = "TRX-T-" + (riwayTransaksi.size() + 1);
            transaksi trxBaru = new transaksi(idTrx, "Debit", nominal);
            riwayTransaksi.add(trxBaru);
            
            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        }
        totaltransaksi +=1;
    }

    public void cetakMutasi() {
        if (riwayTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi pada rekening ini");
        } else {
            for (transaksi t : riwayTransaksi) {
                t.cetakDetail();
            }
        }
    }

    public static void cekListRekening(ArrayList<rekening> listAkun) {
        if (listAkun == null || listAkun.isEmpty()) {
            System.out.println("Belum ada rekening yang terdaftar.");
            return;
        }

        int no = 1;

        System.out.println();
        System.out.println("---- Daftar Rekening Anda ----");
        for (rekening r : listAkun) {
            System.out.println(no + ". " + r.nomerrekening + " - " + r.namapemilik);
            no++;
        }
        System.out.println("------------------------------");
        System.out.println();
    }

    public void cekinformasi() {
        System.out.println("\n-- INFORMASI REKENING --");
        System.out.println("No. Rekening : " + nomerrekening);
        System.out.println("Nama Pemilik : " + namapemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("total setor  : Rp" + totalsetor);
        System.out.println("total tarik  : Rp" + totaltarik);
    }

    public void tampiltransaksi() {
        System.out.println("total transaksi 3 terbaru: ");
        System.out.println("setor terbaru : Rp." + setortransaksi);
        System.out.println("tarik terbaru : Rp." + tariktransaksi);
        setortransaksi = 0;
        tariktransaksi = 0;
    }
}