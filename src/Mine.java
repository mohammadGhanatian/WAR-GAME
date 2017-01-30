/**
 * Created by NP on 1/29/2017.
 */
public class Mine extends Element implements EventListener {
    public Mine(int x, int y,boolean team) {
        super(x, y,team);
        Game.names[x][y]="mine";
    }

    @Override
    public void clock() {
        Launcher.updateGraphic();
    }

    @Override
    public void handle(ExplosionEvent event) {
        if (event.getX() == getX() && event.getY() == getY()) {
                kill();
        }

    }

    @Override
    public void handle(ShootEvent event) {

    }

    @Override
    public void handle(InjuryEvent event) {
        if ((event.getX() == getX()) && (event.getY() == getY()) && Game.names[event.getX()][event.getY()]!="empty") {
            kill();
            int[] clickedPosition=new int[2];
            clickedPosition=Launcher.clickEvent();

            if ((clickedPosition[0]-1>=0  )) {
                if((Game.names[clickedPosition[0]-1][clickedPosition[1]]!="empty")){
                    System.out.println(0);
                    Game.informInjuryEvent(clickedPosition[0] - 1, clickedPosition[1]);
                }
            }
            if ((clickedPosition[0]+1<=9 ) ){
                if((Game.names[clickedPosition[0]+1][clickedPosition[1]]!="empty")){
                    Game.informInjuryEvent(clickedPosition[0] + 1, clickedPosition[1]);System.out.println(1);}
            }
            if ((clickedPosition[1]-1>=0 ) ){
                if((Game.names[clickedPosition[0]][clickedPosition[1]-1]!="empty")){
                    Game.informInjuryEvent(clickedPosition[0], clickedPosition[1]-1);System.out.println(2);}
            }
            if ((clickedPosition[1]+1<=9 ) ){
                if(Game.names[clickedPosition[0]][clickedPosition[1]+1]!="empty"){
                    Game.informInjuryEvent(clickedPosition[0], clickedPosition[1]+1);System.out.println(3);}
            }
            if ((clickedPosition[0]-1>=0 &&clickedPosition[1]-1>=0) ){
                if(Game.names[clickedPosition[0]-1][clickedPosition[1]-1]!="empty"){
                    Game.informInjuryEvent(clickedPosition[0] - 1, clickedPosition[1]-1);System.out.println(4);}
            }
            if ((clickedPosition[0]-1>=0 && clickedPosition[1]+1<=9 ) ){
                if(Game.names[clickedPosition[0]-1][clickedPosition[1]+1]!="empty"){
                    Game.informInjuryEvent(clickedPosition[0] - 1, clickedPosition[1]+1);System.out.println(5);}
            }
            if ((clickedPosition[0]+1<=9 && clickedPosition[1]+1<=9 ) ){
                if(Game.names[clickedPosition[0]+1][clickedPosition[1]+1]!="empty"){
                    Game.informInjuryEvent(clickedPosition[0] + 1, clickedPosition[1]+1);System.out.println(6);}
            }
            if ((clickedPosition[0]+1<=9 && clickedPosition[1]+1<=9 ) ){
                if(Game.names[clickedPosition[0]+1][clickedPosition[1]+1]!="empty"){
                    Game.informInjuryEvent(clickedPosition[0] + 1, clickedPosition[1]+1);System.out.println(7);}
            }
        }
        }


    @Override
    public String toString() {
        return null;
    }

    @Override
    public void escape() {

    }
}
