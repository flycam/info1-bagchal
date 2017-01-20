package org.bawe.bagchal;

/**
 * Base class for game figures containing basic movement & positioning.
 * @Author Stephan Westphal
 */
abstract class Figure {
    /**
     * Position of Figure on board. X=Column, Y=Row.
     */
    protected int column;
    protected int row;

    public static int maxInstances;
    public static int maxDistance;

    /**
     * Creates and placed a new game figure.
     * @param column Column to place figure on
     * @param row Row to place figure on
     */
    Figure(int column, int row){
        this.column = column;
        this.row = row;
    }

    /**
     * @return column on board.
     */
    int getColumn(){
        return this.column;
    }

    /**
     * @return row on Board
     */
    int getRow(){
        return this.row;
    }

    /**
     * Moves the position of the figure.
     * No checking of valid coordinates, as all objects of this class will only live inside the game class.
     */
    void move(int x, int y){
        this.column = x;
        this.row = y;
    }
}
