//pertemuan 1
package mini_bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;

        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("8. Transfer ke Rekening Lain");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine();

                    String pinBaru;
                    while (true) {
                        System.out.print("Masukkan PIN (6 digit angka): ");
                        pinBaru = input.nextLine();
                        if (pinBaru.matches("\\d{6}")) {
                            break;
                        }
                        System.out.println("Gagal: PIN harus berupa 6 digit angka! Silakan coba lagi.");
                    }

                    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis");
                    System.out.print("Pilihan: ");
                    int produk = input.nextInt();
                    input.nextLine();

                    if (produk == 1) {
                        System.out.print("Masukkan Suku Bunga (%): ");
                        double sukuBunga = input.nextDouble();
                        input.nextLine();
                        Rekening baru = new RekeningTabungan(no, nama, saldo, pinBaru, sukuBunga);
                        daftarRekening.add(baru);
                        akunAktif = baru;
                    } else if (produk == 2) {
                        System.out.print("Masukkan Batas Overdraft: ");
                        double batas = input.nextDouble();
                        input.nextLine();
                        Rekening baru = new RekeningGiro(no, nama, saldo, pinBaru, batas);
                        daftarRekening.add(baru);
                        akunAktif = baru;
                    } else {
                        System.out.println("Pilihan produk tidak valid! Pembukaan rekening dibatalkan.");
                    }
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        input.nextLine();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        System.out.print("Masukkan PIN: ");
                        String pinTarik = input.nextLine();
                        if (akunAktif.otentikasi(pinTarik)) {
                            System.out.print("Masukkan nominal tarik: ");
                            double tarik = input.nextDouble();
                            input.nextLine();
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5:
                    System.out.print("Masukkan No Rekening: ");
                    String noCari = input.nextLine();
                    Rekening ditemukan = null;
                    for (Rekening r : daftarRekening) {
                        if (r.getNomorRekening().equals(noCari)) {
                            ditemukan = r;
                            break;
                        }
                    }
                    if (ditemukan == null) {
                        System.out.println("Rekening tidak ditemukan!");
                    } else {
                        akunAktif = ditemukan;
                        System.out.println("Akun aktif sekarang: " + akunAktif.getNamaPemilik() + " (" + akunAktif.getNomorRekening() + ")");
                    }
                    break;

                case 6:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        System.out.print("Masukkan PIN: ");
                        String pinMutasi = input.nextLine();
                        if (akunAktif.otentikasi(pinMutasi)) {
                            akunAktif.cetakMutasi();
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;

                case 7:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else if (akunAktif instanceof RekeningTabungan) {
                        RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                        tabungan.tambahBungaAkhirBulan();
                    } else {
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                    }
                    break;

                case 8:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        System.out.print("Masukkan Nomor Rekening Tujuan: ");
                        String noTujuan = input.nextLine();
                        Rekening akunTujuan = null;
                        for (Rekening r : daftarRekening) {
                            if (r.getNomorRekening().equals(noTujuan)) {
                                akunTujuan = r;
                                break;
                            }
                        }
                        if (akunTujuan == null) {
                            System.out.println("Rekening tujuan tidak ditemukan!");
                        } else {
                            System.out.print("Masukkan nominal transfer: ");
                            double nominalTransfer = input.nextDouble();
                            input.nextLine();
                            System.out.print("Masukkan PIN: ");
                            String pinTransfer = input.nextLine();
                            akunAktif.transfer(akunTujuan, nominalTransfer, pinTransfer);
                        }
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}
