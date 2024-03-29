import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

public class GamePanel extends JLayeredPane{

    private int width, height;
    
    public GamePanel(GameWindow gameWindow){
        this.width = gameWindow.getWidth();
        this.height = gameWindow.getHeight();

        setBounds(0, 0, width, height);
        setOpaque(true);
        setLayout(null);
        setBackground(Color.WHITE);

        setFocusable(true);
        requestFocus();
    }
}