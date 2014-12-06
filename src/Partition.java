import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Partition {

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
	    
	    int nb_entiers = 0;
		int[] entiers = null;
	    
	    try {
			ligne = lecteurAvecBuffer.readLine();
			nb_entiers = Integer.parseInt(ligne);
			
			entiers = new int[nb_entiers];
			for(int i = 0; i < nb_entiers; i++) {
				ligne = lecteurAvecBuffer.readLine();
				entiers[i] = Integer.parseInt(ligne);
			}
			
			lecteurAvecBuffer.close();
		} catch (IOException e) {
			error(3);
		}
		
	    
		PblPartition pp = new PblPartition(nb_entiers, entiers);
		
		switch(mode) {
			case "-v":
				verifMode(pp.redPolyTo());
				break;
			case "-nd":
				NonDeterministeMode(pp.redPolyTo());
				break;
			case "-exh":
				exhaustifMode(pp.redPolyTo());
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
			System.out.println("java Partition <files> <mode> avec comme modes (au moins) -v (verif), -nd (non déterministe), -exh (exhaustif)\n");
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
