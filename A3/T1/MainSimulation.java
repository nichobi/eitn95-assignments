package T1;

import java.util.*;
import Process.*;

public class MainSimulation extends Global {

    //private createSensorNodes() {  }
    
    public static void main(String[] args) {

        Signal actSignal;
    	new SignalList();
        Gen Generator = new Gen();

        SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);

        double confWidth = 0;
        int noMeasurments = 0;
        ArrayList<Double> throughPutMeasurements = new ArrayList<>();
        while (confWidth >= 100 || noMeasurments < 50) {
            actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
            confWidth = Util.calculateConfidenceWidth(throughPutMeasurements);
        }
    }
}