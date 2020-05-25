/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

/**
 *
 * @author Colin
 */
/*
    This is the base class inherited by the Apple and Worm that establishes baseline information
    and allows for testing if two pieces collide on the board.
 */
public class Piece {
    private int x;
    private int y;
    
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    /*
    runsInto is used for validating the main gameplay loop of the snake game of the snake eating the apple.
    It is also used to verify that a newly generated apple is not being placed in the path the snake is already in.
     */
    public boolean runsInto(Piece piece) {
        if(piece.getX() == this.x && piece.getY() == this.y) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
