import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TankMouseListener implements MouseListener{

    Tank tank;
    GamePanel panel;

    //Contains all the shells
    ArrayList<Shell> shellList;

    public TankMouseListener(Tank tank, GamePanel panel){
        this.tank = tank;
        this.panel = panel;
        return;
    }

    public void setShellList(ArrayList<Shell> shellList){
        this.shellList = shellList;
    }

    //MAKE SURE SHELL SPEED IS NOT DIVISIBLE BY WIDTH OR HEIGHT
    private void createShell(){
        Shell shell = new Shell(tank.getTurret(), 10, 15, 9);
        shell.setRicochetNum(1);
        shellList.add(shell);
        panel.add(shell, 3);
        panel.repaint();
    }
    
    public void mousePressed(MouseEvent e){
        createShell();
    }

    public void mouseReleased(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

}