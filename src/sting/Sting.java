package sting;

import java.util.ArrayList;

public class Sting {
	private ArrayList<Layer> layers;
	private final int unitLength;
	private boolean shouldForceLayerWisePropagate;
	
	public Sting(Cell[][] dataCells, int unitLength, boolean shouldForceLayerWisePropagate){	
		this.unitLength = unitLength;
		this.shouldForceLayerWisePropagate = shouldForceLayerWisePropagate;
		initWithDataCells(dataCells);
	}
	
	private void initWithDataCells(Cell[][] dataCells){
		layers = new ArrayList<Layer>();
		Layer layerZero = new Layer(this.unitLength,dataCells);
		layers.add(layerZero);
	}
	
	public void execute(){
		if(this.shouldForceLayerWisePropagate){
			executeLayerWisePropagate();
		}else{
			executeCellLevelPropagate();
		}
	}
	
	private void executeLayerWisePropagate(){
		Layer topLayer = getTopLayer();
		Layer upperLayer = topLayer.layerWisePropagate();
		layers.add(upperLayer);
	}
	
	private Layer getTopLayer(){
		int index = layers.size()-1;
		return layers.get(index);
	}
	
	public Cell[][] getActiveCells(){
		if(this.shouldForceLayerWisePropagate){
			Layer topLayer = getTopLayer();
			return topLayer.getCells();
		}else{
			return null;
		}
	}
	
	private void executeCellLevelPropagate(){
		Layer topLayer = getTopLayer();
		Layer upperLayer = topLayer.cellLevelPropagate();
		layers.add(upperLayer);
	}
}