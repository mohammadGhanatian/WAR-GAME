import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by NP on 1/24/2017.
 */
public class Game extends TimerTask {
    public static String[][] names = new String[10][10];
    private static List<Element> listOfElements=new ArrayList<Element>();
    private Timer timer;

    private static  List<EventListener> eventListeners = new ArrayList<>();

    private int clock = 0;

    private int rows;

    private int cols;

    public Game(int rows, int cols)
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                names[i][j] = "empty";
            }
        }

        this.rows = rows;
        this.cols = cols;

        Officer officer0=new Officer(0,0,false);
        Soldier soldier0=new Soldier(0,1,false);
        Car car0=new Car(0,2,false);
        Mine mine0=new Mine(5,5,false);


        Officer officer1=new Officer(9,0,true);
        Soldier soldier1=new Soldier(9,1,true);
        Car car1=new Car(9,2,true);
        Mine mine1=new Mine(3,3,true);


        eventListeners.add(officer0);
        eventListeners.add(officer1);
        eventListeners.add(soldier0);
        eventListeners.add(soldier1);
        eventListeners.add(car0);
        eventListeners.add(car1);
        eventListeners.add(mine0);
        eventListeners.add(mine1);


        listOfElements.add(officer0);
        listOfElements.add(officer1);
        listOfElements.add(soldier0);
        listOfElements.add(soldier1);
        listOfElements.add(car0);
        listOfElements.add(car1);
        listOfElements.add(mine0);
        listOfElements.add(mine1);

    }
///clocke anaser ro seda mizanim
    @Override
    public void run()
    {
        for (EventListener eventListener : eventListeners) {
            eventListener.clock();
        }
    }

    static void free(int x,int y){
        names[x][y]="empty";
        System.out.println("emptied");

    }


    public static  void informExplosionEvent(int x, int y)
    {
        ExplosionEvent event = new ExplosionEvent(x, y);
        for (EventListener eventListener : eventListeners) {
            eventListener.handle(event);
        }
    }
    public static void informInjuryEvent(int x,int y){
        InjuryEvent event=new InjuryEvent(x,y);
        for(EventListener eventListener:eventListeners){
            eventListener.handle(event);
        }
    }
    public static void moveInList(int curX,int curY,int desX,int desY){
        names[desX][desY]=names[curX][curY];
        names[curX][curY]="empty";
    }

    public void start()
    {
        timer = new Timer();
        timer.schedule(this, 1000, 10000);
    }
    public static Element whatIsInThisPlace(int x,int y){
        for(Element element:listOfElements){
            if(element.getX()==x && element.getY()==y ){
                return element;
            }
        }
        System.out.println("haji nadarim");
        return null;
    }



    @Override
    public String toString()
    {
        String s = "";
        for (EventListener eventListener : eventListeners) {
            s = s + eventListener + "\n";
        }
        return s;
    }

}