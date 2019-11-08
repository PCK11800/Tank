import javax.swing.*;

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
}