package sting;

public class Layer {
	private Cell[][] cells;
	private int unitLength;
	
	public Layer(int unitLength,Cell[][] dataCells){
		this.unitLength = unitLength;
		this.cells = dataCells;
	}
	
	public Cell[][][] groupAdjacentCells(){
		int numOfGroupsPerRow = (int) Math.ceil((double)cells.length / this.unitLength);
		int numOfGroupsPerCol = (int) Math.ceil((double)cells[0].length / this.unitLength);
		int numOfCellsPerGroup = this.unitLength * this.unitLength;
		Cell[][][] cellsInGroups = new Cell[numOfGroupsPerRow][numOfGroupsPerCol][numOfCellsPerGroup];
		for(int x = 0; x < cells.length; x+=this.unitLength){
			for(int y = 0; y < cells[0].length; y+=this.unitLength){
				for(int offsetX = 0; offsetX < this.unitLength; offsetX ++){
					for(int offsetY = 0; offsetY < this.unitLength; offsetY ++){
						if(x+offsetX < cells.length && y+offsetY < cells[0].length){
//							System.out.println("Row "+x/this.unitLength+" Col "+y/this.unitLength+" Index "+(offsetX*this.unitLength+offsetY)+": "+(x+offsetX)+" and "+(y+offsetY));
							cellsInGroups[x/this.unitLength][y/this.unitLength][offsetX*this.unitLength+offsetY] = 
								cells[x+offsetX][y+offsetY];
						}
					}
				}
			}
		}
		
		return cellsInGroups;
	}
	
	public Layer layerWisePropagate(){
		Cell[][][] cellsInGroups = groupAdjacentCells();
		Cell[][] updatedCells = new Cell[cellsInGroups.length][cellsInGroups[0].length];
		Cell combinedCell = null;
		for(int i=0; i< cellsInGroups.length;i++){
			for(int j=0; j<cellsInGroups[0].length;j++){
				for(int k=0;k<cellsInGroups[0][0].length;k++){
					Cell cellToProcess = cellsInGroups[i][j][k];
					if(cellToProcess != null){
						if(k==0){
							combinedCell = cellToProcess;
						}else{
							combinedCell = combinedCell.combine(cellToProcess);
						}
						cellToProcess.deactivateCell();
					}
				}
				updatedCells[i][j] = combinedCell;
			}
		}
		
		Layer updatedLayer = new Layer(this.unitLength,updatedCells);
		return updatedLayer;
	}

	public Layer cellLevelPropagate(){
		Cell[][][] cellsInGroups = groupAdjacentCells();
		return null;
	}
	
	public Cell[][] getCells(){
		return this.cells;
	}
	
//	public static void main(String[] args){
//		Cell[][] dataCells = new Cell[5][5];
//		for(int i=0;i<dataCells.length;i++){
//			for(int j=0;j<dataCells[0].length;j++){
//				dataCells[i][j] = new Cell(i,j);
//			}
//		}
//		
//		Layer layer = new Layer(3,dataCells);
//		layer.groupAdjacentCells();
//	}
}