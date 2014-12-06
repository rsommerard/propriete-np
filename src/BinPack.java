import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BinPack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length < 2) {
			error(0);
		}
		
		String fichier = args[0];
		String mode = args[1];
		
		if(!mode.startsWith("-")) {
			error(1);
		}
		
		BufferedReader lecteurAvecBuffer = null;
		String ligne;
		
	    try
	    {
	    	lecteurAvecBuffer = new BufferedReader(new FileReader(fichier));
	    }
	    catch(FileNotFoundException exc)
	    {
			error(2);
	    }
	    
	    int n = 0;
		int[] p = null;
		int c = 0;
		int nbs = 0;
	    
	    try {
			ligne = lecteurAvecBuffer.readLine();
			n = Integer.parseInt(ligne);
			
			p = new int[n];
			for(int i = 0; i < n; i++) {
				ligne = lecteurAvecBuffer.readLine();
				p[i] = Integer.parseInt(ligne);
			}
			
			ligne = lecteurAvecBuffer.readLine();
			nbs = Integer.parseInt(ligne);
			
			ligne = lecteurAvecBuffer.readLine();
			c = Integer.parseInt(ligne);
			
			lecteurAvecBuffer.close();
		} catch (IOException e) {
			error(3);
		}
		
	    
		PblBinPack pb = new PblBinPack(n, p, c, nbs);
		
		switch(mode) {
			case "-v":
				verifMode(pb);
				break;
			case "-nd":
				NonDeterministeMode(pb);
				break;
			case "-exh":
				exhaustifMode(pb);
				break;
			default:
				error(1);
				break;
		}
	}
	
	private static void exhaustifMode(PblBinPack pb) {
		if(pb.aUneSolution()) {
			System.out.println("True.\n");
		}
		else {
			System.out.println();
			System.out.println("False.\n");
		}
	}

	private static void NonDeterministeMode(PblBinPack pb) {
		CertificatBinPack certificat = new CertificatBinPack(pb);
		
		certificat.alea();
		
		if(certificat.correct()) {
			certificat.affiche();
			System.out.println("True.\n");
		}
		else {
			System.out.println();
			System.out.println("False.\n");
		}
	}

	private static void verifMode(PblBinPack pb) {
		CertificatBinPack certificat = new CertificatBinPack(pb);
		certificat.saisie();
		if(certificat.correct()) {
			certificat.affiche();
			System.out.println("Le certificat est correct.\n");
		}
		else {
			System.out.println();
			System.out.println("Le certificat n'est pas correct.\n");
		}
	}

	public static void error(int type) {
		if(type == 0) {
			System.out.println();
			System.out.println("java BinPack <files> <mode> avec comme modes (au moins) -v (verif), -nd (non déterministe), -exh (exhaustif)\n");
		}
		else if(type == 1) {
			System.out.println();
			System.out.println("arguments error\n");
		}
		else if(type == 2) {
			System.out.println();
			System.out.println("file not found\n");
		}
		else if(type == 3) {
			System.out.println();
			System.out.println("fatale error\n");
		}

		System.exit(1);
	}

}
