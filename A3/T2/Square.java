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

	public Person getAvailablePerson() {
		for (Person p : ids) {
			if(!p.isTalking()) {
				return p;
			}
		}
		return null;
	}

	public void addPerson(Person p) {
		ids.add(p);
	}
}
