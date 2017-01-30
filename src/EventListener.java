/**
 * Created by NP on 1/24/2017.
 */
public interface EventListener {
    void clock();

    void handle(ExplosionEvent event);
    void handle(ShootEvent event);
    void handle(InjuryEvent event);


}
