package com.reversi;

import java.util.ArrayList;

public class Board {
	/***************************** ATRIBUTOS *****************************/
	/*** Tamanho estático do tabuleiro quadrado ***/
	public static int SIZE = 8;
	/*** Número de casas do tabuleiro em função do tamanho SIZE ***/
	public Cell[][] cell = new Cell[SIZE][SIZE];

	/****************************** MÉTODOS ******************************/
	/*** Verifica se a posição está vazia ***/
	public boolean isEmpty(int x, int y, Cell[][] cell) {
		if (cell[x][y].content == '_') {
			return true;
		} else {
			return false;
		}
	}

	/*** Verifica se a posição no tabuleiro possui algum elemento inimigo ao seu redor ***/
	public ArrayList<Coordinate> hasBadNeighborhood(int x, int y, char enemyCell, Cell[][] cell) {
		ArrayList<Coordinate> badNeighbors = new ArrayList<Coordinate>();
		System.out.println("LOOKING FOR ENEMIES AROUND ["+x+"]["+y+"] - LOOKING FOR '" + enemyCell+"'");
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				/* Respeito os limites de bordas do tabuleiro */
				if (i >= 0 && j >= 0 && i < SIZE && j < SIZE) {
					/* Verifico se é uma célula inimiga */
					if (cell[i][j].content == enemyCell) {
						/* Armazeno a célula inimiga em uma lista dinamica de coordenadas inteiras */
						System.out.print("FOUND AN ENEMY CELL (" + enemyCell +") AT [" +i+"]["+j+"]" );
						badNeighbors.add(new Coordinate(i,j));
						System.out.println(" - WATCHING TARGET, OVER.");
					}
				}
			}
		}
		return badNeighbors;
	}
	/*** Verifica o peso da casa em questão ***/
	public int checkCellWeight(int x, int y) {
		if ((x == 0 && y == 0) || (x == 0 && y == SIZE-1) || (x == SIZE-1 && y == 0) || (x == SIZE-1 && y == SIZE-1)) {
			return 20;
		} else if((x == 0 && y == 1) || (x == 1 && y == 0) || (x == 1 && y == 1) ||
				  (x == 0 && y == SIZE-2) || (x == 1 && y == SIZE-1) || (x == 1 && y == SIZE-2) ||
				  (x == SIZE-1 && y == 1) || (x == SIZE-2 && y == 0) || (x == SIZE-2 && y == 1) ||
				  (x == SIZE-2 && y == SIZE-1) || (x == SIZE-1 && y == SIZE-2) || (x == SIZE-2 && y == SIZE-2)) {
			return 0;
		} else if (x >= 0 && y >= 0) {
			return 10;
		} else {
			System.out.println("Erro ao reconhecer o peso da casa (" + x + "," + y +")");
			return -1;
		}
	}
	/*** Verifica toda a direção de uma coordenada (em relação a outra) até encontrar uma peça aliada ***/
	public Transition findAllies(int x, int y, char alliedCell, ArrayList<Coordinate> badNeighbors, Cell[][] matrix) {
		/* Cria um elemento transição para receber os dados */
		Transition newTransition = new Transition();
		
		System.out.println("ENTREI NA FIND ALLIES COM (" + x + "," + y + ")");
		for (int i = 0; i < badNeighbors.size(); i++) {
			/* Diagonal superior esquerda */
			if (badNeighbors.get(i).x == x-1 && badNeighbors.get(i).y == y-1) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] na Diagonal superior esquerda");
				/* Busca direcionada na diagonal superior esquerda */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = 1; x-j >= 0 && y-j >= 0; j++) {
					numberOfIterations++;
					if (matrix[x-j][y-j].content == '_' && isVirgin == false) {
						j = SIZE;
					}
					else if (matrix[x-j][y-j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal superior esquerda ["+(x-j)+"]["+(y-j)+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(x-j,y-j)); //Elemento final
						newTransition.direction.add(new Coordinate(-1,-1)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
					}
					isVirgin = false;	
				}
			}
			/* Esquerda */
			else if(badNeighbors.get(i).x == x-0 && badNeighbors.get(i).y == y-1) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] esquerda");
				/* Busca direcionada na esquerda */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = y-1; j >= 0 && y-1 >= 0; j--) {
					numberOfIterations++;
					if(matrix[x][j].content == '_' && isVirgin == false) {
						j = -1;
					}
					else if (matrix[x][j].content == alliedCell) {
						System.out.println("Encontrei um aliado na esquerda ["+badNeighbors.get(i).x+"]["+j+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(x,j)); //Elemento final
						newTransition.direction.add(new Coordinate(0,-1)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = -1;
					}
					isVirgin = false;
					System.out.println("FIZ " + numberOfIterations + " ITERAÇOES.");
				}
			}
			/* Diagonal inferior esquerda */
			else if(badNeighbors.get(i).x == x+1 && badNeighbors.get(i).y == y-1) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "]  inferior esquerda");
				/* Busca direcionada na diagonal inferior esquerda */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = 1; (x+j < SIZE) && (y-j >= 0); j++) {
					numberOfIterations++;
					if(matrix[x+j][y-j].content == '_' && isVirgin == false) {
						j = y+1;
					}
					else if (matrix[x+j][y-j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal inferior esquerda ["+(x+j)+"]["+(y-j)+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(x+j,y-j)); //Elemento final
						newTransition.direction.add(new Coordinate(1,-1)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = y +1;
					}
					isVirgin = false;
				}
			}
			/* Baixo */
			else if(badNeighbors.get(i).x == x+1 && badNeighbors.get(i).y == y-0) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "] abaixo");
				/* Busca direcionada pra baixo */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = x+1; j < SIZE; j++) {
					numberOfIterations++;
					if(matrix[j][y].content == '_' && isVirgin == false) {
						j = SIZE;
					}
					else if (matrix[j][y].content == alliedCell) {
						System.out.println("Encontrei um aliado embaixo ["+j+"]["+y+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(j,y)); //Elemento final
						newTransition.direction.add(new Coordinate(1,0)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
					}
					isVirgin = false;
				}
			}
			/* Diagonal inferior direita */
			else if(badNeighbors.get(i).x == x+1 && badNeighbors.get(i).y == y+1) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "] Diagonal inferior direita");
				/* Busca direcionada na diagonal inferior direita */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = 1; x+j < SIZE && y+j < SIZE; j++) {
					numberOfIterations++;
					if(matrix[x+j][y+j].content == '_' && isVirgin == false) {
						j = SIZE;
					}
					else if (matrix[x+j][y+j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal inferior direita ["+(x+j)+"]["+(y+j)+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(x+j,y+j)); //Elemento final
						newTransition.direction.add(new Coordinate(1,1)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
					}
					isVirgin = false;
				}
			}
			/* Direita */
			else if(badNeighbors.get(i).x == x-0 && badNeighbors.get(i).y == y+1) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] na direita");
				/* Busca direcionada na direita */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = y+1; j < SIZE; j++) {
					numberOfIterations++;
					if(matrix[x][j].content == '_' && isVirgin == false) {
						j = SIZE;
					}
					else if (matrix[x][j].content == alliedCell) {
						System.out.println("Encontrei um aliado na direita ["+x+"]["+j+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(x,j)); //Elemento final
						newTransition.direction.add(new Coordinate(0,1)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
					}
					isVirgin = false;
				}
			}
			/* Diagonal superior direita */
			else if(badNeighbors.get(i).x == x-1 && badNeighbors.get(i).y == y+1) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "] Diagonal superior direita");
				/* Busca direcionada na diagonal superior direita */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = 1; (x-j >= 0) && (y+j < SIZE); j++) {
					numberOfIterations++;
					if(matrix[x-j][y+j].content == '_' && isVirgin == false) {
						j = x +1;
					}
					else if (matrix[x-j][y+j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal superior direita ["+(x-j)+"]["+(y+j)+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(x-j,y+j)); //Elemento final
						newTransition.direction.add(new Coordinate(-1,1)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = x +1;
					}
					isVirgin = false;
				}
			}
			/* Em cima */
			else if(badNeighbors.get(i).x == x-1 && badNeighbors.get(i).y == y-0) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] em cima");
				/* Busca direcionada pra cima */
				boolean isVirgin = true;
				int numberOfIterations = 0;
				for (int j = x-1; j >= 0 && x-1 >= 0; j--) {
					numberOfIterations++;
					if(matrix[j][y].content == '_' && isVirgin == false) {
						j = -1;
					}
					else if (matrix[j][y].content == alliedCell) {
						System.out.println("Encontrei um aliado logo acima ["+j+"]["+y+"]");
						/* Criação do elemento transição para lógica de jogo */
						newTransition.initial.add(new Coordinate(x,y)); //Elemento inicial
						newTransition.end.add(new Coordinate(j,y)); //Elemento final
						newTransition.direction.add(new Coordinate(-1,0)); //Direção: Superior esquerda
						newTransition.numberOfIterations.add(numberOfIterations); //Number of iterations to make Inital~End
						newTransition.pointsAdd.add(numberOfIterations-1);//Number of points that will be add to player
						newTransition.cellWeight.add(this.checkCellWeight(x, y));//Number of points that will be remove to the other player
						/* Quebra da condição ao achar um elemento aliado */
						j = -1;
					}
					isVirgin = false;
				}
			}
			/* Erro - não encaixou em nenhum dos casos */
			else {
				System.out.println("Erro ao verificar linha de aliados!");
			}
		}
		return newTransition;
	}

	/*** Verifica toda a matriz para identificar casas jogáveis no turno ***/
	public ArrayList<Transition> findPlayableCells(Cell[][] board, char enemyCell, char alliedCell){
		ArrayList<Transition> possibleTransitions = new ArrayList<Transition>();
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				/* Caso a célular esteja vazia */
				if (this.isEmpty(i, j, board)) {
					/* Caso a célula tenha inimigos ao seu redor */
					if (this.hasBadNeighborhood(i, j, enemyCell, board).size() > 0) {
						System.out.println("ENCONTREI INIMIGOS - VERIFICAR AMIGOS PERTO");
						/* E possua aliado(s) a frente desse(s) inimigo(s) */
						Transition newTransition = new Transition();
						newTransition = this.findAllies(i, j, alliedCell, this.hasBadNeighborhood(i, j, enemyCell, board), board);
						if (newTransition.initial.size() > 0) {
							System.out.println("VOU ADD ("+i+","+j+")");
							possibleTransitions.add(newTransition);
							System.out.println();
						}
					}
				}
			}
		}
		System.out.println("VOU RETORNAR O PLAYABLE");
		/* Retorna uma lista com todas as posições jogáveis no momento */
		return possibleTransitions;
	}
	
	/*** Imprime uma lista de possíveis jogadas ***/
	public void printPlayableCells(ArrayList<Transition> playableCells, Cell[][] board) {
		this.printBoard(board);
		if (playableCells.size() > 0) {
			System.out.println("Possíveis jogadas:");
			for (int i = 0; i < playableCells.size(); i++) {
				System.out.println("Jogada "+(i+1)+": ("+playableCells.get(i).initial.get(0).x+","+playableCells.get(i).initial.get(0).y+")");
			}
		} else {
			System.out.println("Não existem jogadas possíveis.");
		}
	}
	
	/*** Imprime uma lista detalhada das possíveis jogadas ***/
	public void printPlayableDetails(ArrayList<Transition> playableCells, Cell[][] board) {
		this.printBoard(board);
		if (playableCells.size() > 0) {
			System.out.println("Possíveis jogadas:");
			int possiblePlays = 0;
			for (int i = 0; i < playableCells.size(); i++) {
				for (int j = 0; j < playableCells.get(i).initial.size(); j++) {
					possiblePlays++;
					System.out.print("Jogada "+(possiblePlays)+": "+ 
							"("+playableCells.get(i).initial.get(j).x+","+playableCells.get(i).initial.get(j).y+") -> "
						  + "("+playableCells.get(i).end.get(j).x+","+playableCells.get(i).end.get(j).y+")");
					System.out.println(" - Na direção:("+playableCells.get(i).direction.get(j).x+","+playableCells.get(i).direction.get(j).y+")"+ 
						  " Ganhando " + playableCells.get(i).pointsAdd.get(j) + " pontos");
					System.out.println("Encontrando o elemento com " + playableCells.get(i).numberOfIterations.get(j) + " iterações.");
					System.out.println("Peso da casa é de " + playableCells.get(i).cellWeight.get(j));
				}
			}
		} else {
			System.out.println("Não existem jogadas possíveis.");
		}
	}
	
	/*** Método com filtros de inserção no tabuleiro ***/
	public Cell[][] protectedInsertItem(int x, int y, char newContent, String player, ArrayList<Transition> transitions, Cell[][] board) {
		for (int i = 0; i < transitions.size(); i++) {
			for (int j = 0; j < transitions.get(i).initial.size(); j++) {
				if (x == transitions.get(i).initial.get(j).x && y == transitions.get(i).initial.get(j).y) {
					board = this.changeItemsOnPlay(x, y, newContent, transitions.get(i), board);
					board = this.insertItem(x, y, newContent, player, board);
					return board;
				}
			}
			
		}
		System.out.println("Erro na inserção com filtros do elemento ("+x+","+y+")");
		return board;
	}
	
	/*** Método para alterar as peças entre a jogada e a aliada delimitadora ***/
	public Cell[][] changeItemsOnPlay(int x, int y, char newContent, Transition transition, Cell[][] board) {
		for (int i = 0; i < transition.initial.size(); i++) {
			int j = x;
			int j2 = y;
			if (j < transition.end.get(i).x && j2 < transition.end.get(i).y) {
				while (j < transition.end.get(i).x && j2 < transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j < transition.end.get(i).x && j2 < transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			} 
			
			else if(j > transition.end.get(i).x && j2 > transition.end.get(i).y){
				while (j > transition.end.get(i).x && j2 > transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j > transition.end.get(i).x && j2 > transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			else if(j > transition.end.get(i).x && j2 < transition.end.get(i).y){
				while (j > transition.end.get(i).x && j2 < transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j > transition.end.get(i).x && j2 < transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			else if(j < transition.end.get(i).x && j2 > transition.end.get(i).y){
				while (j < transition.end.get(i).x && j2 > transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j < transition.end.get(i).x && j2 > transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			else if(j < transition.end.get(i).x && j2 == transition.end.get(i).y){
				while (j < transition.end.get(i).x && j2 == transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j < transition.end.get(i).x && j2 == transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			else if(j == transition.end.get(i).x && j2 < transition.end.get(i).y){
				while (j == transition.end.get(i).x && j2 < transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j == transition.end.get(i).x && j2 < transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			else if(j > transition.end.get(i).x && j2 == transition.end.get(i).y){
				while (j > transition.end.get(i).x && j2 == transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j > transition.end.get(i).x && j2 == transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			else if(j == transition.end.get(i).x && j2 > transition.end.get(i).y){
				while (j == transition.end.get(i).x && j2 > transition.end.get(i).y) {
					j+=transition.direction.get(i).x;
					j2+=transition.direction.get(i).y;
					if (j == transition.end.get(i).x && j2 > transition.end.get(i).y) {
						board[j][j2].content = newContent;
					}
				}
			}
			
			
		}
		return board;
	}
	
	/*** Método de inserção no tabuleiro ***/
	public Cell[][] insertItem(int x, int y, char newContent, String player, Cell[][] board) {
		System.out.println("Jogada: " + player + " jogou na casa ["+x+"]["+y+"] = " + newContent);
		board[x][y].content = newContent;
		return board;
	}
	
	/*** Método para verificar se o tabuleiro está com todas as células completas ***/
	public boolean isFull(Cell[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].content == '_') {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/*** Reseta o tabuleiro ***/
	public void resetBoard() {
		/* Instancia as variáveis internas a matriz */
		Cell newBoard[][] = new Cell[SIZE][SIZE];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				newBoard[i][j] = new Cell();
			}
		}
		this.cell = newBoard;
		
		for (int i = 0; i < this.cell.length; i++) {
			for (int j = 0; j < this.cell[i].length; j++) {
				if (i==((SIZE/2)-1) && j==((SIZE/2)-1) || i==(SIZE/2) && j==(SIZE/2)) {
					this.cell[i][j].content = 'O';
				}else if(i==((SIZE/2)-1) && j==(SIZE/2) || i==(SIZE/2) && j==((SIZE/2)-1)){
					this.cell[i][j].content = 'X';
				}
			}
		}
	}

	/*** Método Imprimir tabuleiro na tela ***/
	public void printBoard(Cell[][] board) {
		/**** Limpa a tela para exibir a matriz ****/
		//this.screenClear();

		/**** Topo da tabela ****/
		for (int i = 0; i < board.length; i++) {
			if (i == 0)
				System.out.print("   ");

			System.out.print("_"+ i +"_ ");

			if (i == board.length-1)
				System.out.println();
		}

		/**** Imprime o resto da tabela ****/
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {System.out.print(i + " |_" + board[i][j].content + "_|");}
				else {System.out.print("_" + board[i][j].content + "_|");}
			}
			System.out.println();
		}
	}

	/*** "Limpa" a tela ***/
	public void screenClear(){
		for(int i = 0; i <= 20; i++){
			System.out.println();
		}
	}
	
	/*** Verifica o score do jogo ***/
	public int[] getScore(Cell[][] matrix) {
		int[] score = new int[2];
		score[0] = 0;
		score[1] = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j].content == 'O') {
					score[0]++;
				}
				else if (matrix[i][j].content == 'X') {
					score[1]++;
				}
			}
		}
		
		return score;
	}

	/***************************** GET'N SET *****************************/
	public Cell[][] getCell() {
		return cell;
	}
	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}

	public static void main(String[] args) {
		Board board = new Board();

		//board.printBoard(board.getCell());
		//board.insertItem(0, 0, 'X', "Humano");
		board.resetBoard();
		board.printBoard(board.getCell());
		//board.hasBadNeighborhood(3, 3, 'X');
		
		/*** Teste das direções básicas ***/
		/* Inserir a direita para teste */
		/*board.insertItem(3, 5, 'X', "Humano");
		board.insertItem(3, 6, 'O', "Humano");
		board.insertItem(3, 7, 'O', "Humano");
		
		/* Inserir logo abaixo para teste*/
		/*board.insertItem(5, 3, 'X', "Humano");
		board.insertItem(6, 3, 'O', "Humano");
		board.insertItem(7, 3, 'O', "Humano");
		
		/* Inserir esquerda para teste */
		/*board.insertItem(3, 2, 'X', "Humano");
		board.insertItem(3, 1, 'O', "Humano");
		board.insertItem(3, 0, 'O', "Humano");
		
		/* Inserir logo acima para teste*/
		/*board.insertItem(2, 3, 'X', "Humano");
		board.insertItem(1, 3, 'O', "Humano");
		board.insertItem(0, 3, 'O', "Humano");
		
		/*** Teste das direções complexas ***/
		/* Inserir a diagonal superior esquerda para teste */
		/*board.insertItem(2, 2, 'X', "Humano");
		board.insertItem(1, 1, 'O', "Humano");
		board.insertItem(0, 0, 'O', "Humano");
		
		/* Inserir a diagonal inferior esquerda para teste */
		/*board.insertItem(4, 2, 'X', "Humano");
		board.insertItem(5, 1, 'O', "Humano");
		board.insertItem(6, 0, 'O', "Humano");
		
		/* Inserir a diagonal superior direita para teste */
		/*board.insertItem(2, 4, 'X', "Humano");
		board.insertItem(1, 5, 'O', "Humano");
		board.insertItem(0, 6, 'O', "Humano");
		
		/* Inserir a diagonal inferior direita para teste */
		/*board.insertItem(4, 4, 'X', "Humano");
		board.insertItem(5, 5, 'X', "Humano");
		board.insertItem(6, 6, 'O', "Humano");
		board.insertItem(7, 7, 'O', "Humano");*/
		
		/*** Novos casos de teste ***/
		board.setCell(board.insertItem(1, 3, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(1, 4, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(2, 3, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(2, 2, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(2, 4, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(2, 5, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(3, 2, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(3, 5, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(4, 2, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(4, 5, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(4, 6, 'O', "Humano", board.getCell()));
		board.setCell(board.insertItem(5, 2, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(5, 5, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(5, 6, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(6, 5, 'X', "Humano", board.getCell()));
		board.setCell(board.insertItem(7, 6, 'O', "Humano", board.getCell()));
		
		board.printBoard(board.getCell());
		//board.findAllies(2, 5, 'O', board.hasBadNeighborhood(2, 5, 'X', board.getCell()), board.getCell());
		ArrayList<Transition> transitions = board.findPlayableCells(board.getCell(), 'X', 'O');
		//board.printPlayableCells(transitions, board.getCell());
		
		board.printPlayableDetails(transitions, board.getCell());
		Game game = new Game();
		game.alphaBeta(transitions);
		
		/*board.setCell(board.protectedInsertItem(1, 1, 'O', "Humano", transitions, board.getCell()));
		board.setCell(board.protectedInsertItem(7, 5, 'O', "Humano", transitions, board.getCell()));
		board.setCell(board.protectedInsertItem(6, 6, 'O', "Humano", transitions, board.getCell()));*/
		
		game.getBoard().resetBoard();
		game.getBoard().setCell(board.getCell());
		game.setPlayer1(new Player("Thiago", 'O'));
		game.setPlayer2(new Player("Adalberto", 'X'));
		game.aiPlays('X', 'O', "ADALBERTO");
		
		//board.setCell(board.protectedInsertItem(7, 7, 'O', "Humano", transitions, board.getCell()));
		//board.printBoard(board.getCell());
		
	}
}
