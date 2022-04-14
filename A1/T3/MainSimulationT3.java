package T3;

import java.util.*;

import T1.*;

import java.io.*;


public class MainSimulationT3 extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	StateT3 actState = new StateT3();

		StateT3.MEAN_ARRIVAL = 1.1;
		StateT3.Q1_MEAN_SERVICE = 1;
		StateT3.Q2_MEAN_SERVICE = 1;

		insertEvent(ARRIVAL, 0);  
		insertEvent(MEASURE, 5);

      	// The main simulation loop
    	while (time < 500000) {
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
    	// Printing the result of the simulation, in this case a mean value
    	System.out.println("Mean nbr of customers: " + 1.0*actState.accumulated/actState.noMeasurements 
							+ " ----- validation: " + 2/(StateT3.MEAN_ARRIVAL-1));
    	System.out.println("Mean time spent in system: " + 1.0*actState.accumulatedTime/actState.noServedCustomers
							+ " ----- validation: " + 2*StateT3.MEAN_ARRIVAL/(StateT3.MEAN_ARRIVAL-1));
    }
}