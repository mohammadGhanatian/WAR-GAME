/**
 * Created by NP on 1/23/2017.
 */
public class Soldier extends Element implements EventListener {
    private int speedOfBullet = 3;
    private static int number = 0;

    public Soldier(int x, int y,boolean team) {
        super(x, y,team);
        Game.names[x][y] = "soldier";
        number++;

    }

    @Override
    public String toString() {
        return "soldier #" + number + " x=" + getX() + " y=" + getY();
    }

    @Override
    public void escape() {

        int flag = 0;
        while (flag == 0) {
            Integer newX=new Integer((int)(Math.random()*10));
            Integer newY=new Integer((int)(Math.random()*10));
            if (Game.names[newX][newY] == "empty") {
                Game.moveInList(getX(),getY(),newX,newY);
                setX(newX);
                setY(newY);
                flag = 1;
            }
            System.out.println(flag);
        }
    }


    public void shoot() {


    }

    public void injur() {

    }


    @Override
    public void clock() {
        System.out.println("clock");
        Launcher.updateGraphic();
    }

    @Override
    public void handle(ExplosionEvent event) {
        if (event.getX() == getX() && event.getY() == getY()) {
            System.out.println("kill");
            kill();
        }
    }

    @Override
    public void handle(ShootEvent event) {


    }

    @Override
    public void handle(InjuryEvent event) {
        if ((Math.abs(event.getX() - getX()) <= 1) && (Math.abs(event.getY() - getY()) <= 1)) {
            escape();
        }
    }

}
