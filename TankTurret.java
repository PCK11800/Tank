import javax.swing.*;
import java.awt.MouseInfo;

public class TankTurret extends RotatingObject{

    //TankTurretIcon.png size = 75 x 75
    
    private TankHull connectedTankHull;

    public TankTurret(TankHull connectedTankHull, int width, int height){
        this.connectedTankHull = connectedTankHull;
        this.width = width;
        this.height = height;
        this.xPos = getTankHullCenter_x();
        this.yPos = getTankHullCenter_y();
        setBounds(xPos, yPos, width, height);
        iniObject();
    }

    public void setTankHullLocation(){
        this.xPos = getTankHullCenter_x();
        this.yPos = getTankHullCenter_y();
        setxPos(this.xPos);
        setyPos(this.yPos);
        repaint();
    }

    public int getTankHullCenter_x(){
        int tankHullCenter_x = ((connectedTankHull.getWidth()/2 + connectedTankHull.getxPos()) - width/2);
        return tankHullCenter_x;
    }

    public int getTankHullCenter_y(){
        int tankHullCenter_y = ((connectedTankHull.getHeight()/2 + connectedTankHull.getyPos()) - width/2);
        return tankHullCenter_y;
    }

    private void activateTurretRotation(){
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

    public void update(){
        //activateTurretRotation();
    }
}