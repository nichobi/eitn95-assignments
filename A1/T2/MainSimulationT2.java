package T2;

import java.util.*;
import java.io.*;


public class MainSimulationT2 extends GlobalSimulationT2{
  public static void main(String[] args) throws IOException {
    for(int i = 0; i < 10; i++) {
      runSimulation();
    }
  }

  public static void runSimulation() throws IOException {
    Event actEvent;
    StateT2 actState = new StateT2();

    /* Init static values for every iteration */
    time = 0;
    eventList = new EventListClass();

    insertEvent(ARRIVAL, 0);  
    insertEvent(MEASURE, 5);
    
    // The main simulation loop
    while (time < 5000){
      actEvent = eventList.fetchEvent();
      time = actEvent.eventTime;
      actState.treatEvent(actEvent);
    }
    
    // Printing the result of the simulation, in this case a mean value
    System.out.println(1.0*actState.accumulated/actState.noMeasurements);
  }
}