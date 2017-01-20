package org.bawe.bagchal;

/**
 * Created by stephan on 1/20/17.
 */
class OutOfBoundsException extends IllegalArgumentException{
    public OutOfBoundsException(){
        super();
    }
}

class IllegalMoveException extends IllegalArgumentException{
    public IllegalMoveException(String message){
        super(message);
    }
}