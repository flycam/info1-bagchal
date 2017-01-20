package org.bawe.bagchal;

/**
 * BagChal Controller, controlls the flow of the game with this CLI implementation.
 * @Author Stephan Westphal
 */
public class BagChalController {
    /**
     * Current game, will be initialized for each game to play.
     */
    private BagChal game;

    /**
     * View. Will be passed from Main depending on the user interface.
     */
    private BagChalView view;

    /**
     * input class to read from cli standard-input.
     */
    private Input input;

    /**
     * Constructor - requires a valid view and newly initialized game object.
     */
    public BagChalController(BagChalView view, BagChal game){
        this.view = view;
        this.game = game;
        this.input = new Input();
    }

    /**
     * runs the game and returns after one party wins.
     */
    public void runGame(){
        Player winner;
        do{
            Player currentPlayer = this.game.getCurrentPlayer();
            int numGoatsAvailable = this.game.getNumGoatsAvailable();
            this.view.renderBoard(this.game.getBoard());
            this.view.printGameStatus(this.game.getNumGoatsEaten(), numGoatsAvailable, currentPlayer);

            int entryLength = (currentPlayer == Player.GOAT && numGoatsAvailable > 0) ? 2 : 4;

            this.view.askForInput(entryLength);

            this.waitForValidEntry(entryLength);

            winner = this.game.getWinner();
            //winner = null;
        }while(winner == null);

        this.view.renderBoard(this.game.getBoard());
        this.view.printWinner(winner);
    }

    /**
     * Blocking-method to wait for input, check if and handle exceptions thrown from the game Model implementing the
     * game logic.
     */
    private void waitForValidEntry(int entryLength){
        boolean waiting;
        do{
            waiting = true;
            String line = this.input.readLine();

            if(line.length() == entryLength){
                // parse
                int[] entry= this.input.parseIntArray(line);

                try{
                    if(entryLength == 4){
                        this.game.moveFigure(entry[0], entry[1], entry[2], entry[3]);
                    }else{
                        this.game.placeFigure(entry[0], entry[1]);
                    }
                    waiting = false;
                }catch(IllegalMoveException e){
                    this.view.printMoveError(e.getMessage());
                    this.view.askForInput(entryLength);
                }catch(OutOfBoundsException e){
                    this.view.printOutOfBoundsError();
                    this.view.askForValidInput(entryLength);
                }
            }else{
                this.view.askForValidInput(entryLength);
            }
        }while(waiting);
    }
}
