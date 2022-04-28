package T4;

import java.io.*;

//Denna klass ärver Global så att man kan använda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation
public class MainSimulation extends Global {
	public static void main(String[] args) throws IOException {

		// Signallistan startas och actSignal deklareras. actSignal är den senast
		// utplockade signalen i huvudloopen nedan.
		// The signal list is started and actSignal is declared. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.

		Signal actSignal;
		new SignalList();

		// Här nedan skapas de processinstanser som behövs och parametrar i dem ges
		// värden.
		// Here process instances are created (two queues and one generator) and their
		// parameters are given values.

		QS Q1 = new QS();
		Q1.sendTo = null;
		Q1.priorityChance = 0.5;
		Q1.lambda = 1.0/(4*60);

		Gen Generator = new Gen();
		Generator.lambda = 1.0/(5*60); // Generator ska generera med medeltid 5 minuter
		Generator.sendTo = Q1; // De genererade kunderna ska skickas till kösystemet QS // The generated
								// customers shall be sent to Q1

		// Här nedan skickas de första signalerna för att simuleringen ska komma igång.
		// To start the simulation the first signals are put in the signal list

		SignalList.SendSignal(READY, Generator, time);
		//SignalList.SendSignal(MEASURE, Q1, time);

		// Detta är simuleringsloopen:
		// This is the main loop

		while (Q1.noReady < 20) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			//System.out.println("Time: " + time);
			actSignal.destination.TreatSignal(actSignal);
		}

		// Slutligen skrivs resultatet av simuleringen ut nedan:
		// Finally the result of the simulation is printed below:

		//System.out.println("Mean number of customers in queuing system: " + 1.0 * Q1.accumulated / Q1.noMeasurements);
		System.out.println("Chance of priority customer: " + Q1.priorityChance);
		System.out.println("Mean queueing time, regular: " + roundToOneDec(1.0 * Q1.accRegular / Q1.noRegular/60.0) + " minutes");
		System.out.println("Mean queueing time, priority: " + roundToOneDec(1.0 * Q1.accPriority / Q1.noPriority/60.0) + " minutes");
		System.out.println("Max queueing time: " + roundToOneDec(Q1.maxQTime/60) + " minutes");

	}

	private static double roundToOneDec(double value) {
		return Math.round(value * 10.0) / 10.0;
	}
}