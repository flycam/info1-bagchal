package org.bawe.bagchal;

import junit.framework.TestCase;
/**
 * Tests for Goat
 * @author Stephan Westphal
 */
public class GoatTests extends TestCase{
    public void testGoatConstructor(){
        Goat goat = new Goat(1, 2);
        assertTrue(goat != null);
        assertEquals(goat.getColumn(), 1);
        assertEquals(goat.getRow(), 2);
    }

    public void testGoatMove(){
        Goat goat = new Goat(1, 2);
        assertTrue(goat != null);
        assertEquals(goat.getColumn(), 1);
        assertEquals(goat.getRow(), 2);
        goat.move(4,3);
        assertEquals(goat.getColumn(), 4);
        assertEquals(goat.getRow(), 3);
    }

    public void testGoatEat(){
        Goat goat = new Goat(1, 2);
        assertTrue(!goat.isEaten());
        goat.getEaten();
        assertTrue(goat.isEaten());
    }
}
