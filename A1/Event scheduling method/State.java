import java.util.*;
import java.io.*;

class State extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQueue = 0, numberInQueue2 = 0, accumulated = 0, 
		noMeasurements = 0, totalCustomers = 0, rejectedCustomers = 0;

	Random slump = new Random(); // This is just a random number generator
	public static int INTER_ARRIVAL = 1;
	public static int SERVICE_Q2 = 2;
	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL:
				arrival();
				break;
			case PROGRESS:
				progress();
				break;
			case READY:
				ready();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrival(){
		totalCustomers++;
		if (numberInQueue == 0)
			insertEvent(PROGRESS, time + getExponential(2.1));
		if(numberInQueue < 10)
			numberInQueue++;
		else
		 	rejectedCustomers++;
		insertEvent(ARRIVAL, time + INTER_ARRIVAL);
	}

	private void progress(){
		if (numberInQueue2 == 0)
			insertEvent(READY, time + SERVICE_Q2);
		numberInQueue--;
		numberInQueue2++;
		if (numberInQueue > 0)
			insertEvent(PROGRESS, time + getExponential(2.1));
	}
	
	private void ready(){
		numberInQueue2--;
		if (numberInQueue2 > 0)
			insertEvent(READY, time + SERVICE_Q2);
	}
	
	private void measure(){
		accumulated = accumulated + numberInQueue2;
		noMeasurements++;
		insertEvent(MEASURE, time + getExponential(5));
	}

  private double getExponential(double mean) {
    double lambda = 1/mean;
    return Math.log(1 - slump.nextDouble())/(-lambda);
  }
}