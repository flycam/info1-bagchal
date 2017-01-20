package org.bawe.bagchal;

import junit.framework.TestCase;

/**
 * Created by stephan on 1/13/17.
 */
public class BagChalModelTests extends TestCase{

    public void testConstructor(){
        BagChalGame game = new BagChalGame();
        assertTrue(game != null);
    }

    public void testGetCurrentPlayer(){
        BagChalGame game = new BagChalGame();
        assertTrue(game.getCurrentPlayer() == Player.GOAT);
        game.placeFigure(1,1);
        assertTrue(game.getCurrentPlayer() == Player.TIGER);
    }

    public void testGetNumGoatsAvailable(){
        BagChalGame game = new BagChalGame();
        assertTrue(game.getNumGoatsAvailable() == Goat.maxInstances);
        game.placeFigure(1,1);
        assertTrue(game.getNumGoatsAvailable() == Goat.maxInstances-1);
    }

    public void testGetNumGoatsEaten(){
        BagChalGame game = new BagChalGame();
        assertTrue(game.getNumGoatsEaten() == 0);
        game.placeFigure(1,1);
        game.moveFigure(0,0,2,2);
        assertTrue(game.getNumGoatsEaten() == 1);
    }

    public void testGetBoard() {
        BagChalGame game = new BagChalGame();
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
        BagChalGame game = new BagChalGame();
        assertTrue(game.getBoard()[0][0] == 'T');
        assertTrue(game.getBoard()[2][2] == 0);
        game.placeFigure(1,1);
        game.moveFigure(0,0,2,2);
        assertTrue(game.getBoard()[0][0] == 0);
        assertTrue(game.getBoard()[2][2] == 'T');
    }

    public void testPlaceFigure(){
        BagChalGame game = new BagChalGame();
        game.placeFigure(1,1);
        assertTrue(game.getBoard()[1][1] == 'G');
    }

    public void testGetWinner(){
        BagChalGame game = new BagChalGame();
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