package T1;

public class Sensor {
	private static String EQUALS = " = ", COMMA = ", ";
	private int id;
	private double x, y;

	public Sensor(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public Sensor(String str) {
		String[] split = str.split(EQUALS);
		if(split.length != 2) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int id = Integer.parseInt(split[0]);

		// Remove []
		String coordinates = split[1].substring(1, split[1].length());
		String[] coordArray = coordinates.split(COMMA);
		
		if(coordArray.length != 2) {
			throw new ArrayIndexOutOfBoundsException();
		}

		this.id = id;
		this.x = Double.parseDouble(coordArray[0]);
		this.y = Double.parseDouble(coordArray[1]);
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

	/**
	 * Example:
	 * 2 = [13.12414, 3948.13134]\n
	 */
	public String getConfString() {
		return Integer.toString(id) + EQUALS + "[" + Double.toString(x) + COMMA + Double.toString(y) + "]\n";
	}
}
