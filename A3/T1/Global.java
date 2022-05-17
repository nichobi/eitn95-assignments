package T1;

import java.util.ArrayList;

public class Global{
	public static final int START_RECEIVING = 1, STOP_RECEIVING = 2, TRANSMIT = 3, MEASURE = 4;
	public static double time = 0;
	public static int accumulated = 0, noMeasurments = 0;
    public static ArrayList<Double> packetLossMeasurements = new ArrayList<>();
	public static double confWidth = 0;
}
