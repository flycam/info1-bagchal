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
     * @param x Column to place tiger on
     * @param y Row to place tiger on
     */
    Tiger(int x, int y){
        super(x, y);
    }

}
