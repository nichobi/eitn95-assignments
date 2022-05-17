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

	@Override
	public void TreatSignal(Signal x){
		switch (x.signalType){

			case START_RECEIVING:{
				nbrTotal++;
				receiving++;
				if(receiving > 1) {
					fail = true;
					//System.out.println("Receiving multiple.");
				}
				//System.out.println("Receiving " + receiving);}
				
			} break;

			case STOP_RECEIVING:{
				if(!fail) {
					nbrSuccessful++;
				}
				receiving--;
				if(receiving == 0) {
					fail = false;
					//System.out.println("Receiving none.");
				}
			} break;

			case MEASURE:{
				noMeasurements++;
				double packetLoss = (double)nbrSuccessful / (double) nbrTotal;
				packetLossMeasurements.add(packetLoss);
				confWidth = Util.calculateConfidenceWidth(packetLossMeasurements);
				SignalList.SendSignal(MEASURE, this, time + 5000*slump.nextDouble());
			} break;
		}
	}

}