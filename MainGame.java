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
            tank.activateTurretRotation();
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

    public static void main(String[] args){
        MainGame main = new MainGame();
        main.initializeGameEnviroment();
        main.gameLoop();
    }
}