package com.reversi;

import java.util.ArrayList;

public class Transition {
	public ArrayList<Coordinate> initial = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> end = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> direction = new ArrayList<Coordinate>();
	public ArrayList<Integer> numberOfIterations = new ArrayList<Integer>();
	public ArrayList<Integer> pointsAdd = new ArrayList<Integer>();
	public ArrayList<Integer> cellWeight = new ArrayList<Integer>();
	
	/*** M�todo para retornar a quantidade real de pontos obtidos numa transi��o ***/
	public int getRealPoints() {
		int totalPoints = 0;
		
		for (int i = 0; i < this.initial.size(); i++) {
			/*** Realizo um somat�rio dos pesos armazenados em todas as poss�veis dire��es afetadas ***/
			totalPoints += this.pointsAdd.get(i).intValue();
		}
		/*** Multiplico o valor obtido da soma pelo peso da casa em quest�o ***/
		totalPoints *= this.cellWeight.get(0);
		
		return totalPoints;
	}
}
