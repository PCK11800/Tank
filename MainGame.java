import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainGame {

    GameWindow gameWindow;
    GamePanel gamePanel;
    Tank tank;

    int screenWidth;
    int screenHeight;

    ArrayList<Shell> shellList = new ArrayList<>();
    ArrayList<Obstacle> obstacleList = new ArrayList<>();

    private void initializeGameEnviroment(){
        gameWindow = new GameWindow(1000, 1000);
        setFullScreen();
        gamePanel = new GamePanel(gameWindow);
        gameWindow.add(gamePanel);
    }

    private void iniGame(){
        createPlayerTank();
        createObstacles();
        gameWindow.initListeners(tank, gamePanel, shellList);
    }

    private void setFullScreen(){
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private void createPlayerTank(){
        tank = new Tank();
        tank.setHull(500, 500, 100, 100);
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

    private void createObstacles(){
        Obstacle obstacle1 = new Obstacle(500, 200, 500, 100, obstacleList);
        Obstacle obstacle2 = new Obstacle(800, 600, 200, 400, obstacleList);

        for(int i = 0; i < obstacleList.size(); i++){
            Obstacle thisObstacle = obstacleList.get(i);
            gamePanel.add(thisObstacle);
            gamePanel.repaint();
        }

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
                    sleep(15);
                    checkAllShellsForCollision();
                    updateShells();
                    gamePanel.repaint();
                }
            }
        });
        t2.start();
    }

    public void ricochetShell(Shell shell, Obstacle obstacle){
        
        //Get shell corner coordinates
        int shellxPos = shell.getWidth()/2 + shell.getxPos();
        int shellyPos = shell.getHeight()/2 + shell.getyPos();

        int shell_a = shellxPos - (shell.getWidth()/2); //Top left corner x value
        int shell_b = shellyPos - (shell.getHeight()/2); //Top left corner y value

        int shell_c = shellxPos + (shell.getWidth()/2); //Top right corner x value
        int shell_d = shellyPos - (shell.getHeight()/2); //Top right corner y value

        int shell_e = shellxPos - (shell.getWidth()/2); //Bottom left corner x value
        int shell_f = shellyPos + (shell.getHeight()/2); //Bottom left right corner y value

        int shell_g = shellxPos - (shell.getWidth()/2); //Bottom right corner x value
        int shell_h = shellyPos - (shell.getHeight()/2); //Bottom right corner y value


        //Get Obstacle corner coordinates
        int obstaclexPos = obstacle.getWidth()/2 + obstacle.getxPos();
        int obstacleyPos = obstacle.getHeight()/2 + obstacle.getyPos();

        int obstacle_a = obstaclexPos - (obstacle.getWidth()/2); //Top left corner x value
        int obstacle_b = obstacleyPos - (obstacle.getHeight()/2); //Top left corner y value

        int obstacle_c = obstaclexPos + (obstacle.getWidth()/2); //Top right corner x value
        int obstacle_d = obstacleyPos - (obstacle.getHeight()/2); //Top right corner y value

        int obstacle_e = obstaclexPos - (obstacle.getWidth()/2); //Bottom left corner x value
        int obstacle_f = obstacleyPos + (obstacle.getHeight()/2); //Bottom left right corner y value

        int obstacle_g = obstaclexPos + (obstacle.getWidth()/2); //Bottom right corner x value
        int obstacle_h = obstacleyPos + (obstacle.getHeight()/2); //Bottom right corner y value

        //Detect top side
        if(shell_f <= obstacle_b && shell_a <= obstacle_c && shell_c >= obstacle_a){
            shell.rotateObject(180 - shell.getObjectDirection());
        }
        else if(shell_f >= obstacle_b && shell_b < obstacle_b && shell_a <= obstacle_c && shell_c >= obstacle_a){
            shell.rotateObject(180 - shell.getObjectDirection());
        }

        //Detect bottom side
        if(shell_b >= obstacle_f && shell_a <= obstacle_c && shell_c >= obstacle_a){
            shell.rotateObject(180 - shell.getObjectDirection());
        }
        else if(shell_b <= obstacle_f && shell_f > obstacle_f && shell_a <= obstacle_c && shell_c >= obstacle_a){
            shell.rotateObject(180 - shell.getObjectDirection());
        }

        //Detect left side
        if(shell_c <= obstacle_a && shell_b <= obstacle_f && shell_f >= obstacle_b){
            shell.rotateObject(0 - shell.getObjectDirection());
        }
        else if(shell_c >= obstacle_a && shell_a < obstacle_a && shell_b <= obstacle_f && shell_f >= obstacle_b){
            shell.rotateObject(0 - shell.getObjectDirection());
        }

        //Detect right side
        if(shell_a >= obstacle_c && shell_b <= obstacle_f && shell_f >= obstacle_b){
            shell.rotateObject(0 - shell.getObjectDirection());
        }
        else if(shell_a <= obstacle_c && (shell_a + shell.getWidth()) > obstacle_c && shell_b <= obstacle_f && shell_f >= obstacle_b){
            shell.rotateObject(0 - shell.getObjectDirection());
        }

        shell.increaseRicochetAmount();
    }

    public boolean collisionCheckShell(Shell shell, Obstacle obstacle){
        return shell.getBounds().intersects(obstacle.getBounds());
    }

    public void checkAllShellsForCollision(){
        for(int i = 0; i < shellList.size(); i++){
            Shell thisShell = shellList.get(i);
            for(int j = 0; j < obstacleList.size(); j++){
                Obstacle thisObstacle = obstacleList.get(j);
                if(collisionCheckShell(thisShell, thisObstacle)){
                    if(thisShell.getRicochetAmount() < thisShell.getRicochetNum()){
                        ricochetShell(thisShell, thisObstacle);
                    }
                    else{
                        gamePanel.remove(thisShell);
                        shellList.remove(thisShell);
                        shellList.trimToSize();
                    }
                }
            }
        }
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