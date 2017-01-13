package org.bawe.bagchal;

/**
 * Created by stephan on 1/13/17.
 */
public class Goat extends Figure {
    private boolean eaten = false;

    public Goat(int x, int y){
        super(x, y);
    }

    public boolean isEaten(){
        return this.eaten;
    }

    public void eat(){
        this.eaten = true;
    }
}
