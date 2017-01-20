package org.bawe.bagchal;

import junit.framework.TestCase;
/**
 * Tests for Tiger
 * @Author Stephan Westphal
 */
public class TigerTests extends TestCase{
    public void testTigerConstructor(){
        Tiger tiger = new Tiger(1, 2);
        assertTrue(tiger != null);
        assertEquals(tiger.getColumn(), 1);
        assertEquals(tiger.getRow(), 2);
    }

    public void testTigerMove(){
        Tiger tiger = new Tiger(1, 2);
        assertTrue(tiger != null);
        assertEquals(tiger.getColumn(), 1);
        assertEquals(tiger.getRow(), 2);
        tiger.move(4,3);
        assertEquals(tiger.getColumn(), 4);
        assertEquals(tiger.getRow(), 3);
    }
}
