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
		field.endInteraction();
	}

	private void measure() {
		results.add(field.peopleDone());
		insertEvent(MEASURE, time + Global.MEASURE_TIME);
	}
}