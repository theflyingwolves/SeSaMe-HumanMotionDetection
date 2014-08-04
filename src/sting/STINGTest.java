package sting;

public class StingTest {
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
			boolean flag = true;
			int sum = 0;
			Significance sig;
			
			for(Combinable c : objs){
				if(!(c instanceof Significance)){
					flag = false;
					break;
				}else{				
					sig = (Significance)c;
					sum += sig.getSignificance();
				}
			}
			
			if(flag){
				return sum < 50;
			}else{
				return false;
			}
		}
	}
	
	public static void main(String[] args){
		Cell[][] cells = new Cell[4][4];
		int counter = 0;
		for(int i=0; i<cells.length;i++){
			for(int j=0; j<cells[0].length; j++){
				Significance sig = new Significance(counter);
				cells[i][j] = new Cell(sig);
				counter++;
			}
		}
		System.out.println("Counter: "+counter);
		
		Sting sting = new Sting(cells,2);
		
		printStingInfo(sting);
		
		sting.execute();
		printStingInfo(sting);
		
		sting.execute();
		printStingInfo(sting);
		
		sting.execute();
		printStingInfo(sting);
		
		sting.execute();
		printStingInfo(sting);
	}
	
	private static void printStingInfo(Sting sting){
		Cell[] activeCells = sting.getActiveCells();
		System.out.println("Number of Cells Left: "+activeCells.length);
		for(Cell c : activeCells){
			System.out.println("Cell Content: "+c);
		}
	}
}
