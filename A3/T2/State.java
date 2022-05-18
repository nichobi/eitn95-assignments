package T2;

import java.util.*;

class State extends Global {
	Field field = new Field();
	public ArrayList<Integer> results = new ArrayList<Integer>();

	public void treatEvent(Event x) {
		switch (x.eventType) {
			case MOVE:
				move();
				break;
			case MEASURE:
				measure();
				break;
		}
	}

	private void move() {
		field.moveAll();
		if (field.peopleDone() < Global.STUDENTS) {
			insertEvent(MOVE, time + Global.MOVE_TIME);
		}
	}

	private void measure() {
		results.add(interactedWithAll);
		insertEvent(MEASURE, time + Global.MEASURE_TIME);
	}
}