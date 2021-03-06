package org.bawe.bagchal;

/**
 * Created by stephan on 1/13/17.
 */
class Goat extends Figure {
    private boolean eaten = false;
    public static int maxInstances = 20;
    public static int maxDistance = 1;

    /**
     * Instantiates a new Goat with initial placement.
     * @param column Column to place goat on
     * @param row Row to place goat on
     */
    Goat(int column, int row){
        super(column, row);
    }

    /**
     * Returns status of the goat (animal or food)
     * @return true is it has been eaten (and should be hidden from the board)
     */
    boolean isEaten(){
        return this.eaten;
    }

    /**
     * Converts goat from animal to tigerfood.
     */
    void getEaten(){
        this.eaten = true;
    }
}
