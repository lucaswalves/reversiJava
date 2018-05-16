package com.reversi;

public class Main {
	/***************************** ATRIBUTOS *****************************/
	private Game game = new Game();

	/***************************** MAINZAO DA MASSA *****************************/
	public static void main(String[] args) {
		Game game = new Game();
		/*** Parametro 0 - Humano x Humano ***/
		/*** Parametro 1 - Humano x IA ***/
		game.startGame(1);
	}
	
	/***************************** GET'N SET *****************************/
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
