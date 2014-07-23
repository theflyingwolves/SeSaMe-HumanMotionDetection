package sting;

public class STINGTest {
	public static class Significance extends Combinable {
		private float significance;
		
		public Significance(float sig){
			this.significance = sig;
		}
		
		public float getSignificance(){
			return this.significance;
		}
		
		public Combinable combineWith(Combinable obj) {
			if(obj instanceof Significance){
				Significance s = (Significance)obj;
				Significance newS= new Significance(this.significance + s.getSignificance());
				return newS;
			}else{
				return null;
			}
		}
		
		public String toString(){
			return significance+"";
		}

		@Override
		public boolean shouldPropagate(Combinable[] objs) {
			// TODO Auto-generated method stub
			return true;
		}
	}
	
	public static void main(String[] args){
		Cell[][] cells = new Cell[18][18];
		int counter = 0;
		for(int i=0; i<cells.length;i++){
			for(int j=0; j<cells[0].length; j++){
				Significance sig = new Significance(counter);
				cells[i][j] = new Cell(sig);
				counter++;
			}
		}
		System.out.println("Counter: "+counter);
		Sting sting = new Sting(cells,4,true);
		sting.execute();
		sting.execute();
//		sting.execute();
//		sting.execute();
		Cell[][] activeCells = sting.getActiveCells();
		System.out.println("Row: "+activeCells.length +" Columns: "+activeCells[0].length+" Data:"+activeCells[0][0]);
	}
}