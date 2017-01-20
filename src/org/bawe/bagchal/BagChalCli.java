package org.bawe.bagchal;


/**
 * CLI Version of Bagchal.
 * Initializes View, Controller and Model (Game).
 */
public class BagChalCli {

    public static void main(String[] args) {
	// write your code here
        BagChalView cli = new BagChalView();
        BagChalGame game = new BagChalGame();

        BagChalController controller = new BagChalController(cli, game);

        controller.runGame();
    }

}
