package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Worm worm;
    private Apple apple;
    private Random random;
    private Updatable updatable;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);
        
        this.worm = new Worm(width/2, height/2, Direction.DOWN);
        this.random = new Random();
        while(true) {
            this.apple = new Apple(this.random.nextInt(width), this.random.nextInt(height));
            if(!this.worm.runsInto(this.apple)) {
                break;
            }
        }
    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        this.worm.move();
        
        if(this.worm.runsInto(this.apple)) {
            this.worm.grow();
            
            setNewApple();
        }
        
        if(this.worm.runsIntoItself()) {
            this.continues = false;
        }
        Piece head = this.worm.getPieces().get(this.worm.getPieces().size() - 1);
        if(head.getX() <= 0 || head.getX() >= this.width) {
            this.continues = false;
        }
        if(head.getY() <= 0 || head.getY() >= this.height) {
            this.continues = false;
        }
        
        this.updatable.update();
        
        setDelay(1000 / this.worm.getLength());
    }

    public Worm getWorm() {
        return this.worm;
    }
    
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    
    public Apple getApple() {
        return this.apple;
    }
    
    public void setApple(Apple apple) {
        this.apple = apple;
    }
    
    public void setNewApple() {
        while(true) {
            this.apple = new Apple(this.random.nextInt(width), this.random.nextInt(height));
            if(!this.worm.runsInto(this.apple)) {
                break;
            }
        }
    }
}
