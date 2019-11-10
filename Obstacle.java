import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

public class Obstacle extends RotatingObject{

    public Obstacle(int xPos, int yPos, int width, int height, ArrayList<Obstacle> obstacleList){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        setBounds(xPos, yPos, width, height);
        iniObject();

        setOpaque(true);
        setBackground(Color.RED);

        obstacleList.add(this);
    }
}