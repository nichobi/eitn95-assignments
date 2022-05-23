package T1;

import java.util.*;

class State extends GlobalSimulation {
	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int noMeasurements = 0;
	public int totalServers = GlobalSimulation.N, activeServers = 0;
	public int[] results;

	Random slump = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched from the event list in the main loop.
	public void treatEvent(Event x) {
		switch (x.eventType) {
			case ARRIVAL:
				arrival();
				break;
			case READY:
				ready();
				break;
			case MEASURE:
				measure();
				break;
		}
	}

	// The following methods defines what should be done when an event takes place.
	// This could have been placed in the case in treatEvent, but often it is 
	// simpler to write a method if things are getting more complicated than this.
	private void arrival() {
		if (activeServers < totalServers) {
			insertEvent(READY, time + GlobalSimulation.X);
			activeServers++;
		}
		insertEvent(ARRIVAL, time + getExponential(GlobalSimulation.LAMBDA));
	}

	private void ready() {
		activeServers--;
	}

	private void measure() {
		if (results == null) {
			results = new int[GlobalSimulation.M];
		}
		results[noMeasurements++] = activeServers;
		insertEvent(MEASURE, time + GlobalSimulation.T);
	}

	private double getExponential(double lambda) {
		return Math.log(1 - slump.nextDouble()) / (-lambda);
	}
}