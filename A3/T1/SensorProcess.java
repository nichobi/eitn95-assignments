package T1;

import java.util.*;

import Process.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc gateway;
	public double meanSleep = 4000;
	Random slump = new Random();

	public void TreatSignal(Signal x){
		switch (x.signalType){

			case TRANSMIT:{
				SignalList.SendSignal(START_RECEIVING, gateway, time);
				SignalList.SendSignal(STOP_RECEIVING, gateway, time+1);
				SignalList.SendSignal(TRANSMIT, this, time + 1 + Util.getExponential(meanSleep));
			} break;
		}
	}
}