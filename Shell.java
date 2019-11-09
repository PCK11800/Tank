import javax.swing.*;
import java.awt.Color;

public class Shell extends RotatingObject{

    //TankShellIcon.png size = 10 x 15

    private int shellSpeed;
    private int ricochetNum;
    private TankTurret connectedTankTurret;

    public Shell(TankTurret connectedTankTurret, int width, int height){
        this.connectedTankTurret = connectedTankTurret;
        this.width = width;
        this.height = height;
        this.xPos = getTankTurretCenter_x();
        this.yPos = getTankTurretCenter_y();
        setBounds(xPos, yPos, width, height);
        iniObject();
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
        objectDirection = connectedTankTurret.getObjectDirection();
        setxPos(getxPos() + (int)(shellSpeed * Math.sin(Math.toRadians(objectDirection))));
        setyPos(getyPos() - (int)(shellSpeed * Math.cos(Math.toRadians(objectDirection))));
    }

    public void setShellSpeed(int shellSpeed){
        this.shellSpeed = shellSpeed;
    }

    public void update(){
        launchedForward();
    }

}