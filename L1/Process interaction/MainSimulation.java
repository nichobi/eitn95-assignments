import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation


public class MainSimulation extends Global {

  public static void main(String[] args) throws IOException {
    // Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.
    // The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
    // signal list in the main loop below.

    Signal actSignal;
    new SignalList();
    int nbrOfQueues = 3;

    //H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.
    // Here process instances are created (two queues and one generator) and their parameters are given values. 

    ArrayList<QS> queues = new ArrayList<QS>();
    for(int i = 0; i < nbrOfQueues; i++) {
      QS Q = new QS();
      if(i == 0) {
        Q.sendTo = null;
      } else {
        Q.sendTo = queues.get(i - 1);
      }
      queues.add(Q); 
    }
    QS Q1 = queues.get(queues.size() - 1);

    /* Used for exercise e) in Lab 1, comment out otherwise */

    Gen Generator = new Gen();
    Generator.lambda = 9; // Generator ska generera nio kunder per sekund  //Generator shall generate 9 customers per second
    Generator.sendTo = Q1; // De genererade kunderna ska skickas till k�systemet QS  // The generated customers shall be sent to Q1

    // H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
    // To start the simulation the first signals are put in the signal list

    SignalList.SendSignal(READY, Generator, time);
    for(QS Q : queues) {
      SignalList.SendSignal(MEASURE, Q, time);
    }

    // Detta �r simuleringsloopen:
    // This is the main loop

    while (time < 100000){
      actSignal = SignalList.FetchSignal();
      time = actSignal.arrivalTime;
      actSignal.destination.TreatSignal(actSignal);
    }

    //Slutligen skrivs resultatet av simuleringen ut nedan:
    //Finally the result of the simulation is printed below:
    for(int i = 0; i < nbrOfQueues; i++) {
      QS Q = queues.get(i);
      int queueNumber = nbrOfQueues - i;
      String str = "Mean number of customers in queuing system Q" + queueNumber + ": ";
      System.out.println(str + 1.0*Q.accumulated/Q.noMeasurements);
    }
  }
}