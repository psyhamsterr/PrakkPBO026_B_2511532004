package mini_bank;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        rekening akunaktif = null;
        ArrayList<rekening> daftarRekening = new ArrayList<>();
        boolean isrunning = true;
        int totaltransaksi = 0; 
        
        System.out.println("== SYSTEM PERBANKAN MINI ==");
        
        while (isrunning) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Buka rekening baru");
            System.out.println("2. Setor tunai");
            System.out.println("3. Tarik tunai");
            System.out.println("4. Cek informasi rekening");
            System.out.println("5. Cek daftar rekening");
            System.out.println("6. Cek mutasi");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = input.nextInt();
            input.nextLine(); 
            
            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan no rekening: ");
                    String no = input.nextLine();

                    System.out.print("Masukkan nama pemilik: ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan saldo awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine(); 

                    String pinawal;
                    while (true) {
                        System.out.print("Masukkan PIN (6 digit): ");
                        pinawal = input.nextLine();
                        if (pinawal.length() == 6) {
                            break;
                        }
                        System.out.println("Gagal: PIN harus berupa 6 digit angka! Silakan coba lagi.");
                    }
                    System.out.println("Pilih tujuan anda:\n1. Tabungan Umum\n2. Giro Bisnis");
                    System.out.print("Pilihan: ");
                    String pilihanTipe = input.nextLine();

                    if (pilihanTipe.equals("1")) {
                        System.out.print("Berapa persen (%) suku bunga yang Anda tetapkan? ");
                        double sukubungauser = input.nextDouble();
                        input.nextLine();
                        akunaktif = new rekeningtabungan(no, nama, saldo, pinawal, sukubungauser);
                    } else if (pilihanTipe.equals("2")) {
                        System.out.print("Berapa limit pinjaman yang Anda tetapkan? ");
                        double limitpinjam = input.nextDouble();
                        input.nextLine();
                        akunaktif = new rekeninggiro(no, nama, saldo, pinawal, limitpinjam);
                    } else {
                        System.out.println("Pilihan tidak valid, dibuat sebagai Rekening Reguler.");
                        akunaktif = new rekening(no, nama, saldo, pinawal);
                    }

                    daftarRekening.add(akunaktif);
                    System.out.println("Rekening berhasil dibuat!");
                    break;
                    
                case 2:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        input.nextLine(); 
                        akunaktif.setortunai(setor);
                        totaltransaksi++;
                    }
                    break;

                case 3:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = input.nextLine();

                        if (akunaktif.otentikasi(pinInput)) {
                            System.out.print("Masukkan nominal tarik: ");
                            double tarik = input.nextDouble();
                            input.nextLine();
                            akunaktif.tariktunai(tarik);
                            totaltransaksi++;
                        }
                    }
                    break;

                case 4:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        akunaktif.cekinformasi();
                    }
                    break; 

                case 5:
                    rekening.cekListRekening(daftarRekening);
                    break;

                case 6:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = input.nextLine();

                        if (akunaktif.otentikasi(pinInput)) {
                            akunaktif.cetakMutasi();
                        }
                    }
                    break;
               case 7:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else if (akunaktif instanceof rekeningtabungan) {
                        rekeningtabungan tabungan = (rekeningtabungan) akunaktif;
                        tabungan.tambahbungaakhirbulan();
                    } else {
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                    }
                    break;

                case 0:
                    isrunning = false;
                    System.out.println("Terima kasih, program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }

            if (akunaktif != null && totaltransaksi >= 3) {
                akunaktif.tampiltransaksi();
                totaltransaksi = 0;
            }
        }
        input.close();
    }
}