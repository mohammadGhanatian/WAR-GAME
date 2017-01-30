import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP on 1/23/2017.
 */
public class Car extends Element implements EventListener {
    private static int number=0;
    private List<Element> insideCar=new ArrayList<Element>();
    //constructor
    public Car(int x, int y,boolean team) {
        super(x, y,team);
        Game.names[x][y]="car";
        number++;
        strength=5;
    }
    //methods
    public boolean addToCar(Element element){

        if(insideCar.size()<4){
            insideCar.add(element);
            return true;
        }

        else return false;
    }

    public String toString(){
        return "Car number"+number+" x="+getX()+" y="+getY();
    }

    @Override
    public void escape() {

    }


    @Override
    public void clock() {
        Launcher.updateGraphic();
    }

    @Override
    public void handle(ExplosionEvent event) {
        if(event.getX()==getX()&&event.getY()==getY()){
            System.out.println("handle of car inside");
            kill();
        }

    }

    @Override
    public void handle(ShootEvent event) {
        strength--;
    }

    @Override
    public void handle(InjuryEvent event) {



    }
}
