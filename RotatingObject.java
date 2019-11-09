import javax.swing.*;
import java.lang.InterruptedException;
import java.awt.Image;

public class RotatingObject extends JLabel{

    /*
     * Creating a class that extends RotatingObject requires
     * - RotatingObject(xPos, yPos, width, height);
     * - setImage(ImageURL);
     * - setTurningDistance(turningDistance);
     */

    protected int xPos, yPos;
    protected int width, height;
    protected double objectDirection; //0 - 360
    protected double turningDistance;

    protected ImageIcon staticIcon;
    protected RotatedIcon objectIcon;

    public void iniObject(){
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setVisible(true);
    }

    public void setImage(String ImageURL){
        staticIcon = new ImageIcon(ImageURL);
        Image image = staticIcon.getImage();
        Image newimage = image.getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH);
        staticIcon.setImage(newimage);
        objectIcon = new RotatedIcon(staticIcon, objectDirection, false);
        setIcon(objectIcon);
    }

    public void sleep(long milli){
        try{
            Thread.sleep(milli);
        }catch(InterruptedException ie){
            System.out.println("Sleep is for the weak.");
        }
    }

    public void rotateObject(double objectDirection){
        this.objectDirection = objectDirection;
        objectIcon.setDegrees(this.objectDirection);
        repaint();
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
        setLocation(xPos, yPos);
        repaint();
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
        setLocation(xPos, yPos);
        repaint();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getObjectDirection() {
        return objectDirection;
    }

    public void setObjectDirection(double objectDirection) {
        this.objectDirection = objectDirection;
    }

    public double getTurningDistance() {
        return turningDistance;
    }

    public void setTurningDistance(double turningDistance) {
        this.turningDistance = turningDistance;
    }
    
}