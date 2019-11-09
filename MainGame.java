import java.util.ArrayList;

public class MainGame {

    GameWindow gameWindow;
    GamePanel gamePanel;
    Tank tank;

    private void initializeGameEnviroment(){
        gameWindow = new GameWindow(1000, 1000);
        gamePanel = new GamePanel(gameWindow);
        gameWindow.add(gamePanel);
    }

    private void iniGame(){
        createPlayerTank();
    }

    private void gameLoop(){
        while(true){
            tank.update();
            gamePanel.repaint();
        }
    }

    private void createPlayerTank(){
        tank = new Tank();
        tank.setHull(500, 500, 100, 100);
        tank.setHullImage("TankHullIcon.png");
        tank.setHullTurningDistance(2);
        tank.setStartingDirection(0);
        tank.setTankSpeed(4);

        tank.setTurret(100, 100);
        tank.setTurretImage("TankTurretIcon.png");
        tank.setTurretTurningDistance(2);

        gamePanel.add(tank.getTurret(), 1);
        gamePanel.add(tank.getHull(), 2);
        gamePanel.repaint();

        gameWindow.initListeners(tank, gamePanel);
    }

    public static void main(String[] args){
        MainGame main = new MainGame();
        main.initializeGameEnviroment();
        main.iniGame();
        main.gameLoop();
    }
}