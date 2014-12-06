
public class PblSum extends PblDec {
	
	private int nb_entiers;
	private int entiers[];
	private int cible;
	
	public PblSum(int nb_entiers, int[] entiers, int cible) {
		this.nb_entiers = nb_entiers;
		this.entiers = entiers;
		this.cible = cible;
	}
	
	public PblPartition redPolyTo() {
		int nb_entiers2 = this.nb_entiers + 1;
		
		int entiers2[] = new int[nb_entiers2];
		
		int somme = 0;
		for(int i = 0; i < nb_entiers; i++) {
			somme += entiers[i];
		}
		
		if((2*this.cible) >= somme) {
			entiers2[nb_entiers2-1] = (2*this.cible) - somme;
		}
		else {
			entiers2[nb_entiers2-1] = somme - (2*this.cible);
		}
		
		for(int i = 0; i < nb_entiers2-1; i++){
			entiers2[i] = this.entiers[i];
		}
		
		return new PblPartition(nb_entiers2, entiers2);
	}
	
	public boolean aUneSolution() {
		return redPolyTo().aUneSolution();
	}

}