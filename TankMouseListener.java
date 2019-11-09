import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TankMouseListener implements MouseListener{

    Tank tank;
    GamePanel panel;

    public TankMouseListener(Tank tank, GamePanel panel){
        this.tank = tank;
        this.panel = panel;
        return;
    }
    
    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseClicked(MouseEvent e){
        
    }
}