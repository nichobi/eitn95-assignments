package T2;

import java.io.*;

public class MainSimulationT2 extends GlobalSimulationT2{
  public static void main(String[] args) throws IOException {
    int iterations = 1000;
    double total = 0;

    System.out.println("Running " + Integer.toString(iterations) + " simulations...");
    for(int i = 0; i < iterations; i++) {
      total += runSimulation();
    }
    System.out.println("Total mean: " + Double.toString(total/iterations));
  }

  public static double runSimulation() throws IOException {
    Event actEvent;
    StateT2 actState = new StateT2();

    /* Init static values for every iteration */
    time = 0;
    eventList = new EventListClass();

    insertEvent(ARRIVAL, 0);  
    insertEvent(MEASURE, 0.1);
    
    // The main simulation loop
    while (time < 100){
      actEvent = eventList.fetchEvent();
      time = actEvent.eventTime;
      actState.treatEvent(actEvent);
    }
    
    // Printing the result of the simulation, in this case a mean value
    return  1.0*actState.accumulated/actState.noMeasurements;
  }
}