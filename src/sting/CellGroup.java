package sting;

public class CellGroup implements CellType{
	private final int unitLength;
	private Cell[] cells;
	
	public CellGroup(int unitLength,Cell[] cells){
		this.unitLength = unitLength;
		this.cells = cells;
		assert(cells.length == unitLength*unitLength);
	}
}
