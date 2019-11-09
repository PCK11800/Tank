import javax.swing.*;
import java.awt.MouseInfo;

public class TankTurret extends RotatingObject{
    
    private TankHull connectedTankHull;

    public TankTurret(TankHull connectedTankHull, int width, int height){
        this.width = width;
        this.height = height;
        this.xPos = connectedTankHull.getxPos();
        this.yPos = connectedTankHull.getyPos();
        this.connectedTankHull = connectedTankHull;
        setBounds(xPos, yPos, width, height);
        iniObject();
    }

    public void setLocation(){
        this.xPos = connectedTankHull.getxPos();
        this.yPos = connectedTankHull.getyPos();
        setxPos(this.xPos);
        setyPos(this.yPos);
        repaint();
    }

    public void activateTurretRotation(){
        int mouseXPos = MouseInfo.getPointerInfo().getLocation().x;
        int mouseYPos = MouseInfo.getPointerInfo().getLocation().y;

        double dx = connectedTankHull.getxPos() - mouseXPos;
        double dy = connectedTankHull.getyPos() - mouseYPos;

        double inRads = Math.atan2(dy, dx);

        if(inRads < 0){
            inRads = Math.abs(inRads);
        }
        else{
            inRads = 2 * Math.PI - inRads;
        }

        rotateObject(- Math.toDegrees(inRads) - 90);
    }
}