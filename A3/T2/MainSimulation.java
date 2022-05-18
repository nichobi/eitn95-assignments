package T2;

import java.io.*;
import java.util.ArrayList;

public class MainSimulation extends GlobalSimulation {
	public static void main(String[] args) throws IOException {
		run();
	}

	public static void run() {
		ArrayList<Person> people = new ArrayList<Person>();
		// TODO: Insert people from config file
		Field field = new Field(people);

		State actState = new State();
		Event actEvent;
		// Some events must be put in the event list at the beginning
		insertEvent(ARRIVAL, 0);

		// The main simulation loop
		while (!field.allDone()) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}
	}
}