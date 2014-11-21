
public class PblBinPack extends PblDec {
	
	private int nb_objets;
	private int poids[];
	private int cap;
	private int nb_sacs;
	
	public PblBinPack(int n, int p[], int c, int nbs ){
		this.nb_objets = n;
		this.poids = p;
		this.cap = c;
		this.nb_sacs = nbs;
	}
	
	public int getNbSacs() {
		return this.nb_sacs;
	}
	
	public int getNbObjets() {
		return this.nb_objets;
	}
	
	public int getPoidsObjet(int obj) {
		return this.poids[obj];
	}
	
	public int getCapacite() {
		return this.cap;
	}
	
}
