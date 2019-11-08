import javax.swing.*;
import java.awt.Color;

public class GamePanel extends JPanel{

    private int width, height;
    
    public GamePanel(GameWindow gameWindow){
        this.width = gameWindow.getWidth();
        this.height = gameWindow.getHeight();

        setBounds(0, 0, width, height);
        setOpaque(true);
        setLayout(null);
        setBackground(Color.WHITE);
    }
}