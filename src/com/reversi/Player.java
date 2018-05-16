package com.reversi;

public class Player {
	/**** Atributes ****/
	private String name;
	private char piece;
	private int score;

	/**** Constructors ****/
	public Player() {
	}
	
	public Player(String newName, char piece) {
		this.name = newName;
		this.piece = piece;
	}
	
	public Player(String newName, char piece, int score) {
		this.name = newName;
		this.piece = piece;
		this.score = score;
	}
	
	/**** Getters'N Setters ****/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getPiece() {
		return piece;
	}
	public void setPiece(char cell) {
		this.piece = cell;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
