package T6;

import java.util.*;
import java.io.*;

public class MainSimulationT6 extends GlobalT6 {

	private static void newSimulation() {
		Component C2 = new Component();
    	Component C5 = new Component();
		ArrayList<Component> temp = new ArrayList<>();
		temp.add(C2); temp.add(C5);
		Component C1 = new Component(temp);
		Component C4 = new Component();
		temp.clear(); temp.add(C4);
		Component C3 = new Component(temp);

    	SignalListT6.SendSignal(START, C1, time);
		SignalListT6.SendSignal(START, C2, time);
		SignalListT6.SendSignal(START, C3, time);
		SignalListT6.SendSignal(START, C4, time);
		SignalListT6.SendSignal(START, C5, time);
	}

    public static void main(String[] args) throws IOException {

    	SignalT6 actSignal;

		int nbrOfRuns = 1000;
		double accumulatedTime = 0;

		for (int i = 0; i < nbrOfRuns; i++) {
			time = 0;
			new SignalListT6();
			newSimulation();
			while (Component.LIVE_COMPONENTS != 0 || time == 0){
				actSignal = SignalListT6.FetchSignal();
				time = actSignal.arrivalTime;
				actSignal.destination.TreatSignal(actSignal);
			}
			accumulatedTime += time;
		}

    	System.out.println("Mean time before system breakdown: " + 1.0*accumulatedTime/nbrOfRuns);

    }
}