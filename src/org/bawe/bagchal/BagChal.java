package org.bawe.bagchal;

/**
 * Created by stephan on 12/21/16.
 */

public class BagChal {
	private char[][] board = {
			{'T',0,0,0,'T'},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{'T',0,0,0,'T'}
	};

	private Tiger[] tigers = {
			new Tiger(0, 0),
			new Tiger(0, 4),
			new Tiger(4, 0),
			new Tiger(4, 4)
	};

	private Goat[] goats = new Goat[20];

	private Player currentPlayer = Player.GOAT; // Goat or Tiger

	private static int [] [] validMoves =
			{
					{0, 1, 2, 0, 0, 1, 1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{1, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{2, 1, 0, 1, 2, 0, 1, 1, 1, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 2, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 2, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{1, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{1, 1, 1, 0, 0, 1, 0, 1, 2, 0, 1, 1, 1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 0, 0, 2, 1 ,0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 1, 0, 2, 1, 0, 1, 0, 0, 1, 1, 1, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
					{2, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 1, 2, 0, 0, 1, 1, 0, 0, 0, 2, 0, 2, 0, 0},
					{0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0},
					{2, 0, 2, 0, 2, 0, 1, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1, 1, 0, 2, 0, 2, 0, 2},
					{0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0},
					{0, 0, 2, 0, 2, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 2},
					{0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 1, 1, 1, 0, 0, 1, 0, 1, 2, 0, 1, 1, 1, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0, 0, 1, 0, 0},
					{0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1, 1, 1, 0, 2, 1, 0, 1, 0, 0, 1, 1, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 1, 2, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 2, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 1, 1, 1, 0, 2, 1, 0, 1, 2},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0}
			};

	public void BagChal(){
	}


	public int getNumGoatsEaten(){
		int numGoatsEaten = 0;
		for(Goat goat : this.goats){
			if(goat != null && goat.isEaten()){
				numGoatsEaten++;
			}
		}
		return numGoatsEaten;
	}

	public int getNumGoatsAvailable(){
		int numGoatsSet = 0;
		for(Goat goat : this.goats){
			if(goat != null){
				numGoatsSet++;
			}
		}
		return this.goats.length - numGoatsSet;
	}

	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	/**
	 * Checks if a player has won.
	 * @return char winner, 0 if game is still active.
	 */
	public Player getWinner(){
		Player winner = null;
		if(countGoatsEaten() >= 5){
			winner = Player.TIGER;
		}else if(countPossibleTigerMoves() == 0){
			winner = Player.GOAT;
		}
		return winner;
	}

	public int countGoatsEaten(){
		int numGoatsEaten = 0;
		for (Goat goat : this.goats){
			if(goat != null && goat.isEaten()){
				numGoatsEaten++;
			}
		}
		return numGoatsEaten;
	}

	public int countPossibleTigerMoves(){
		int numPossibleMoves = 0;
		for (Tiger tiger : this.tigers){
			numPossibleMoves += this.countPossibleMoves(tiger.getPos_x(), tiger.getPos_y(), Tiger.maxDistance);
		}
		return numPossibleMoves;
	}


	public boolean checkBounds(int x, int y){
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}

	public int checkMoveValidity(int from_x, int from_y, int to_x, int to_y){
		if(! checkBounds(from_x, from_y) || !checkBounds(to_x, to_y)){
			return -1; // destination out of Range
		}

		return (BagChal.validMoves[from_y*5 + from_x][to_y*5 + to_x]);
	}

	public Player getOccupancyType(int x, int y){
		Player occupant = null;
		for(Goat goat: this.goats){
			if( goat != null && goat.getPos_x() != x && goat.getPos_y() != y){
				occupant = Player.GOAT;
				break; // no need to search further, as we just found a goat on this position
			}
		}
		for(Tiger tiger: this.tigers){
			if( tiger != null && tiger.getPos_x() != x && tiger.getPos_y() != y){
				occupant = Player.TIGER;
				break; // no need to search further, as we just found a tiger on this position
			}
		}
		return occupant;
	}

	public boolean checkOccupancy(int x, int y){
		return this.getOccupancyType(x, y) != null;
	}

	public int countPossibleMoves(int start_x, int start_y, int max_distance){
		int numPossibleMoves = 0;
		for (int x = -max_distance; x <= max_distance; x++){
			for (int y = -max_distance; y <= max_distance; y++){
				int dst_x = start_x + x;
				int dst_y = start_y + y;
				if(this.checkMoveValidity(start_x, start_y, dst_x, dst_y) > 0 && !this.checkOccupancy(dst_x, dst_y)){
					numPossibleMoves++;
				}
			}
		}
		return numPossibleMoves;
	}

	public boolean setGoat(int dst_x, int dst_y){
		return true;
	}

	public boolean moveGoat(int src_x, int src_y, int dst_x, int dst_y) {
		Goat goat = this.findGoat(src_x, src_y);
		if(goat == null) return false; // Goat not found

		int moveType = this.checkMoveValidity(src_x, src_y, dst_x, dst_y);
		if(moveType != 1) return false; // invalid move

		goat.move(dst_x, dst_y);
		return true;
	}

	public boolean moveTiger(int src_x, int src_y, int dst_x, int dst_y){
		Tiger tiger = this.findTiger(src_x, src_y);
		if(tiger == null) return false; // Tiger not found

		int moveType = this.checkMoveValidity(src_x, src_y, dst_x, dst_y);
		if(moveType < 1 || moveType > 2) return false; // invalid move


		if(this.checkOccupancy(dst_x, dst_y)) return false; // Field is occupied.

		if(moveType == 2){
			// ToDo: calculate jumped-coordinates.
			Player jump_occupant = this.getOccupancyType(dst_x, dst_y);
			if( jump_occupant == Player.TIGER) return false;
			if( jump_occupant == Player.GOAT){
				// eat it!

			}
		}
		tiger.move(dst_x, dst_y);
		return true;
	}

	private Tiger findTiger(int x, int y){
		for (Tiger tiger : this.tigers){
			if(tiger.getPos_x() == x && tiger.getPos_y() == y){
				return tiger;
			}
		}
		return null;
	}

	private Goat findGoat(int x, int y){
		for (Goat goat : this.goats){
			if(goat.getPos_x() == x && goat.getPos_y() == y){
				return goat;
			}
		}
		return null;
	}


	public char[][] getBoard(){
		char[][] board = {
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
		};
		for(Goat goat: this.goats){
			if( goat != null ){
				board[goat.getPos_y()][goat.getPos_x()] = 'G';
			}
		}
		for(Tiger tiger: this.tigers){
			if( tiger != null ){
				board[tiger.getPos_y()][tiger.getPos_x()] = 'T';
			}
		}

		return this.board;
	}
}
