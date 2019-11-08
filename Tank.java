public class Tank{
    
    TankHull tankHull;
    TankTurret tankTurret;

    public void setHull(int xPos, int yPos, int width, int height){
        tankHull = new TankHull(xPos, yPos, width, height);
    }

    public void setHullImage(String ImageURL){
        tankHull.setImage(ImageURL);
    }

    public void setHullTurningDistance(double turningDistance){
        tankHull.setTurningDistance(turningDistance);
    }

    public void setStartingDirection(double objectDirection){
        tankHull.setObjectDirection(objectDirection);
    }

    public void setTankSpeed(int speed){
        tankHull.setMovementSpeed(speed);
    }

    public TankHull getHull(){
        return tankHull;
    }

    public void setTurret(int width, int height){
        tankTurret = new TankTurret(this.tankHull, width, height);
        tankTurret.setObjectDirection(tankHull.getObjectDirection());
    }

    public void setTurretImage(String ImageURL){
        tankTurret.setImage(ImageURL);
    }

    public void setTurretTurningDistance(double turningDistance){
        tankTurret.setTurningDistance(turningDistance);
    }

    public TankTurret getTurret(){
        return tankTurret;
    }
}