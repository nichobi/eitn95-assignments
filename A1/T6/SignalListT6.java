package T6;

public class SignalListT6 {
	private  static SignalT6 list, last;

	SignalListT6 (){
    	list = new SignalT6();
    	last = new SignalT6();
    	list.next = last;
	}

	public static void SendSignal(int type, Component dest, double arrtime){
		SignalT6 dummy, predummy;
		SignalT6 newSignal = new SignalT6();
		newSignal.signalType = type;
		newSignal.destination = dest;
		newSignal.arrivalTime = arrtime;
		predummy = list;
		dummy = list.next;
		while ((dummy.arrivalTime < newSignal.arrivalTime) & (dummy != last)){
			predummy = dummy;
			dummy = dummy.next;
		}
		predummy.next = newSignal;
		newSignal.next = dummy;
	}

	public static SignalT6 FetchSignal(){
		SignalT6 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}

class SignalT6 {
	public Component destination;
	public double arrivalTime;
	public int signalType;
	public SignalT6 next;
}
