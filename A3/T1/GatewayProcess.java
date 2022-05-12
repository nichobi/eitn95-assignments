package T1;

import java.util.*;

import Process.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {
	public int nbrSuccessful = 0, nbrTotal = 0, noMeasurements = 0;
	public int receiving = 0;
	public boolean fail = false;
	Random slump = new Random();

	public void TreatSignal(Signal x){
		switch (x.signalType){

			case START_RECEIVING:{
				nbrTotal++;
				receiving++;
				if(receiving > 1) {
					fail = true;
				}
				
			} break;

			case STOP_RECEIVING:{
				if(!fail) {
					nbrSuccessful++;
				}
				receiving--;
				if(receiving == 0) {
					fail = false;
				}
			} break;

			case MEASURE:{
				noMeasurements++;
				accumulated = accumulated + nbrSuccessful;
				SignalList.SendSignal(MEASURE, this, time + 2*slump.nextDouble());
			} break;
		}
	}
}