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

    public void initKeyListener(Tank tank, GamePanel panel){
        TankKeyListener tkl = new TankKeyListener(tank, panel);
        addKeyListener(tkl);
    }
}