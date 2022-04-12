package T4;

import java.util.*;
import java.io.*;
import java.sql.Time;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {
	public int noReady;
	public double maxQTime;
	public Queue<Double> qRegular = new LinkedList<>(), qPriority = new LinkedList<>();
	public Proc sendTo;
	public double priorityChance, lambda;
	public double accRegular = 0, accPriority = 0;
	public int noRegular = 0, noPriority = 0;
	Random slump = new Random();

	public void TreatSignal(Signal x) {
		//System.out.println("QS: " + x.signalType);
		switch (x.signalType) {

			case ARRIVAL: {
				if (slump.nextDouble() < priorityChance) {
					qPriority.add(time);
				} else {
					qRegular.add(time);
				}
				if (qPriority.size() + qRegular.size() == 1) {
					SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
				}
			}
				break;

			case READY: {
				noReady++;
				double qTime;
				if (qPriority.size() > 0) {
					qTime = time - qPriority.remove();
					accPriority += qTime;
					noPriority++;
				} else {
					qTime = time - qRegular.remove();
					accRegular += qTime;
					noRegular++;
				}
				if(qTime > maxQTime) {
					maxQTime = qTime;
				}
				if (sendTo != null) {
					SignalList.SendSignal(ARRIVAL, sendTo, time);
				}
				if (qPriority.size() + qRegular.size() > 0) {
					SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
				}
			}
				break;

			//case MEASURE: {
			//	noMeasurements++;
			//	accumulated = accumulated + numberInQueue;
			//	SignalList.SendSignal(MEASURE, this, time + 2 * slump.nextDouble());
			//}
			//	break;
		}
	}
}