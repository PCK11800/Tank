import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankKeyListener implements KeyListener{

    Tank tank;
    GamePanel panel;
    
    private ArrayList<Integer> keysDown = new ArrayList<>();

    public TankKeyListener(Tank tank, GamePanel panel){
        this.tank = tank;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(!keysDown.contains(e.getKeyCode())){
            keysDown.add(new Integer(e.getKeyCode()));
        }
        processInput();
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){
        keysDown.remove(new Integer(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e){
        //DO NOTHING
    }

    public void processInput(){
        for(int keys : keysDown){
            if(keysDown.contains(new Integer(KeyEvent.VK_W))){
                tank.getHull().goForward();
                tank.getTurret().setLocation();
            }
            if(keysDown.contains(new Integer(KeyEvent.VK_A))){
                tank.getHull().turnLeft();
                tank.getTurret().setLocation();
            }
            if(keysDown.contains(new Integer(KeyEvent.VK_S))){
                tank.getHull().goBackward();
                tank.getTurret().setLocation();
            }
            if(keysDown.contains(new Integer(KeyEvent.VK_D))){
                tank.getHull().turnRight();
                tank.getTurret().setLocation();
            }
        }
    }
}
