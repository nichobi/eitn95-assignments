package T2;

import java.util.*;

class State extends Global {
	public Field field = new Field();
	public ArrayList<Integer> results = new ArrayList<Integer>();

	public void treatEvent(Event x) {
		switch (x.eventType) {
			case MOVE:
				move();
				break;
			case END_INTERACTION:
				endInteraction();
				break;
			case MEASURE:
				measure();
				break;
		}
	}

	private void move() {
		field.moveAll();
		insertEvent(MOVE, time + Global.MOVE_TIME);
	}

	private void endInteraction() {
		// TODO:
		// 1) Retrieve the people who are finished from a interactions-FIFO-queue.
		// 2) Save the other person in the list of interactions for both of the persons.
		// 3) Set direction for each of the 2 persons.
		// 4) Check if the simulation is done (i.e. everyone has met eachother).
	}

	private void measure() {
		results.add(interactedWithAll);
		insertEvent(MEASURE, time + Global.MEASURE_TIME);
	}
}