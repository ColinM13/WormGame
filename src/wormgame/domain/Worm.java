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
    
    public void move() {
        if(this.getLength() < 3) {
            getNewPiece();
        } else {
            // if grow is true we do not remove one yet. grow will be toggled
            // and next time we move it will be off, so we will remove the 
            // first piece in the array.
            if(this.growBool) {
                getNewPiece();
                grow();
            } else {
                getNewPiece();
                this.pieces.remove(0);
            }
        }
        
        
    }
    
    public void grow() {
        // toggles the value of growBool between true and false
        if(this.getLength() < 3) {
            return;
        }
        this.growBool = !this.growBool;
    }
    
    public void getNewPiece() {
        int x = this.pieces.get(pieces.size() - 1).getX();
        int y = this.pieces.get(pieces.size() - 1).getY();
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
