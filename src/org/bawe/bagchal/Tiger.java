package org.bawe.bagchal;

/**
 * Created by stephan on 1/13/17.
 */
class Tiger extends Figure{
    private int numGoatsEaten = 0;

    public static int maxInstances = 4;
    public static int maxDistance = 2;

    Tiger(int x, int y){
        super(x, y);
    }

    void eat(){
        this.numGoatsEaten++;
    }
}
