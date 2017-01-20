package org.bawe.bagchal;

import junit.framework.TestCase;

/**
 * Created by stephan on 1/13/17.
 */
public class BagChalModelTests extends TestCase{

    public void testConstructor(){
        BagChal game = new BagChal();
        assertTrue(game != null);
    }

    public void testGetCurrentPlayer(){
        BagChal game = new BagChal();
        assertTrue(game.getCurrentPlayer() == Player.GOAT);
        game.placeFigure(1,1);
        assertTrue(game.getCurrentPlayer() == Player.TIGER);
    }

    public void testGetNumGoatsAvailable(){
        BagChal game = new BagChal();
        assertTrue(game.getNumGoatsAvailable() == Goat.maxInstances);
        game.placeFigure(1,1);
        assertTrue(game.getNumGoatsAvailable() == Goat.maxInstances-1);
    }

    public void testGetNumGoatsEaten(){
        BagChal game = new BagChal();
        assertTrue(game.getNumGoatsEaten() == 0);
        game.placeFigure(1,1);
        game.moveFigure(0,0,2,2);
        assertTrue(game.getNumGoatsEaten() == 1);
    }

    public void testGetBoard() {
        BagChal game = new BagChal();
        game.placeFigure(1,1);
        assertTrue(game.getBoard()[0][0] == 'T');
        assertTrue(game.getBoard()[4][0] == 'T');
        assertTrue(game.getBoard()[0][4] == 'T');
        assertTrue(game.getBoard()[4][4] == 'T');
        assertTrue(game.getBoard()[1][1] == 'G');
        assertTrue(game.getBoard()[2][2] == 0);
        assertTrue(game.getBoard()[0][3] == 0);
    }

    public void testMoveFigure(){
        BagChal game = new BagChal();
        assertTrue(game.getBoard()[0][0] == 'T');
        assertTrue(game.getBoard()[2][2] == 0);
        game.placeFigure(1,1);
        game.moveFigure(0,0,2,2);
        assertTrue(game.getBoard()[0][0] == 0);
        assertTrue(game.getBoard()[2][2] == 'T');
    }

    public void testPlaceFigure(){
        BagChal game = new BagChal();
        game.placeFigure(1,1);
        assertTrue(game.getBoard()[1][1] == 'G');
    }

    public void testGetWinner(){
        BagChal game = new BagChal();
        assertTrue(game.getWinner() == null);
        game.placeFigure(1,1);
        game.moveFigure(0,0,2,2); // eats 1,1

        game.placeFigure(3,0);
        game.moveFigure(4,0,2,0); // eats 3,0
        assertTrue(game.getWinner() == null);

        game.placeFigure(1,4);
        game.moveFigure(0,4,2,4); // eats 1,4

        game.placeFigure(1,4);
        game.moveFigure(2,4,0,4); // eats 1,4
        assertTrue(game.getWinner() == null);

        game.placeFigure(3,4);
        game.moveFigure(4,4,2,4); // eats 3,4
        assertTrue(game.getWinner() == Player.TIGER);
    }
}