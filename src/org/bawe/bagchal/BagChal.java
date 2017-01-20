package org.bawe.bagchal;


/**
 * Created by stephan on 12/21/16.
 */

public class BagChal {

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
	 * @return Player winner, null if game is still active.
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

	private boolean checkBounds(int x, int y){
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}

	private int checkMoveValidity(int from_x, int from_y, int to_x, int to_y){
		return (BagChal.validMoves[from_y*5 + from_x][to_y*5 + to_x]);
	}

	public Player getOccupancyType(int x, int y){
		Player occupant = null;
		for(Goat goat : this.goats){
			if( goat != null && !goat.isEaten() && goat.getPos_x() == x && goat.getPos_y() == y){
				occupant = Player.GOAT;
				break; // no need to search further, as we just found a goat on this position
			}
		}
		for(Tiger tiger : this.tigers){
			if( tiger != null && tiger.getPos_x() == x && tiger.getPos_y() == y){
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

	/**
	 * places a goat if it is allowed.
	 */
	public void placeFigure(int x, int y){
		if(!this.checkBounds(x, y)){
			throw new OutOfBoundsException();
		}

		if(this.checkOccupancy(x, y)){
			throw new IllegalMoveException("Field is occupied.");
		}

		if(this.currentPlayer == Player.GOAT){
			if(this.getNumGoatsAvailable() < 1){
				throw new IllegalMoveException("No goats remaining to be placed");
			}
			// all checks passed, place goat;
			if(this.placeGoat(x, y)){
				this.currentPlayer = Player.TIGER;
			}
		}else{
			throw new IllegalMoveException("Tiger cannot be placed");
		}
	}

	/**
	 * moves a figure if allowed.
	 */
	public void moveFigure(int src_x, int src_y, int dst_x, int dst_y){
		if(!this.checkBounds(dst_x, dst_y)){
			throw new OutOfBoundsException();
		}

		if(this.checkOccupancy(dst_x, dst_y)){
			throw new IllegalMoveException("Field is occupied.");
		}

		int moveValidity = this.checkMoveValidity(src_x, src_y, dst_x, dst_y);
		if((this.currentPlayer == Player.GOAT && moveValidity != 1) ||
				(this.currentPlayer == Player.TIGER && moveValidity < 1)){
			throw new IllegalMoveException("Cannot move in this direction");
		}

		if(this.currentPlayer == Player.GOAT){
			if(this.getNumGoatsAvailable() > 0){
				throw new IllegalMoveException("All remaining goats have to be placed before they can be moved.");
			}
			// all checks passed, place goat;
			this.moveGoat(src_x, src_y, dst_x, dst_y);
			this.currentPlayer = Player.TIGER;
		}else{
			this.moveTiger(src_x, src_y, dst_x, dst_y, (moveValidity == 2));
			this.currentPlayer = Player.GOAT;
		}
	}



	private boolean placeGoat(int x, int y){
		for (int i = 0; i < this.goats.length; i++){
			if(this.goats[i] == null){
				this.goats[i] = new Goat(x, y);
				return true;
			}
		}
		return false;
	}

	private void moveGoat(int src_x, int src_y, int dst_x, int dst_y) {
		Goat goat = this.findGoat(src_x, src_y);
		if(goat == null){
			throw new IllegalMoveException("No goat at position "+src_x+"x"+src_y);
		}

		goat.move(dst_x, dst_y);
	}

	private void moveTiger(int src_x, int src_y, int dst_x, int dst_y, boolean jump){
		Tiger tiger = this.findTiger(src_x, src_y);
		if(tiger == null){
			throw new IllegalMoveException("No tiger at position "+src_x+","+src_y);
		}

		if(jump){
			int jump_x = src_x + (dst_x - src_x)/2;
			int jump_y = src_y + (dst_y - src_y)/2;
			Player jump_occupant = this.getOccupancyType(jump_x, jump_y);

			if( jump_occupant == Player.GOAT){
				// eat it!
				tiger.eat();
				this.eatGoat(jump_x, jump_y);
			}else if( jump_occupant == Player.TIGER){
				throw new IllegalMoveException("Cannot jump over another tiger ("+jump_x+","+jump_y+").");
			}else if(jump_occupant == null){
				throw new IllegalMoveException("Cannot jump over empty field ("+jump_x+","+jump_y+").");
			}
		}
		tiger.move(dst_x, dst_y);
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
			if(goat != null && goat.getPos_x() == x && goat.getPos_y() == y){
				return goat;
			}
		}
		return null;
	}

	private boolean eatGoat(int x, int y){
		Goat goat = this.findGoat(x, y);
		if(goat != null){
			goat.getEaten();
			return true;
		}else return false;
	}

	public char[][] getBoard(){
		char[][] board = {
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
		};
		for(Goat goat : this.goats){
			if( goat != null && !goat.isEaten()){
				board[goat.getPos_y()][goat.getPos_x()] = 'G';
			}
		}
		for(Tiger tiger : this.tigers){
			if( tiger != null ){
				board[tiger.getPos_y()][tiger.getPos_x()] = 'T';
			}
		}
		return board;
	}
}
