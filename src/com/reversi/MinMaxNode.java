package com.reversi;

import java.util.ArrayList;

public class MinMaxNode {
	/***************************** ATRIBUTOS *****************************/
	/*** Momento do tabuleiro em que o nó se encontra ***/
	private Board board = new Board();
	/*** Melhores jogadas (com filtro alpha beta caso seja IA) para o tabuleiro atual ***/
	private ArrayList<Transition> bestPlays = new ArrayList<Transition>();
	/*** Lista de filhos (jogadas derivadas das bestPlays) ***/
	private ArrayList<MinMaxNode> sons = new ArrayList<MinMaxNode>();
	/*** Indicador de MIN ou MAX ***/
	private boolean min;
	/*** Indicador de MIN ou MAX ***/
	private MinMaxNode bestSon = null;


	/***************************** METODOS *****************************/
	/*** Imprime todo o conteúdo do nó ***/
	public void printNodeContent() {
		if (this.min) {
			System.out.println("ATENÇAO - LISTA DE JOGADAS INIMIGAS");
		}
		System.out.println();
		System.out.println("Vou imprimir um nó");
		//this.board.printBoard(this.board.getCell());
		if (this.min) {
			System.out.println("ATENÇAO - LISTA DE JOGADAS INIMIGAS");
		}
		this.board.printPlayableDetails(this.bestPlays, this.board.getCell());
		System.out.println("ESTADO: " + this.min);
		System.out.println("Possuo " + sons.size() + " filhos.");
	}
	
	/***************************** GET'N SET *****************************/
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public ArrayList<Transition> getBestPlays() {
		return bestPlays;
	}
	public void setBestPlays(ArrayList<Transition> bestPlays) {
		this.bestPlays = bestPlays;
	}
	public ArrayList<MinMaxNode> getSons() {
		return sons;
	}
	public void setSons(ArrayList<MinMaxNode> sons) {
		this.sons = sons;
	}
	public boolean isMin() {
		return min;
	}
	public void setMin(boolean min) {
		this.min = min;
	}
	public MinMaxNode getBestSon() {
		return bestSon;
	}

	public void setBestSon(MinMaxNode bestSon) {
		this.bestSon = bestSon;
	}
}
