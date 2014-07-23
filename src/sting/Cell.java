package sting;

public class Cell implements CellType{
	private int level;
	private boolean isActive;
	private Combinable property;
	
	public Cell(Combinable property){
		this.property = property;
		this.isActive = true;
	}
	
	public boolean isCellAtSameLevel(Cell cell){
		return level == cell.getLevelNumber();
	}
	
	public int getLevelNumber(){
		return level;
	}
	
	public void deactivateCell(){
		this.isActive = false;
	}
	
	public boolean isCellActive(){
		return isActive;
	}
	
	public Combinable getCombinableProperty(){
		return this.property;
	}
	
	public Cell combine(Cell[] cells){
		Combinable[] combinableProps = new Combinable[cells.length];
		for(int i=0; i<cells.length; i++){
			combinableProps[i] = cells[i].getCombinableProperty();
		}
		
		Combinable combinedProperty = this.property.combineWith(combinableProps);
		return new Cell(combinedProperty);
	}
	
	public Cell combine(Cell cell){
		Combinable combinedProperty = this.property.combineWith(cell.getCombinableProperty());
		return new Cell(combinedProperty);
	}
	
	public String toString(){
		return this.property.toString();
	}
}