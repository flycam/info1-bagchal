package org.bawe.bagchal;

public class BagChalCli {

    public static void main(String[] args) {
	// write your code here
        BagChalView cli = new BagChalView();
        BagChal game = new BagChal();

        BagChalController controller = new BagChalController(cli, game);

        controller.runGame();
    }

}
