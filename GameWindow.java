import java.util.ArrayList;
import javax.swing.*;

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
    }

    public void initListeners(Tank tank, GamePanel panel, ArrayList<Shell> shellList){
        TankKeyListener tkl = new TankKeyListener(tank, panel);
        addKeyListener(tkl);

        TankMouseListener tm1 = new TankMouseListener(tank, panel);
        tm1.setShellList(shellList);
        addMouseListener(tm1);
    }
}