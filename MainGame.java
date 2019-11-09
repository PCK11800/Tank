import java.awt.MouseInfo;

public class MainGame{

    GameWindow gameWindow;
    GamePanel gamePanel;
    Tank tank;

    private void initializeGameEnviroment(){
        gameWindow = new GameWindow(1000, 1000);
        gamePanel = new GamePanel(gameWindow);
        gameWindow.add(gamePanel);
    }

    private void gameLoop(){
        createPlayerTank();
        while(true){
            setConstantTurretDirection(tank);
            gamePanel.repaint();
        }
    }

    private void createPlayerTank(){
        tank = new Tank();
        tank.setHull(500, 500, 25, 50);
        tank.setHullImage("TankHullIcon.png");
        tank.setHullTurningDistance(2);
        tank.setStartingDirection(0);
        tank.setTankSpeed(4);

        tank.setTurret(25, 25);
        tank.setTurretImage("TankTurretIcon.png");
        tank.setTurretTurningDistance(2);

        gamePanel.add(tank.getTurret());
        gamePanel.add(tank.getHull());
        gamePanel.repaint();

        gameWindow.initKeyListener(tank, gamePanel);
    }

    //This works!!
    private void setConstantTurretDirection(Tank tank){
        int mouseXPos = MouseInfo.getPointerInfo().getLocation().x;
        int mouseYPos = MouseInfo.getPointerInfo().getLocation().y;

        double dx = tank.getHull().getxPos() - mouseXPos;
        double dy = tank.getHull().getyPos() - mouseYPos;

        double inRads = Math.atan2(dy, dx);

        if(inRads < 0){
            inRads = Math.abs(inRads);
        }
        else{
            inRads = 2 * Math.PI - inRads;
        }

        tank.getTurret().rotateObject(- Math.toDegrees(inRads) - 90);
    }

    public static void main(String[] args){
        MainGame main = new MainGame();
        main.initializeGameEnviroment();
        main.gameLoop();
    }
}