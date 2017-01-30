/**
 * Created by NP on 1/29/2017.
 */
public class Bullet extends Element implements EventListener {
    private boolean isItShowed = false;

    public Bullet(int x, int y, boolean team) {
        super(x, y, team);
        Game.names[x][y]="bullet";
    }

    @Override
    public void clock() {
        if (getTeam() == false) {
            Game.moveInList(getX(),getY(),getX()+1,getY());
            setX(getX() + 1);
        } else {
            Game.moveInList(getX(),getY(),getX()-1,getY());
            setX(getX() - 1);
        }
        Launcher.updateGraphic();
    }

    public void show() {
        if (isItShowed == false) {
            Game.names[getX()][getY()]="bullet";
        }
    }
    public void hide(){
        if(isItShowed == true){
            Game.names[getX()][getY()]="empty";
        }
    }


    @Override
    public void handle(ExplosionEvent event) {

    }

    @Override
    public void handle(ShootEvent event) {

    }

    @Override
    public void handle(InjuryEvent event) {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void escape() {

    }
}