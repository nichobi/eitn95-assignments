
import java.util.*;

class Dispatcher extends Proc{
  Random slump = new Random();
  int roundRobinIndex;
  ArrayList<QS> qs = new ArrayList<QS>();
  int queueSize = 5;
  int balanceAlgo = 1;
  int accumulated = 0, noMeasurements = 0;
  int activeAccum = 0;

  public Dispatcher() {
    for(int i = 0; i < queueSize; i++) {
      qs.add(new QS());
    }
  }
  
	public void TreatSignal(Signal x){
		switch (x.signalType){
			case ARRIVAL:{
                QS receiver = qs.get(0);
                switch(balanceAlgo) {
                    case RANDOM: {
                        receiver = randomQueue();
                    }
                    case ROUND_ROBIN: {
                        receiver = roundRobin();
                    }
                    case LOAD_BALANCE: {
                        receiver = activeLoadBalance();
                    }
                }
                SignalList.SendSignal(ARRIVAL, receiver, time);
			} break;

      case MEASURE: {
          noMeasurements++;
          accumulated += totalCustomers();
          activeAccum += activeQueues();
          //System.out.println(accumulated);
          SignalList.SendSignal(MEASURE, this, time + slump.nextDouble() * 100);
        } break;
		}
	}

  private QS roundRobin() {
    roundRobinIndex = (roundRobinIndex + 1) % queueSize;
    return qs.get(roundRobinIndex);
  }

  private QS randomQueue() {
    return qs.get(slump.nextInt(queueSize));
  }

  private QS activeLoadBalance() {
    QS min = qs.get(0);
    for(QS queue : qs) {
      if(min.numberInQueue > queue.numberInQueue) {
        min = queue;
      }
    }
    return min;
  }
  
  private int totalCustomers() {
    int total = 0;
    for(QS queue : qs) {
      total += queue.numberInQueue;
    }
    return total;
  }

  private int activeQueues() {
    int total = 0;
    for(QS queue : qs) {
      if(queue.numberInQueue > 0) total++;
    }
    return total;
  }
}
