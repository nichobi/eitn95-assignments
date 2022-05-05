package T2;
import java.util.*;
import java.io.*;

class State extends GlobalSimulation{
    
    // Here follows the state variables and other variables that might be needed
    // e.g. for measurements
    public ArrayList<Double> closingTimes = new ArrayList<>();
    public ArrayList<Double> processingTimes = new ArrayList<>();
    public LinkedList<Double> queue = new LinkedList<>();

    Random slump = new Random(); // This is just a random number generator
    
    
    // The following method is called by the main program each time a new event has been fetched
    // from the event list in the main loop. 
    public void treatEvent(Event x){
        switch (x.eventType){
            case ARRIVAL:
                arrival();
                break;
            case READY:
                ready();
                break;
        }
    }
    
    
    // The following methods defines what should be done when an event takes place. This could
    // have been placed in the case in treatEvent, but often it is simpler to write a method if 
    // things are getting more complicated than this.
    
    private void arrival(){
        if (queue.size() == 0)
            insertEvent(READY, time + (10.0 + 10.0*slump.nextDouble())/60.0);
        queue.addLast(time);
        scheduleArrival();
    }
    
    public void scheduleArrival() {
        double nextArrival = time + getExponential(1.0/4.0);
        if (nextArrival % 24 >= 17 || nextArrival % 24 < 9) 
            nextArrival = nextOpening() + getExponential(1.0/4.0);//wait until next opening time
        insertEvent(ARRIVAL, nextArrival);
    }

    private double nextOpening() {
        return time - (time % 24) + 24 + 9;
    }

    private void ready(){
        double arrivalTime = queue.removeFirst();
        processingTimes.add(time - arrivalTime);
        if (queue.size() > 0)
            insertEvent(READY, time + (10.0 + 10.0*slump.nextDouble())/60.0);
        else if (time % 24 > 17) 
            closingTimes.add(time%24);
    }
    
    private double getExponential(double lambda) {
        return Math.log(1 - slump.nextDouble())/(-lambda);
    }
}