import javax.swing.*;
import java.awt.Color;

public class TankHull extends RotatingObject{

    private int movementSpeed;

    public TankHull(int xPos, int yPos, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        setBounds(xPos, yPos, width, height);
        iniObject();
    }

    public void goForward(){
        setxPos(getxPos() + (int)(movementSpeed * Math.sin(Math.toRadians(objectDirection))));
        setyPos(getyPos() - (int)(movementSpeed * Math.cos(Math.toRadians(objectDirection))));
        repaint();
    }

    public void goBackward(){
        setxPos(getxPos() - (int)(movementSpeed * Math.sin(Math.toRadians(objectDirection))));
        setyPos(getyPos() + (int)(movementSpeed * Math.cos(Math.toRadians(objectDirection))));
        repaint();
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void update(){
        //Collision detection and other 
    }
}