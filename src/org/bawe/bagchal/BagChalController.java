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
            Player currentPlayer = game.getCurrentPlayer();
            int numGoatsAvailable = game.getNumGoatsAvailable();
            this.view.renderBoard(this.game.getBoard());
            this.view.printGameStatus(game.getNumGoatsEaten(), numGoatsAvailable, currentPlayer);

            int entryLength = (currentPlayer == Player.GOAT && numGoatsAvailable > 0) ? 2 : 4;

            this.view.askForInput(entryLength);
            while(this.waitForValidEntry(entryLength)){
                int[] entry = {0,0,0,0};

                if(currentPlayer == Player.GOAT){
                    if(entryLength == 4){
                        this.game.moveGoat(entry[0], entry[1], entry[2], entry[3]);
                    }else{
                        this.game.setGoat(entry[0], entry[1]);
                    }
                }else{
                    this.game.moveTiger(entry[0], entry[1], entry[2], entry[3]);
                }
            }

            winner = this.game.getWinner();
        }while(winner == null);
        this.view.printWinner(winner);
    }

    private boolean waitForValidEntry(int entryLength){
        do{
            int move = this.input.readInt();
            if(move > 0 && move < 5555){

            }else{
                this.view.askForValidInput();
            }
        }while(true);

    }
}
