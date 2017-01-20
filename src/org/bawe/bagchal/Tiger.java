package org.bawe.bagchal;

/**
 * Tiger class for game figures containing specifics for Tiger.
 * @Author Stephan Westphal
 */
class Tiger extends Figure{
    public static int maxInstances = 4;
    public static int maxDistance = 2;

    /**
     * Instantiates a new Tiger with initial placement.
     * @param column Column to place tiger on
     * @param row Row to place tiger on
     */
    Tiger(int column, int row){
        super(column, row);
    }

}
