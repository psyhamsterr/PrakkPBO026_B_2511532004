package mini_bank;

public class rekening {
	String nomerrekening;
	String namapemilik;
	double saldo;
	
	public rekening(String nomer, String nama, double saldo) {
		nomerrekening = nomer;
		namapemilik = nama;
		saldo = saldo;
		System.out.println("atas nama: " + namapemilik + " berhasil dibuat dengan saldo rp."+ saldo);
	}
	public void setortunai (double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			System.out.println("setor tunai rp" + nominal + " sekarang saldo " + saldo);
			
		}else {System.out.println("gagal, nominal setor harus lebih dari 0"); 
	}
		}
	public void tariktunai (double nominal) {
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal : Minimal nominal penarikan 10.000");
		} else if (nominal > saldo) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
		} else {
			saldo -= nominal;
			System.out.println("tarik tunai rp" + nominal + " sekarang saldo " + saldo);
		}
	}
	
	public void cekinformasi() {
		System.out.println("--rekening--");
		System.out.println("no. rekening : "+ nomerrekening);
		System.out.println("nama pemilik: " + namapemilik);
		System.out.println("saldo akhir : rp."+ saldo);
	}
}
