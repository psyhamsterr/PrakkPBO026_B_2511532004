package mini_bank;
import java.util.Scanner;


public class main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		rekening akunaktif = null;
		boolean isrunning = true;
		
		System.out.println("==system perbankan mini==");
		
		while (isrunning) {
			System.out.println("menu utama");
			System.out.println("1.buka rekening baru");
			System.out.println("2.setor tunai");
			System.out.println("3.tarik tunai");
			System.out.println("4.cek informasi rekening");
			System.out.println("0.keluar");
			System.out.println("pilih menu");
			int pilihan = input.nextInt();
			input.nextLine();
			switch (pilihan) {
				case 1:
					System.out.println("masukkan no rekening: ");
					String no = input.nextLine();
					System.out.println("masukkan nama pemilik: ");
					String nama = input.nextLine();
					System.out.println("masukkan saldo awal: ");
					double saldo = input.nextDouble();
					akunaktif = new rekening(no, nama,saldo);
					break;
					
			
			
			case 2:
				if (akunaktif == null) {
					System.out.println("maaf anda belum punya nomer rekening");
				}
				else {
					System.out.println("masukkan nominal setor");
					double setor = input.nextDouble();
					akunaktif.setortunai(setor);
				}
				break;
			case 3:
				if (akunaktif == null) {
					System.out.println("maaf anda belum punya nomer rekening");
				}
				else {
					System.out.println("masukkan nominal tarik");
					double tarik = input.nextDouble();
					akunaktif.tariktunai(tarik);
				}
				break;

			case 4:
				if (akunaktif == null) {
					System.out.println("maaf anda belum punya nomer rekening");
				}
				else {
					akunaktif.cekinformasi();
				}
			case 0:
				isrunning = false;
				System.out.println("keluar");
				break;
			default:
					System.out.println("pilihan ga valid");
	}
}
		input.close();
}
}
