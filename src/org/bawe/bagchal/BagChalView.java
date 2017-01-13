package org.bawe.bagchal;

/**
 * Created by stephan on 1/12/17.
 */
public class BagChalView {

    public BagChalView(){
        this.printWelcome();
    }

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
     * Renders the board for cli output
     */
    public void renderBoard(char[][] board){
        char[][] outputBoard = {
                {'+','-','+','-','+','-','+','-','+'},
                {'|','\\','|','/','|','\\','|','/','|'},
                {'+','-','+','-','+','-','+','-','+'},
                {'|','\\','|','/','|','\\','|','/','|'},
                {'+','-','+','-','+','-','+','-','+'},
                {'|','\\','|','/','|','\\','|','/','|'},
                {'+','-','+','-','+','-','+','-','+'},
                {'|','\\','|','/','|','\\','|','/','|'},
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

    public void printGameStatus(int numGoatsEaten, int numGoatsAvailable, char currentPlayer){
        System.out.println("Number of Goats eaten: "+numGoatsEaten);
        System.out.println("Number of Goat to set: "+numGoatsAvailable);
        System.out.println("It's "+ ((currentPlayer == 'G') ? "Goat's":"Tiger's") + " turn.");
    }

    public void askForValidInput(){
        System.out.println("Please enter move in this format: 1234 (from_x,from_y,to_x,to_y)");
    }
}
