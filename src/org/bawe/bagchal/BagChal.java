package org.bawe.bagchal;


/**
 * BagChal Model to coordinate the gameplay. One object of this class is one game.
 *
 * @Author Stephan Westphal
 */

public class BagChal {

	/**
	 * Tigers will be initialized and placed immediately, as they start in the 4 corners.
	 */
	private Tiger[] tigers = {
			new Tiger(0, 0),
			new Tiger(0, 4),
			new Tiger(4, 0),
			new Tiger(4, 4)
	};

	/**
	 * Array of Goats, houses 20, uninitialized. Any goat that will be paced on board takes an empty place in this
	 * array. When it is full, no further goats can be placed.
	 */
	private Goat[] goats = new Goat[Goat.maxInstances];

	/**
	 * Currently active player. Game starts with goat.
	 */
	private Player currentPlayer = Player.GOAT; // Goat or Tiger

	/**
	 * Adjacency Matrix showing possible from/to move combinations.
	 */
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

	/**
	 * counts and returns the number of goats already eaten
	 * @return number of goats eaten
	 */
	public int getNumGoatsEaten(){
		int numGoatsEaten = 0;
		for(Goat goat : this.goats){
			if(goat != null && goat.isEaten()){
				numGoatsEaten++;
			}
		}
		return numGoatsEaten;
	}

	/**
	 * Calcualtes number of goats available to be placed
	 * @return number of goats available
	 */
	public int getNumGoatsAvailable(){
		int numGoatsSet = 0;
		for(Goat goat : this.goats){
			if(goat != null){
				numGoatsSet++;
			}
		}
		return this.goats.length - numGoatsSet;
	}

	/**
	 * Returns Player currently active.
	 * @return {@link Player} GOAT or TIGER
	 */
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	/**
	 * Checks if a player has won.
	 * @return {@link Player} winner, null if game is still active.
	 */
	public Player getWinner(){
		Player winner = null;
		if(getNumGoatsEaten() >= 5){
			winner = Player.TIGER;
		}else if(countPossibleTigerMoves() == 0){
			winner = Player.GOAT;
		}
		return winner;
	}

	/**
	 * Returns the board as a 2-dimensional char array
	 * @return int[row][col]. 0 for empty, 'T' for Tiger, 'G' for goat.
	 */
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

	/**
	 * places a goat if it is allowed.
	 * @param x column of destination on board, ranging from 0 to 4
	 * @param y row of destination on board, ranging from 0 to 4
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
	 * moves the currently active figure if the game allows it.
	 * @param src_x column of figure to move, ranging from 0 to 4
	 * @param src_y row of figure to move, ranging from 0 to 4
	 * @param dst_x column of destination on board, ranging from 0 to 4
	 * @param dst_y row of destination on board, ranging from 0 to 4
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


	/**
	 * Simple check to see if provided coordinated are within the bounds of the game board.
	 */
	private boolean checkBounds(int x, int y){
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}

	/**
	 * Counts and sums, for every Tiger in the game, all possible moves, to help determine game-over for tiger.
	 */
	private int countPossibleTigerMoves(){
		int numPossibleMoves = 0;
		for (Tiger tiger : this.tigers){
			numPossibleMoves += this.countPossibleMoves(tiger.getPos_x(), tiger.getPos_y(), Tiger.maxDistance);
		}
		return numPossibleMoves;
	}

	/**
	 * Counts the theoretical possible moves of a figure with a given maximum travel distance (usually 1). Does not
	 * check if it can jump, only if there are free fields.
	 * @param start_x column of figure on board
	 * @param start_y row of figure on board
	 * @return the absolute maximum number of possible moves
	 */
	private int countPossibleMoves(int start_x, int start_y, int max_distance){
		int numPossibleMoves = 0;
		for (int x = -max_distance; x <= max_distance; x++){
			int dst_x = start_x + x;
			if(dst_x >= 0 && dst_x < 5){
				for (int y = -max_distance; y <= max_distance; y++){
					int dst_y = start_y + y;
					if(this.checkBounds(dst_x, dst_y)){
						if(this.checkMoveValidity(start_x, start_y, dst_x, dst_y) > 0 && !this.checkOccupancy(dst_x, dst_y)){
							numPossibleMoves++;
						}
					}
				}
			}
		}
		return numPossibleMoves;
	}

	/**
	 * Checks whether a move is valid according to the adjacency-matrix. Easier than calculating it.
	 * @param from_x Column of figure to move
	 * @param from_y Row of figure to move
	 * @param to_x Column to move figure to
	 * @param to_y Row to move figure to
	 * @return number of hops of travel are possible (0: none, 1: move, 2: jump)
	 */
	private int checkMoveValidity(int from_x, int from_y, int to_x, int to_y){
		return (BagChal.validMoves[from_y*5 + from_x][to_y*5 + to_x]);
	}

	/**
	 * checks and returns the type of occupant on the given field.
	 * @param x Column of field
	 * @param y Row of field
	 * @return {@link Player} GOAT, TIGER or null if empty.
	 */
	private Player getOccupancyType(int x, int y){
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

	/**
	 * checks if a field is empty.
	 * @param x Column of field
	 * @param y Row of field
	 * @return boolean true if not empty.
	 */
	private boolean checkOccupancy(int x, int y){
		return this.getOccupancyType(x, y) != null;
	}

	/**
	 * Places a goat to the specified cordinates - does not check if it can.
	 * @param x Column of field to place on
	 * @param y Row of field to place on
	 * @return  true if successfull
	 * */
	private boolean placeGoat(int x, int y){
		for (int i = 0; i < this.goats.length; i++){
			if(this.goats[i] == null){
				this.goats[i] = new Goat(x, y);
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves a goat from the specified location to the destination - no checks for validity.
	 * @param from_x Column of figure to move
	 * @param from_y Row of figure to move
	 * @param to_x Column to move figure to
	 * @param to_y Row to move figure to
	 * */
	private void moveGoat(int from_x, int from_y, int to_x, int to_y) {
		Goat goat = this.findGoat(from_x, from_y);
		if(goat == null){
			throw new IllegalMoveException("No goat at position "+from_x+"x"+from_y);
		}
		goat.move(to_x, to_y);
	}

	/**
	 * Moves a tiger from the specified location to the destination. Contains checks if the Tiger is found at the
	 * location and a jump is valid. Does not check whether the from/to coordinates are valid.
	 * @param from_x Column of figure to move
	 * @param from_y Row of figure to move
	 * @param to_x Column to move figure to
	 * @param to_y Row to move figure to
	 * @param jump whether to jump or not.
	 * */
	private void moveTiger(int from_x, int from_y, int to_x, int to_y, boolean jump){
		Tiger tiger = this.findTiger(from_x, from_y);
		if(tiger == null){
			throw new IllegalMoveException("No tiger at position "+from_x+","+from_y);
		}

		if(jump){
			int jump_x = from_x + (to_x - from_x)/2;
			int jump_y = from_y + (to_y - from_y)/2;
			Player jump_occupant = this.getOccupancyType(jump_x, jump_y);

			if(jump_occupant == Player.GOAT){
				// eat it!
				this.eatGoat(jump_x, jump_y);
			}else if(jump_occupant == Player.TIGER){
				throw new IllegalMoveException("Cannot jump over another tiger ("+jump_x+","+jump_y+").");
			}else if(jump_occupant == null){
				throw new IllegalMoveException("Cannot jump over empty field ("+jump_x+","+jump_y+").");
			}
		}
		tiger.move(to_x, to_y);
	}

	/**
	 * finds and returns a tiger by coordinates
	 * @param x Column of field to place on
	 * @param y Row of field to place on
	 * @return  Tiger if found, else null
	 */
	private Tiger findTiger(int x, int y){
		for (Tiger tiger : this.tigers){
			if(tiger.getPos_x() == x && tiger.getPos_y() == y){
				return tiger;
			}
		}
		return null;
	}

	/**
	 * finds and returns a goat by coordinates
	 * @param x Column of field to place on
	 * @param y Row of field to place on
	 * @return  Goat if found, else null
	 */
	private Goat findGoat(int x, int y){
		for (Goat goat : this.goats){
			if(goat != null && !goat.isEaten() && goat.getPos_x() == x && goat.getPos_y() == y){
				return goat;
			}
		}
		return null;
	}

	/**
	 * Feed the tiger - takes coordinates of a goat jumped by a tiger and sets it's status to eaten.
	 * @param x Column of field of goat to be eaten
	 * @param y Row of field of goat to be eaten
	 * @return  true if goat was found and eaten, else false
	 */
	private boolean eatGoat(int x, int y){
		Goat goat = this.findGoat(x, y);
		if(goat != null){
			goat.getEaten();
			return true;
		}else return false;
	}
}
