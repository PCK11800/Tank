import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainGame {

    GameWindow gameWindow;
    GamePanel gamePanel;
    Tank tank;

    int screenWidth;
    int screenHeight;

    ArrayList<Shell> shellList = new ArrayList<>();

    private void initializeGameEnviroment(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) Math.round(screenSize.getWidth());
        screenHeight = (int) Math.round(screenSize.getHeight());

        gameWindow = new GameWindow(screenWidth, screenHeight);
        gamePanel = new GamePanel(gameWindow);
        gameWindow.add(gamePanel);
    }

    private void iniGame(){
        createPlayerTank();
        gameWindow.initListeners(tank, gamePanel, shellList);
    }

    private void createPlayerTank(){
        tank = new Tank();
        tank.setHull(screenWidth/2, screenHeight/2, 100, 100);
        tank.setHullImage("TankHullIcon.png");
        tank.setHullTurningDistance(2);
        tank.setStartingDirection(0);
        tank.setTankSpeed(5);

        tank.setTurret(100, 100);
        tank.setTurretImage("TankTurretIcon.png");
        tank.setTurretTurningDistance(2);

        gamePanel.add(tank.getTurret(), 1);
        gamePanel.add(tank.getHull(), 2);
        gamePanel.repaint();
    }

    private void updateShells(){
        for(int i = 0; i < shellList.size(); i++){
            Shell thisShell = shellList.get(i);
            if(thisShell.getxPos() < 0 || thisShell.getyPos() < 0){
                gamePanel.remove(thisShell);
                shellList.remove(thisShell);
                shellList.trimToSize();
            }
            else if(thisShell.getxPos() > gameWindow.getWidth() || thisShell.getyPos() > gameWindow.getHeight()){
                gamePanel.remove(thisShell);
                shellList.remove(thisShell);
                shellList.trimToSize();
            }
            else{
                thisShell.update();
            }
        }
    }

    //Multithreading the gameloop for hopefully better performance.
    private void gameLoop(){

        Thread t1 = new Thread(new Runnable(){
            public void run(){
                while(true){
                    tank.update();
                    gamePanel.repaint();
                }
     
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                while(true){
                    sleep(10);
                    updateShells();
                    gamePanel.repaint();
                }
            }
        });
        t2.start();
    }

    public void sleep(long milli){
        try{
            Thread.sleep(milli);
        }catch(InterruptedException ie){
            System.out.println("Sleep is for the weak.");
        }
    }

    public static void main(String[] args){
        MainGame main = new MainGame();
        main.initializeGameEnviroment();
        main.iniGame();
        main.gameLoop();
    }
}