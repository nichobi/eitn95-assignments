package T3;

import java.util.*;

class State extends GlobalSimulation{
	
	public int nbrOfMonths = 0, noMeasurements = 0;

	Random rand = new Random(); // This is just a random number generator

	public static double SAVINGS_LEVEL = 5000;
	public static double DISTURBANCE_RISK = 0.25;

	public static double savings = 0;
	private static double growthRate = 1.30;

	public void treatEvent(Event x){
		switch (x.eventType){
			case NEW_YEAR:
				newYear();
				break;
			case NEW_MONTH:
				newMonth();
				break;
		}
	}
	
	private void newYear() {
		if (rand.nextDouble() < DISTURBANCE_RISK) {
			marketMechanism();
		} else {
			growthRate = 1.30;
		}
		insertEvent(NEW_YEAR, time + 12);
	}

	private void newMonth() {
		nbrOfMonths++;
		savings += SAVINGS_LEVEL;
		savings *= Math.pow(growthRate, 1.0/12.0);
		insertEvent(NEW_MONTH, time + 1);
	}

	private void marketMechanism() {
		double marketRand = rand.nextDouble();
		if (marketRand < 0.1) {
			growthRate = 0.75;
		} else if (marketRand < 0.35) {
			growthRate = 0.50;
		} else if (marketRand < 0.6) {
			growthRate = 0.40;
		} else {
			growthRate = 0.90;
		}
	}

	public void reset() {
		savings = 0;
		growthRate = 1.30;
		nbrOfMonths = 0;
	}
}