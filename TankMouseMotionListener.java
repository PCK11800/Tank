import java.awt.event.*;

public class TankMouseMotionListener implements MouseMotionListener{

    Tank tank;
    GamePanel panel;
    int mouseX, mouseY;

    public TankMouseMotionListener(Tank tank, GamePanel panel){
        this.tank = tank;
        this.panel = panel;
    }

    public void mouseMoved(MouseEvent e){
        mouseX = e.getXOnScreen();
        mouseY = e.getYOnScreen();

        double dx = tank.getHull().getxPos() - mouseX;
        double dy = tank.getHull().getyPos() - mouseY;
        double inRads = Math.atan2(dy, dx);

        if(inRads < 0){
            inRads = Math.abs(inRads);
        }
        else{
            inRads = 2 * Math.PI - inRads;
        }

        tank.getTurret().rotateObject(- Math.toDegrees(inRads) - 90);
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
}