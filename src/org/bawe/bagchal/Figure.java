package org.bawe.bagchal;

/**
 * Base class for game figures containing basic movement & positioning.
 * @Author Stephan Westphal
 */
abstract class Figure {
    /**
     * Position of Figure on board. X=Column, Y=Row.
     */
    protected int pos_x;
    protected int pos_y;

    public static int maxInstances;
    public static int maxDistance;

    /**
     * Creates and placed a new game figure.
     * @param x Column to place figure on
     * @param y Row to place figure on
     */
    Figure(int x, int y){
        this.pos_x = x;
        this.pos_y = y;
    }

    /**
     * @return column on board.
     */
    int getPos_x(){
        return this.pos_x;
    }

    /**
     * @return row on Board
     */
    int getPos_y(){
        return this.pos_y;
    }

    /**
     * Moves the position of the figure.
     * No checking of valid coordinates, as all objects of this class will only live inside the game class.
     */
    void move(int x, int y){
        this.pos_x = x;
        this.pos_y = y;
    }
}
