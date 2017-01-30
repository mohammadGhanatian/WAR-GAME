/**
 * Created by NP on 1/23/2017.
 */
public class Officer extends Element implements EventListener {
    private int speedOfBullet = 3;
    private static int totalNumber = 0;
    private int index;

    public Officer(int x, int y,boolean team) {
        super(x, y,team);
        Game.names[x][y] = "officer";
        strength = 10;
        index = totalNumber;
        totalNumber++;
    }

    @Override
    public String toString() {
        return "officer #" + index + " x= " + getX() + " y= " + getY();
    }

    @Override
    public void escape() {
        if (getX() == 0) {
            int newX=getX() + 1;
            Game.moveInList(getX(),getY(),newX,getY());
            setX(getX()+1);
        } else if (getX() == 9) {
            int newX=getX() - 1;
            setX(newX);
            Game.moveInList(getX(),getY(),newX,getY());
        } else {
            int newX=getX() + 1;
            setX(newX);
            Game.moveInList(getX(),getY(),newX,getY());
        }
    }


    public void shoot() {

    }


    @Override
    public void clock() {
        Launcher.updateGraphic();
    }

    @Override
    public void handle(ExplosionEvent event) {

        if (event.getX() == getX() && event.getY() == getY() && strength > 0) {
            strength -= 2;
            System.out.println("Officer #" + index + " strength: " + strength);
            if (strength == 0) {
                kill();
            }
        }

    }

    @Override
    public void handle(ShootEvent event) {

    }

    @Override
    public void handle(InjuryEvent event) {
        if ((event.getX() == getX()) && (event.getY() == getY()) && Game.names[event.getX()][event.getY()]!="empty") {
            escape();
        }

    }
}