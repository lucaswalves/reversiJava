package com.reversi;

import java.util.ArrayList;

public class Transition {
	public ArrayList<Coordinate> initial = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> end = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> direction = new ArrayList<Coordinate>();
	public ArrayList<Integer> numberOfIterations = new ArrayList<Integer>();
	public ArrayList<Integer> pointsAdd = new ArrayList<Integer>();
	public ArrayList<Integer> cellWeight = new ArrayList<Integer>();
	
	/*** Método para retornar a quantidade real de pontos obtidos numa transição ***/
	public int getRealPoints() {
		int totalPoints = 0;
		
		for (int i = 0; i < this.initial.size(); i++) {
			/*** Realizo um somatório dos pesos armazenados em todas as possíveis direções afetadas ***/
			totalPoints += this.pointsAdd.get(i).intValue();
		}
		/*** Multiplico o valor obtido da soma pelo peso da casa em questão ***/
		totalPoints *= this.cellWeight.get(0);
		
		return totalPoints;
	}
}
