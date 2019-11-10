import java.util.ArrayList;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Point;

public class GameWindow extends JFrame{

    private int width, height;

    public GameWindow(int width, int height){
        this.width = width;
        this.height = height;

        setSize(this.width, this.height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setFocusable(true);
        requestFocusInWindow();

        setCustomCursor("");
    }

    public void setCustomCursor(String imageURL){
        try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(imageURL).getImage(),new Point(0,0),"custom cursor"));
        }catch(Exception e){}
    }

    
    public void initListeners(Tank tank, GamePanel panel, ArrayList<Shell> shellList){
        TankKeyListener tkl = new TankKeyListener(tank, panel);
        addKeyListener(tkl);

        TankMouseListener tm1 = new TankMouseListener(tank, panel);
        tm1.setShellList(shellList);
        addMouseListener(tm1);

        TankMouseMotionListener tmm1 = new TankMouseMotionListener(tank, panel);
        addMouseMotionListener(tmm1);
    }
}