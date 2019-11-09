import javax.swing.*;
import java.awt.Color;

public class TankHull extends RotatingObject{

    //TankHullIcon.png = 75 x 75

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

    public void turnRight(){
        rotateObject(getObjectDirection() + turningDistance);

        if(getObjectDirection() >= 360){
            setObjectDirection(getObjectDirection() - 360);
        }
    }

    public void turnLeft(){
        rotateObject(getObjectDirection() - turningDistance);

        if(getObjectDirection() <= 0){
            setObjectDirection(getObjectDirection() + 360);
        }
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