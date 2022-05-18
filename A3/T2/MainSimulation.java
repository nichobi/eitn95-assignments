package T2;

import java.io.*;

public class MainSimulation extends Global {
	public static void main(String[] args) throws IOException {
		run();
	}

	public static void run() {
		State actState = new State();
		Event actEvent;
		// Some events must be put in the event list at the beginning
		insertEvent(MOVE, 0);
		insertEvent(MEASURE, 0);

		// The main simulation loop
		while (actState.field.peopleDone() < Global.STUDENTS) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}
	}
}