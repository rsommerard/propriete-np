import java.util.Scanner;


public class CertificatBinPack implements Certificat {

	private PblBinPack pb;
	private int[][] certificat;
	private int iterator;
	
	public CertificatBinPack(PblBinPack pb) {
		this.pb = pb;
		this.certificat = new int[pb.getNbSacs()][pb.getNbObjets()];
		this.iterator = 0;
	}

	@Override
	public boolean correct() {
		for(int i = 0; i < this.pb.getNbSacs(); i++) {
			int somme = 0;
			
			for(int j = 0; j < this.pb.getNbObjets(); j++) {
				if(this.certificat[i][j] == 1) {
					somme += this.pb.getPoidsObjet(j);
				}
			}
			
			if(somme > this.pb.getCapacite()) {
				return false;
			}
		}
		
		for(int i = 0; i < this.pb.getNbObjets(); i++) {
			boolean present = false;
			
			for(int j = 0; j < this.pb.getNbSacs(); j++) {
				if(this.certificat[j][i] == 1) {
					present = true;
				}
			}
			
			if(!present) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void suivant() {
		int reste = this.iterator;
		
		for(int i = 0; i < this.pb.getNbSacs(); i++) {
			for(int j = 0; j < this.pb.getNbObjets(); j++) {
				if(reste == 0) {
					this.certificat[i][j] = 0;
				}
				else {
					this.certificat[i][j] = reste % 2;
					reste = reste / 2;
				}
			}
		}
		
		this.iterator++;
	}

	@Override
	public boolean estDernier() {
		for(int i = 0; i < this.pb.getNbSacs(); i++) {
			for(int j = 0; j < this.pb.getNbObjets(); j++) {
				if(this.certificat[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public void alea() {
		for(int i = 0; i < this.pb.getNbSacs(); i++) {
			for(int j = 0; j < this.pb.getNbObjets(); j++) {
				this.certificat[i][j] = (int)(Math.round(Math.random()));
			}
		}
	}

	@Override
	public void affiche() {
		System.out.println();
		System.out.println("Certificat:");
		System.out.println();
		
		System.out.print("\t");
		for(int i = 0; i < this.pb.getNbObjets(); i++) {
			System.out.print("Objet" + i + "\t");
		}
		System.out.println();
		
		for(int i = 0; i < this.pb.getNbSacs(); i++) {
			System.out.print("Sac" + i + "\t");
			for(int j = 0; j < this.pb.getNbObjets(); j++) {
				System.out.print("  " + this.certificat[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void reset() {
		for(int i = 0; i < this.pb.getNbSacs(); i++) {
			for(int j = 0; j < this.pb.getNbObjets(); j++) {
				this.certificat[i][j] = 0;
			}
		}
	}

	@Override
	public void saisie() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("INFO | L'indice des objets va de 0 à " + (this.pb.getNbObjets()-1));
		System.out.println("INFO | L'indice des sacs va de 0 à " + (this.pb.getNbSacs()-1));
		
		this.reset();
		this.affiche();
		
		System.out.print("Continuer la saisie? (o/N): ");
		String rep = sc.nextLine();
		System.out.println();
		while(rep.equalsIgnoreCase("o")) {
			System.out.print("Sac: ");
			int sac = sc.nextInt();
			System.out.print("Objet: ");
			int objet = sc.nextInt();
			System.out.println();
			if(sac < this.pb.getNbSacs() && objet < this.pb.getNbObjets()) {
				this.certificat[sac][objet] = 1;
				this.affiche();
			}
			else {
				System.out.println("Erreur de saisie");
				this.affiche();
			}
			
			System.out.print("Continuer la saisie? (o/N): ");
			sc.nextLine();
			rep = sc.nextLine();
			System.out.println();
		}
	}
	
}
