package T2;

import java.util.HashSet;

public class Person {
	private boolean talking;
	private int id, movementSpeed, direction;
	private HashSet<Person> interactions;
	private double timeInteracted, x, y;

	public Person(int id, double x, double y) {
		this.talking = false;
		this.id = id;
		this.movementSpeed = Global.V;
		this.direction = Util.generateDirection();
		this.interactions = new HashSet<Person>();
		this.timeInteracted = 0;
		this.x = x;
		this.y = y;
	}

	public Person(String str) {
		String[] split = str.split(Global.EQUALS);
		if(split.length != 2) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int id = Integer.parseInt(split[0]);

		String coordinates = split[1].substring(1, split[1].length());	// Remove []
		String[] coordArray = coordinates.split(Global.COMMA);
		
		if(coordArray.length != 2) {
			throw new ArrayIndexOutOfBoundsException();
		}

		this.id = id;
		this.x = Double.parseDouble(coordArray[0]);
		this.y = Double.parseDouble(coordArray[1]);
	}

	public boolean isInteracting() {
		return this.talking;
	}

	public int getId() {
		return this.id;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setInteracting(boolean talking) {
		this.talking = talking;
	}

	public void interactedWith(Person p2) {
		interactions.add(p2);
		talking = false;
	}

	/**
	 * We are allowed to neglect calculating diagonal movement speed
	 * as sqrt(x^2 + y^2) according to Björn.
	 * 
	 * @Returns Return true if the new position is inside a new square.
	 */
	public boolean move() {
		double movement = Global.MOVE_TIME * this.movementSpeed;

		// Move in X direction
		if (1 <= this.direction <= 3) {
			this.x += movement;
		} else if (5 <= this.direction <= 7) {
			this.x -= movement;
		}

		// Move in Y direction
		if (this.direction % 7 <= 1) {
			this.y += movement;
		} else if (3 <= this.direction <= 5) {
			this.y -= movement;
		}

		return (oldX == math.floor(this.x) && oldY == math.floor(this.y))
	}

	public boolean isSameBox(int x, int y) {
		return (x == math.floor(this.x) && y == math.floor(this.y))
	}

	public boolean done() {
		return interactions.size() == Global.STUDENTS - 1;
	}

	/**
	 * Example:
	 * 2 = [13.12414, 3948.13134]\n
	 */
	public String getConfString() {
		return Integer.toString(id) + Global.EQUALS + "[" +
			Double.toString(x) + Global.COMMA + Double.toString(y) + "]\n";
	}
}
