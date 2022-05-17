package T1;

import java.util.*;


// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class GatewayProcess extends Proc {
	public int nbrSuccessful = 0, nbrTotal = 0, noMeasurements = 0;
	public int receiving = 0;
	public boolean fail = false;
	Random slump = new Random();
	public double x = 5000, y = 5000;
	public ArrayList<Sensor> transmittingSensors = new ArrayList<>();

	@Override
	public void TreatSignal(Signal x){
		switch (x.signalType){

			case START_RECEIVING:{
				nbrTotal++;
				//transmittingSensors.add((Sensor) x.source);
				if(transmittingSensors.size() > 1) {
					fail = true;
				}
				
			} break;

			case STOP_RECEIVING:{
				if(!fail) {
					nbrSuccessful++;
				}
				transmittingSensors.remove((Sensor) x.source);
				if(transmittingSensors.size() == 0) {
					fail = false;
				}
			} break;

			case MEASURE:{
				noMeasurements++;
				double packetLoss = (double)nbrSuccessful / (double) nbrTotal;
				packetLossMeasurements.add(packetLoss);
				confWidth = Util.calculateConfidenceWidth(packetLossMeasurements);
				SignalList.SendSignal(MEASURE, this, this, time + 5000*slump.nextDouble());
				System.out.println(confWidth);
				System.out.println(transmittingSensors.size());
				System.out.println("Transmitting sensors:");
				System.out.println(transmittingSensors);
			} break;
		}
	}

}