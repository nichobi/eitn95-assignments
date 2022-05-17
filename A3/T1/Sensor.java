package T1;

import java.util.*;

public class Sensor extends Proc {
	private static String EQUALS = " = ", COMMA = ", ";
	private int id;
	public double x, y, radius;

	public GatewayProcess gateway;
	public double meanSleep, Tp;
	Random slump = new Random();

	public Sensor(int id, double x, double y, GatewayProcess gateway, double radius, double sleep, double Tp) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.gateway = gateway;
		this.radius = radius;
		this.meanSleep = sleep;
		this.Tp = Tp;
	}

	public Sensor(String str) {
		String[] split = str.split(EQUALS);
		if(split.length != 2) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int id = Integer.parseInt(split[0]);

		//String coordinates = split[1].substring(1, split[1].length());	// Remove []
		String[] coordArray = split[1].split(COMMA);
		
		if(coordArray.length != 2) {
			throw new ArrayIndexOutOfBoundsException();
		}

		this.id = id;
		this.x = Double.parseDouble(coordArray[0]);
		this.y = Double.parseDouble(coordArray[1]);
	}

	public void TreatSignal(Signal s){
		switch (s.signalType){

			case TRANSMIT:{
				if(!Util.withinRange(x, y, gateway.x, gateway.y, radius)) {
					break;
				}

				SignalList.SendSignal(START_RECEIVING, gateway, time);
				SignalList.SendSignal(STOP_RECEIVING, gateway, time+Tp);
				SignalList.SendSignal(TRANSMIT, this, time + Tp + Util.getExponential(meanSleep));
			} break;
		}
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

	@Override
	public String toString() {
		return "Sensor("+id+", "+x+", "+y+")";
	}
}
