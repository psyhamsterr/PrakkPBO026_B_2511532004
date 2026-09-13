package mini_bank;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        rekening akunaktif = null;
        boolean isrunning = true;
        
        System.out.println("== SYSTEM PERBANKAN MINI ==");
        
        while (isrunning) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Buka rekening baru");
            System.out.println("2. Setor tunai");
            System.out.println("3. Tarik tunai");
            System.out.println("4. Cek informasi rekening");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan sisa newline
            
            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan no rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan nama pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan saldo awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine(); // FIX: Bersihkan buffer setelah nextDouble()
                    
                    akunaktif = new rekening(no, nama, saldo);
                    break;
                    
                case 2:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        input.nextLine(); // FIX: Bersihkan buffer
                        akunaktif.setortunai(setor);
                    }
                    break;

                case 3:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        System.out.print("Masukkan nominal tarik: ");
                        double tarik = input.nextDouble();
                        input.nextLine(); // FIX: Bersihkan buffer
                        akunaktif.tariktunai(tarik);
                    }
                    break;

                case 4:
                    if (akunaktif == null) {
                        System.out.println("Maaf, Anda belum punya nomor rekening.");
                    } else {
                        akunaktif.cekinformasi();
                    }
                    break; // FIX: Tambahkan break agar tidak lanjut ke case 0

                case 0:
                    isrunning = false;
                    System.out.println("Terima kasih, program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
        input.close();
    }
}