package T2;

import java.util.*;

public class Square extends Global {
	public int x, y;
	public ArrayList<Person> ids;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		this.ids = new ArrayList<Person>();
	}

	public boolean isEmpty() {
		return ids.size() == 0;
	}

	public Person getInteractablePerson(Person interactor) {
		for (Person p : ids) {
			if (!p.isInteracting() && p != interactor) {
				return p;
			}
		}
		return null;
	}

	public void addPerson(Person p) {
		ids.add(p);
	}

	public void removePerson(Person p) {
		ids.remove(p);
	}
}
