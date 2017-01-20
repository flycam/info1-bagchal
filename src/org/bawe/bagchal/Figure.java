package org.bawe.bagchal;

/**
 * Created by stephan on 1/13/17.
 */
abstract class Figure {
    protected int pos_x;
    protected int pos_y;

    public static int maxInstances;
    public static int maxDistance;

    Figure(int x, int y){
        this.pos_x = x;
        this.pos_y = y;
    }

    int getPos_x(){
        return this.pos_x;
    }
    int getPos_y(){
        return this.pos_y;
    }
    void move(int x, int y){
        this.pos_x = x;
        this.pos_y = y;
    }
}
