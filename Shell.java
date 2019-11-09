import javax.swing.*;
import java.awt.Color;

public class Shell extends RotatingObject{

    //TankShellIcon.png size = 10 x 15

    private int shellSpeed;
    private int ricochetNum;
    private TankTurret connectedTankTurret;

    public Shell(TankTurret connectedTankTurret, int width, int height, int shellSpeed){
        this.connectedTankTurret = connectedTankTurret;
        this.width = width;
        this.height = height;
        this.shellSpeed = shellSpeed;
        this.xPos = getTankTurretCenter_x();
        this.yPos = getTankTurretCenter_y();
        setBounds(xPos, yPos, width, height);
        iniObject();

        this.objectDirection = connectedTankTurret.getObjectDirection();
        setImage("TankShellIcon.png");
        
        if(getObjectDirection() >= 360){
            setObjectDirection(getObjectDirection() - 360);
        }
        if(getObjectDirection() <= 0){
            setObjectDirection(getObjectDirection() + 360);
        }
    }

    public int getTankTurretCenter_x(){
        int tankTurretCenter_x = ((connectedTankTurret.getWidth()/2 + connectedTankTurret.getxPos()) - width/2);
        return tankTurretCenter_x;
    }

    public int getTankTurretCenter_y(){
        int tankTurretCenter_y = ((connectedTankTurret.getHeight()/2 + connectedTankTurret.getyPos()) - height/2);
        return tankTurretCenter_y;
    }

    public void launchedForward(){
        setxPos(getxPos() + (int)(shellSpeed * Math.sin(Math.toRadians(objectDirection))));
        setyPos(getyPos() - (int)(shellSpeed * Math.cos(Math.toRadians(objectDirection))));
        repaint();
    }

    public void setShellSpeed(int shellSpeed){
        this.shellSpeed = shellSpeed;
    }

    public int getShellSpeed(){
        return shellSpeed;
    }

    public void update(){
        launchedForward();
    }

}