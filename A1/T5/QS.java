import java.util.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc{
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc sendTo;
	Random slump = new Random();

	public void TreatSignal(Signal x){
		switch (x.signalType){

			case ARRIVAL:{
				numberInQueue++;
				if (numberInQueue == 1){
					SignalList.SendSignal(READY, this, time + getExponential(1.0/0.5));
				}
			} break;

			case READY:{
				numberInQueue--;
				if (sendTo != null){
					SignalList.SendSignal(ARRIVAL, sendTo, time);
				}
				if (numberInQueue > 0){
					SignalList.SendSignal(READY, this, time + getExponential(1.0/0.5));
				}
			} break;

			case MEASURE:{
				noMeasurements++;
				accumulated = accumulated + numberInQueue;
				SignalList.SendSignal(MEASURE, this, time + slump.nextDouble() * 5);
			} break;
		}
	}

	/**
   * Used to generate an exponentially distributed variable.
   * @param lambda is also defined as 1/mean.
   * @return the generated value.
   */
  private double getExponential(double lambda) {
    return Math.log(1 - slump.nextDouble())/(-lambda);
  }
}
