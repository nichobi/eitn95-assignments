package T2;
import java.util.*;
import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
        Event actEvent;
        State actState = new State(); // The state that shoud be used
        // Some events must be put in the event list at the beginning
        actState.scheduleArrival();
        
        // The main simulation loop
        while (time < 24*1001){
            actEvent = eventList.fetchEvent();
            time = actEvent.eventTime;
            actState.treatEvent(actEvent);
        }
        
        // Printing the result of the simulation, in this case a mean value
        ArrayList<Double> closingTimes = actState.closingTimes;
        ArrayList<Double> processingTimes = actState.processingTimes;
        System.out.println(Analysis.mean(closingTimes));
        System.out.println(Arrays.toString(Analysis.confidenceInterval(closingTimes)));
        System.out.println(Analysis.mean(processingTimes));
        System.out.println(Arrays.toString(Analysis.confidenceInterval(processingTimes)));
    }
}