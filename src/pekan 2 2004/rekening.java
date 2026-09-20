package mini_bank;

import java.util.ArrayList;

public class rekening {
    String nomerrekening;
    String namapemilik;
    double saldo;
    double totaltarik;
    double totalsetor;
    double totaltransaksi;
    double tariktransaksi;
    double setortransaksi;
    
    ArrayList<transaksi> riwayTransaksi = new ArrayList<>();

    public rekening(String nomer, String nama, double saldo) {
        nomerrekening = nomer;
        namapemilik = nama;
        this.saldo = saldo; 
        System.out.println("Rekening atas nama: " + namapemilik + " berhasil dibuat dengan saldo Rp" + this.saldo);
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
