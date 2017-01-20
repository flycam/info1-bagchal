package org.bawe.bagchal;

/**
 * Created by stephan on 1/12/17.
 */
public class BagChalController {
    private BagChal game;
    private BagChalView view;
    private Input input;

    public BagChalController(BagChalView view, BagChal game){
        this.view = view;
        this.game = game;
        this.input = new Input();
    }

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

            //winner = this.game.getWinner();
            winner = null;
        }while(winner == null);
        this.view.printWinner(winner);
    }

    private void waitForValidEntry(int entryLength){
        boolean waiting;
        do{
            waiting = true;
            String line = this.input.readLine();

            //ToDo: DEBUG below, remove!!!
            System.out.println("Input was '"+line+"' (length: "+line.length()+")");

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
