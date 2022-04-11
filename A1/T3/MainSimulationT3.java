package T3;

import Event.*;
import java.util.*;
import java.io.*;


public class MainSimulationT3 extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	StateT3 actState = new StateT3(); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
		insertEvent(ARRIVAL, 0);  
		insertEvent(MEASURE, 5);

      	// The main simulation loop
    	while (time < 5000) {
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
    	// Printing the result of the simulation, in this case a mean value
    	System.out.println("Mean nbr of customers: " + 1.0*actState.accumulated/actState.noMeasurements 
							+ " ----- validation: " + 2/(actState.MEAN_ARRIVAL-1));
    	System.out.println("Mean time spent in system: " + 1.0*actState.accumulatedTime/actState.noServedCustomers
							+ " ----- validation: " + 2*actState.MEAN_ARRIVAL/(actState.MEAN_ARRIVAL-1));
    }
}