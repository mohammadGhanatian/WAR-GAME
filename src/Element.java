/**
 * Created by NP on 1/23/2017.
 */
public abstract class Element {
    private int x;
    private int y;
    private boolean team;
    protected int strength;
    private boolean isAlive;

    public Element(int x, int y,boolean team) {
        this.x = x;
        this.y = y;
        this.team=team;
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
        Game.free(getX(), getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean getTeam(){
        return team;
    }

    public void move(String direction){
        if(direction=="up"){
            setY(getY()+1);
        }if(direction=="down"){
            setY(getY()-1);
        }
        if (direction == "left") {
            setX(getX()-1);

        }if(direction=="right"){
            setX(getX()+1);
        }

    }



    public abstract String toString();

    public abstract void escape();

}