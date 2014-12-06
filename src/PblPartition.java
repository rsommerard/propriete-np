
public class PblPartition extends PblDec {
	
	private int nb_entiers;
	private int entiers[];
	
	public PblPartition(int nb_entiers, int[] entiers) {
		this.nb_entiers = nb_entiers;
		this.entiers = entiers;
	}

	public PblBinPack redPolyTo() {
		int capacity = 0;
			
		for(int i = 0; i < this.nb_entiers; i++) {
			capacity += this.entiers[i];
		}
		
		capacity /= 2;
		
		return new PblBinPack(this.nb_entiers, this.entiers, capacity, 2);
	}
	
	public boolean aUneSolution() {
		return redPolyTo().aUneSolution();
	}

}
