package T1;

import java.util.*;

public class Sensor extends Proc {
	private static String EQUALS = " = ", COMMA = ", ";
	private static boolean shouldSense = true;
	private int id;
	public double x, y, radius;
	private double lb = 4000, ub = 5000;

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
				boolean collision = false;
				if(shouldSense) {
					for(Sensor sensor : gateway.transmittingSensors) {
						if(Util.withinRange(x, y, sensor.x, sensor.y, radius)) {
							collision = true;
							SignalList.SendSignal(TRANSMIT, this, this, time + lb + (ub-lb)*slump.nextDouble());
							break;
						}
					}
				}
				if(!collision){
					gateway.transmittingSensors.add(this);
					if (Util.withinRange(x, y, gateway.x, gateway.y, radius)) {
						SignalList.SendSignal(START_RECEIVING, this, gateway, time);
						SignalList.SendSignal(STOP_RECEIVING, this, gateway, time + Tp);
					} else {
						SignalList.SendSignal(STOP_TRANSMIT, this, this, time + Tp);
					}
					SignalList.SendSignal(TRANSMIT, this, this, time + Tp + Util.getExponential(meanSleep));
				}
			} break;
			case STOP_TRANSMIT:{
				gateway.transmittingSensors.remove(this);
			}
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
