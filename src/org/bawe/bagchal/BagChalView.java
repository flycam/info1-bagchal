package org.bawe.bagchal;

/**
 * View of BagChal game for CLI-use.
 * @Author Stephan Westphal
 */
public class BagChalView {

    /**
     * Constructor of view starts with printing the logo. Everything else will be called from the controller.
     */
    public BagChalView(){
        this.printWelcome();
    }


    /**
     * Shows Game logo
     */
    public void printWelcome(){
        System.out.println("\n" +
                "   ___               ___ _           _ \n" +
                "  / __\\ __ _  __ _  / __\\ |__   __ _| |\n" +
                " /__\\/// _` |/ _` |/ /  | '_ \\ / _` | |\n" +
                "/ \\/  \\ (_| | (_| / /___| | | | (_| | |\n" +
                "\\_____/\\__,_|\\__, \\____/|_| |_|\\__,_|_|\n" +
                "             |___/                     ");

        System.out.println("");
    }

    /**
     * Renders the board for cli output.
     * @param board accepts a 2-dimensional array[row][column]. A value of 0 denotes an empty field.
     */
    public void renderBoard(char[][] board){
        char[][] outputBoard = {
                {'+','-','+','-','+','-','+','-','+'},
                {'|','\\','|','/','|','\\','|','/','|'},
                {'+','-','+','-','+','-','+','-','+'},
                {'|','/','|','\\','|','/','|','\\','|'},
                {'+','-','+','-','+','-','+','-','+'},
                {'|','\\','|','/','|','\\','|','/','|'},
                {'+','-','+','-','+','-','+','-','+'},
                {'|','/','|','\\','|','/','|','\\','|'},
                {'+','-','+','-','+','-','+','-','+'}
        };
        for(int i = 0; i < board.length; i++){
            char[] row = board[i];
            for(int j = 0; j < row.length; j++){
                char field = row[j];
                if(field != 0){
                    outputBoard[2*i][2*j] = field;
                }
            }
        }
        for (char[] outputRow : outputBoard) {
            System.out.println(outputRow);
        }
    }

    /**
     * Prints the current game status (# goats eaten/available to place and current player)
     * @param numGoatsEaten
     * @param numGoatsAvailable
     * @param {@link Player} current player
     */
    public void printGameStatus(int numGoatsEaten, int numGoatsAvailable, Player currentPlayer){
        System.out.println("Number of Goats eaten: "+numGoatsEaten);
        System.out.println("Number of Goat to set: "+numGoatsAvailable);
        System.out.println("It's "+ ((currentPlayer == Player.GOAT) ? "Goat's":"Tiger's") + " turn.");
    }

    /**
     * Prints the winner of the game.
     * @param {@link Player} winning player
     */
    public void printWinner(Player winner){
        System.out.println("");
        System.out.println("**** Game over. " + ((winner == Player.GOAT) ? "Goat": "Tiger") + " won. ****");
    }

    /**
     * Prints an error message related to an invalid move.
     * @param message (String) to describe the wrong move.
     */
    public void printMoveError(String message){
        System.out.println("!!! Invalid Move: "+message);
    }

    /**
     * Prints an error message related to out-of-bounds coordinates.
     */
    public void printOutOfBoundsError(){
        System.out.println("!!! Move was out of bounds, please choose coordinates between 0 and 4.");
    }

    /**
     * Asks for coordinated for the next move.
     * @param length of expected input (2 or 4 digits)
     */
    public void askForInput(int entryLength){
        System.out.print("Please enter coordinates ("+ entryLength +" Digits): ");
    }

    /**
     * Asks for valid coordinated for the next move. Usually called when there was an error with the first try.
     * @param length of expected input (2 or 4 digits)
     */
    public void askForValidInput(int entryLength){
        System.out.print("Please enter your move in the format ");
        if(entryLength == 4){
            System.out.print("1234 (from column, from row, to column,to row): ");
        }else{
            System.out.print("12 (to column, to row): ");
        }
    }
}
