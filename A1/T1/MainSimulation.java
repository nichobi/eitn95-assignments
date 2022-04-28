package T1;

import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	State actState = new State();

		State.INTER_ARRIVAL = 1;
		State.MEAN_SERVICE_Q1 = 2.1;
		State.SERVICE_Q2 = 2.0;
		
		insertEvent(ARRIVAL, 0);  
		insertEvent(MEASURE, 5);
		
      	// The main simulation loop
    	while (time < 10000000){
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
    	// Printing the result of the simulation, in this case a mean value
    	System.out.println(1.0*actState.accumulated/actState.noMeasurements);
    	System.out.println(1.0*actState.rejectedCustomers/actState.totalCustomers);
    }
}