/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.List;
import java.util.ArrayList;
import wormgame.Direction;

/**
 *
 * @author Colin
 */

/*
A worm is a List of Piece objects
 */
public class Worm {
    private Direction direction;
    private List<Piece> pieces;
    private boolean growBool;
    
    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.direction = originalDirection;
        this.pieces = new ArrayList<Piece>();
        this.pieces.add(new Piece(originalX, originalY));
        this.growBool = false;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setDirection(Direction dir) {
        this.direction = dir;
    }
    
    public int getLength() {
        return pieces.size();
    }
    
    public List<Piece> getPieces() {
        return pieces;
    }

    /*
    The best way to imagine the movement is to not think of head and tail as they are often noted in Linked Lists, but
    instead think of an actual snake.

    The head of the snake is always noted by the last item in the list. Movement works by adding a new piece to the array
    and updating the coordinates of every item in the list. The newest piece is actually the head.

    When the snake grows the tail actually stops moving for a frame because a new piece is being added to the head.
    If the snake is growing, we do not remove the 0 index of the list as seen in the else statement of this function.
     */
    public void move() {
        if(this.getLength() < 3) {
            getNewPiece();
        } else {
            // if grow is true we do not remove one yet. grow will be toggled
            // and next time we move it will be off, so we will remove the 
            // first piece in the array.
            if(this.growBool) {
                getNewPiece();
                grow(); //toggles the growBool, in this case, back to false
            } else {
                getNewPiece();
                this.pieces.remove(0);
            }
        }
        System.out.println("move");
        pieces.stream()
                .forEach(System.out::println);

        
        
    }
    
    public void grow() {
        // At the beginning of the game the snake continues to grow until it is 3 pieces long.
        if(this.getLength() < 3) {
            return;
        }
        this.growBool = !this.growBool; //Toggles the value of growBool
    }

    /*
    getNewPiece gets the current coordinates for the head which is always the last item in the list
    and adds a new piece as the head.

    Keep in mind that top left of the window is 0,0.
     */
    public void getNewPiece() {
        int x = this.pieces.get(pieces.size() - 1).getX();
        int y = this.pieces.get(pieces.size() - 1).getY();
        System.out.println(String.format("Grow: %s %s", x, y));
        System.out.println(this.direction);
        switch(this.direction) {
            case UP:
                this.pieces.add(new Piece(x, y - 1));
                break;
            case DOWN:
                this.pieces.add(new Piece(x, y + 1));
                break;
            case LEFT:
                this.pieces.add(new Piece(x - 1, y ));
                break;
            case RIGHT:
                this.pieces.add(new Piece(x + 1, y));
                break;
        }
    }
    
    public boolean runsInto(Piece piece) {
        for(Piece worm : this.pieces) {
            if(worm.runsInto(piece)) {
                return true;
            }
        }
        return false;
    }

    public boolean runsIntoItself() {
        for(Piece a : this.pieces) {
            for(Piece b : this.pieces) {
                if(a == b) {
                    continue;
                }
                if(a.runsInto(b)) {
                    return true;
                }
            }
        }
        return false;
    }
}
