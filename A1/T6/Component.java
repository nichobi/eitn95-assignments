import java.util.ArrayList;
import java.util.Random;

public class Component extends GlobalT6 {

    public static int LIVE_COMPONENTS = 0;

    public ArrayList<Component> connections;
    public double lifeTime;
    private boolean alive;
    private Random rand = new Random();

    public Component(ArrayList<Component> connections) {
        this.connections = connections;
        lifeTime = rand.nextDouble() * 4.0 + 1.0;
    }

    public Component() {
        this(new ArrayList<>());
    }

    public void TreatSignal(SignalT6 x){
		switch (x.signalType){
            case START:
                LIVE_COMPONENTS++;
                alive = true;
                SignalListT6.SendSignal(BREAK, this, time + lifeTime);
                for(Component conn : connections)
                    SignalListT6.SendSignal(BREAK, conn, time + lifeTime);
                break;
            case BREAK:
                if (alive) {
                    LIVE_COMPONENTS--;
                    alive = false;
                }
                break;
        }
    }
}
