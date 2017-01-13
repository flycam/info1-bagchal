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
        do{

            this.showState();

            while(this.waitForValidEntry()){

            }
        }while(!this.game.isOver());

        // print winner or tie
    }

    private void showState(){
        this.view.renderBoard(this.game.getBoard());
        this.view.printGameStatus(game.getNumGoatsEaten(), game.getNumGoatsAvailable(), game.getCurrentPlayer());
    }

    private boolean waitForValidEntry(){
        do{
            int move = this.input.readInt();
            if(move > 0 && move < 5555){

            }else{
                this.view.askForValidInput();
            }
        }while(true);

    }
}
