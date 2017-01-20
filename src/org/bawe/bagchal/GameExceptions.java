package org.bawe.bagchal;

/**
 * Exceptions to pass Input errors back to the Controller
 * @Author Stephan Westphal
 */

/**
 * Exception to use when the provided coordinates are out-of-bounds.
 */
class OutOfBoundsException extends IllegalArgumentException{
    public OutOfBoundsException(){
        super();
    }
}

/**
 * Exception to use for all kinds of game rule violations, passing a short reason for error back.
 */
class IllegalMoveException extends IllegalArgumentException{
    public IllegalMoveException(String message){
        super(message);
    }
}

/**
 * Exception to use when Game is over but Player wants to make another move. Mostly needed if the model is used with
 * someone else's controller.
 */
class GameOverException extends IllegalArgumentException{
    public GameOverException(){
        super();
    }
}