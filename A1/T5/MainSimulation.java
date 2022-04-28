package T5;

import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	Signal actSignal;
    	new SignalList();

    	Gen generator = new Gen();
    	generator.lambda = 1.0/2.0;

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.balanceAlgo = LOAD_BALANCE;

    	generator.sendTo = dispatcher;

    	SignalList.SendSignal(READY, generator, time);
    	SignalList.SendSignal(MEASURE, dispatcher, time);

    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:
    	//Finally the result of the simulation is printed below:

    	System.out.println("Mean number of jobs in queuing system: " + 1.0*dispatcher.accumulated/dispatcher.noMeasurements);
    	System.out.println("Mean number of active queues: " + 1.0*((double) dispatcher.activeAccum )/dispatcher.noMeasurements/5);

    }
}