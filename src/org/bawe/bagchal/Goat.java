package org.bawe.bagchal;

/**
 * Created by stephan on 1/13/17.
 */
class Goat extends Figure {
    private boolean eaten = false;

    Goat(int x, int y){
        super(x, y);
    }

    boolean isEaten(){
        return this.eaten;
    }

    void getEaten(){
        this.eaten = true;
    }
}
