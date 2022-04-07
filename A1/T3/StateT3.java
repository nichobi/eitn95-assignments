import java.util.*;
import java.io.*;

class StateT3 extends GlobalSimulation{
	
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int accumulated = 0, noMeasurements = 0;
	public double accumulatedTime = 0, noServedCustomers = 0;

	public LinkedList<Double> queue1 = new LinkedList<>();
	public LinkedList<Double> queue2 = new LinkedList<>();

	Random slump = new Random(); // This is just a random number generator
	public static final double MEAN_ARRIVAL = 2, Q1_MEAN_SERVICE = 1, Q2_MEAN_SERVICE = 1;

	
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
		if (queue1.size() == 0)
			insertEvent(PROGRESS, time + getExponential(Q1_MEAN_SERVICE));
		queue1.add(time);
		insertEvent(ARRIVAL, time + getExponential(MEAN_ARRIVAL));
	}

	private void progress(){
		if (queue2.size() == 0)
			insertEvent(READY, time + getExponential(Q2_MEAN_SERVICE));
		queue2.add(queue1.removeFirst());
		if (queue1.size() > 0)
			insertEvent(PROGRESS, time + getExponential(Q1_MEAN_SERVICE));
	}
	
	private void ready(){
		double timeIn = queue2.removeFirst();
		accumulatedTime += time - timeIn;
		noServedCustomers++;
		if (queue2.size() > 0)
			insertEvent(READY, time + getExponential(Q2_MEAN_SERVICE));
	}
	
	private void measure(){
		accumulated = accumulated + queue1.size() + queue2.size();
		noMeasurements++;
		insertEvent(MEASURE, time + getExponential(5));
	}

	private double getExponential(double mean) {
		double lambda = 1/mean;
		return Math.log(1 - slump.nextDouble())/(-lambda);
	}
}