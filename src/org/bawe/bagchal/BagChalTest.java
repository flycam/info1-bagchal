package org.bawe.bagchal;

import junit.framework.TestCase;

/**
 * Created by stephan on 1/13/17.
 */
public class BagChalTest extends TestCase{
    public void testGetNumGoatsEaten(){
        BagChal game = new BagChal();
        assertTrue(game.getNumGoatsEaten() == 0);
    }

    public void testGetBoard() {

    }

}