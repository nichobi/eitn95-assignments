package T1;

public class GlobalSimulation {
	// This class contains the definition of the events that shall take place in the
	// simulation. It also contains the global time, the event list and also a method
	// for insertion of events in the event list. That is just for making the code in
	// MainSimulation.java and State.java simpler (no dot notation is needed).

	public static final int ARRIVAL = 1, READY = 2, MEASURE = 3; // The events, add or remove if needed!
	public static double time = 0; // The global time variable
	public static int N = 1000, X = 100, LAMBDA = 8, T = 1, M = 1000;
	public static EventListClass eventList = new EventListClass(); // The event list used in the program

	public static void insertEvent(int type, double TimeOfEvent) { // Just to be able to skip dot notation
		eventList.InsertEvent(type, TimeOfEvent);
	}

	public void setN(int N) {
		GlobalSimulation.N = N;
	}

	public void setX(int X) {
		GlobalSimulation.X = X;
	}

	public void setLambda(int LAMBDA) {
		GlobalSimulation.LAMBDA = LAMBDA;
	}

	public void setT(int T) {
		GlobalSimulation.T = T;
	}

	public void setM(int M) {
		GlobalSimulation.M = M;
	}

	public void setVariables(int N, int X,
			int LAMBDA, int T, int M) {
		GlobalSimulation.N = N;
		GlobalSimulation.X = X;
		GlobalSimulation.LAMBDA = LAMBDA;
		GlobalSimulation.T = T;
		GlobalSimulation.M = M;
	}
}