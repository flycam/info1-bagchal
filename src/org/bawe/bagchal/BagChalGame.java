package org.bawe.bagchal;


/**
 * BagChalGame Model to coordinate the gameplay. One object of this class is one game.
 *
 * @Author Stephan Westphal
 */

public class BagChalGame {

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

	/**
	 * BagChalGame Game Model constructor. Nothing to initialize here.
	 */
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
				board[goat.getRow()][goat.getColumn()] = 'G';
			}
		}
		for(Tiger tiger : this.tigers){
			if( tiger != null ){
				board[tiger.getRow()][tiger.getColumn()] = 'T';
			}
		}
		return board;
	}

	/**
	 * places a goat if it is allowed.
	 * @param column column of destination on board, ranging from 0 to 4
	 * @param row row of destination on board, ranging from 0 to 4
	 */
	public void placeFigure(int column, int row){
		if(!this.checkBounds(column, row)){
			throw new OutOfBoundsException();
		}

		if(this.checkOccupancy(column, row)){
			throw new IllegalMoveException("Field is occupied.");
		}

		if(this.currentPlayer == Player.GOAT){
			if(this.getNumGoatsAvailable() < 1){
				throw new IllegalMoveException("No goats remaining to be placed");
			}
			// all checks passed, place goat;
			if(this.placeGoat(column, row)){
				this.currentPlayer = Player.TIGER;
			}
		}else{
			throw new IllegalMoveException("Tiger cannot be placed");
		}
	}

	/**
	 * moves the currently active figure if the game allows it.
	 * @param fromColumn column of figure to move, ranging from 0 to 4
	 * @param fromRow row of figure to move, ranging from 0 to 4
	 * @param toColumn column of destination on board, ranging from 0 to 4
	 * @param toRow row of destination on board, ranging from 0 to 4
	 */
	public void moveFigure(int fromColumn, int fromRow, int toColumn, int toRow){
		if(!this.checkBounds(toColumn, toRow)){
			throw new OutOfBoundsException();
		}

		if(this.checkOccupancy(toColumn, toRow)){
			throw new IllegalMoveException("Field is occupied.");
		}

		int moveValidity = this.checkMoveValidity(fromColumn, fromRow, toColumn, toRow);
		if((this.currentPlayer == Player.GOAT && moveValidity != 1) ||
				(this.currentPlayer == Player.TIGER && moveValidity < 1)){
			throw new IllegalMoveException("Cannot move in this direction");
		}

		if(this.currentPlayer == Player.GOAT){
			if(this.getNumGoatsAvailable() > 0){
				throw new IllegalMoveException("All remaining goats have to be placed before they can be moved.");
			}
			// all checks passed, place goat;
			this.moveGoat(fromColumn, fromRow, toColumn, toRow);
			this.currentPlayer = Player.TIGER;
		}else{
			this.moveTiger(fromColumn, fromRow, toColumn, toRow, (moveValidity == 2));
			this.currentPlayer = Player.GOAT;
		}
	}


	/**
	 * Simple check to see if provided coordinated are within the bounds of the game board.
	 */
	private boolean checkBounds(int column, int row){
		return column >= 0 && column < 5 && row >= 0 && row < 5;
	}

	/**
	 * Counts and sums, for every Tiger in the game, all possible moves, to help determine game-over for tiger.
	 */
	private int countPossibleTigerMoves(){
		int numPossibleMoves = 0;
		for (Tiger tiger : this.tigers){
			numPossibleMoves += this.countPossibleMoves(tiger.getColumn(), tiger.getRow(), Tiger.maxDistance);
		}
		return numPossibleMoves;
	}

	/**
	 * Counts the theoretical possible moves of a figure with a given maximum travel distance (usually 1). Does not
	 * check if it can jump, only if there are free fields.
	 * @param fromColumn column of figure on board
	 * @param fromRow row of figure on board
	 * @return the absolute maximum number of possible moves
	 */
	private int countPossibleMoves(int fromColumn, int fromRow, int max_distance){
		int numPossibleMoves = 0;
		for (int x = -max_distance; x <= max_distance; x++){
			int dst_x = fromColumn + x;
			if(dst_x >= 0 && dst_x < 5){
				for (int y = -max_distance; y <= max_distance; y++){
					int dst_y = fromRow + y;
					if(this.checkBounds(dst_x, dst_y)){
						if(this.checkMoveValidity(fromColumn, fromRow, dst_x, dst_y) > 0 && !this.checkOccupancy(dst_x, dst_y)){
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
	 * @param fromColumn Column of figure to move
	 * @param fromRow Row of figure to move
	 * @param toColumn Column to move figure to
	 * @param toRow Row to move figure to
	 * @return number of hops of travel are possible (0: none, 1: move, 2: jump)
	 */
	private int checkMoveValidity(int fromColumn, int fromRow, int toColumn, int toRow){
		return (BagChalGame.validMoves[fromRow*5 + fromColumn][toRow*5 + toColumn]);
	}

	/**
	 * checks and returns the type of occupant on the given field.
	 * @param column Column of field
	 * @param row Row of field
	 * @return {@link Player} GOAT, TIGER or null if empty.
	 */
	private Player getOccupancyType(int column, int row){
		Player occupant = null;
		for(Goat goat : this.goats){
			if( goat != null && !goat.isEaten() && goat.getColumn() == column && goat.getRow() == row){
				occupant = Player.GOAT;
				break; // no need to search further, as we just found a goat on this position
			}
		}
		for(Tiger tiger : this.tigers){
			if( tiger != null && tiger.getColumn() == column && tiger.getRow() == row){
				occupant = Player.TIGER;
				break; // no need to search further, as we just found a tiger on this position
			}
		}
		return occupant;
	}

	/**
	 * checks if a field is empty.
	 * @param column Column of field
	 * @param row Row of field
	 * @return boolean true if not empty.
	 */
	private boolean checkOccupancy(int column, int row){
		return this.getOccupancyType(column, row) != null;
	}

	/**
	 * Places a goat to the specified cordinates - does not check if it can.
	 * @param column Column of field to place on
	 * @param row Row of field to place on
	 * @return  true if successfull
	 * */
	private boolean placeGoat(int column, int row){
		for (int i = 0; i < this.goats.length; i++){
			if(this.goats[i] == null){
				this.goats[i] = new Goat(column, row);
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves a goat from the specified location to the destination - no checks for validity.
	 * @param fromColumn Column of figure to move
	 * @param fromRow Row of figure to move
	 * @param toColumn Column to move figure to
	 * @param toRow Row to move figure to
	 * */
	private void moveGoat(int fromColumn, int fromRow, int toColumn, int toRow) {
		Goat goat = this.findGoat(fromColumn, fromRow);
		if(goat == null){
			throw new IllegalMoveException("No goat at position "+fromColumn+"x"+fromRow);
		}
		goat.move(toColumn, toRow);
	}

	/**
	 * Moves a tiger from the specified location to the destination. Contains checks if the Tiger is found at the
	 * location and a jump is valid. Does not check whether the from/to coordinates are valid.
	 * @param fromColumn Column of figure to move
	 * @param fromRow Row of figure to move
	 * @param toColumn Column to move figure to
	 * @param toRow Row to move figure to
	 * @param jump whether to jump or not.
	 * */
	private void moveTiger(int fromColumn, int fromRow, int toColumn, int toRow, boolean jump){
		Tiger tiger = this.findTiger(fromColumn, fromRow);
		if(tiger == null){
			throw new IllegalMoveException("No tiger at position "+fromColumn+","+fromRow);
		}

		if(jump){
			int jumpedColumn = fromColumn + (toColumn - fromColumn)/2;
			int jumpedRow = fromRow + (toRow - fromRow)/2;
			Player jump_occupant = this.getOccupancyType(jumpedColumn, jumpedRow);

			if(jump_occupant == Player.GOAT){
				// eat it!
				this.eatGoat(jumpedColumn, jumpedRow);
			}else if(jump_occupant == Player.TIGER){
				throw new IllegalMoveException("Cannot jump over another tiger ("+jumpedColumn+","+jumpedRow+").");
			}else if(jump_occupant == null){
				throw new IllegalMoveException("Cannot jump over empty field ("+jumpedColumn+","+jumpedRow+").");
			}
		}
		tiger.move(toColumn, toRow);
	}

	/**
	 * finds and returns a tiger by coordinates
	 * @param column Column of field to place on
	 * @param row Row of field to place on
	 * @return  Tiger if found, else null
	 */
	private Tiger findTiger(int column, int row){
		for (Tiger tiger : this.tigers){
			if(tiger.getColumn() == column && tiger.getRow() == row){
				return tiger;
			}
		}
		return null;
	}

	/**
	 * finds and returns a goat by coordinates
	 * @param column Column of field to place on
	 * @param row Row of field to place on
	 * @return  Goat if found, else null
	 */
	private Goat findGoat(int column, int row){
		for (Goat goat : this.goats){
			if(goat != null && !goat.isEaten() && goat.getColumn() == column && goat.getRow() == row){
				return goat;
			}
		}
		return null;
	}

	/**
	 * Feed the tiger - takes coordinates of a goat jumped by a tiger and sets it's status to eaten.
	 * @param column Column of field of goat to be eaten
	 * @param row Row of field of goat to be eaten
	 * @return  true if goat was found and eaten, else false
	 */
	private boolean eatGoat(int column, int row){
		Goat goat = this.findGoat(column, row);
		if(goat != null){
			goat.getEaten();
			return true;
		}else return false;
	}
}
