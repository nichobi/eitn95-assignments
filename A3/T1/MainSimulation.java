package T1;

import java.util.*;

import java.io.FileInputStream;

public class MainSimulation extends Global {
    public static void main(String[] args) {
        Signal actSignal;
    	new SignalList();
        Properties config = new Properties();
        

        // Read config
        try {
            String path = "A3/T1/1000.conf";
            FileInputStream fis = new FileInputStream(path);
            config.load(fis);
            fis.close();
        } catch (Exception e){};

        int n = Integer.parseInt(config.getProperty("n"));
        double Tp =  Double.parseDouble(config.getProperty("Tp"));
        double ts = Double.parseDouble(config.getProperty("ts"));
        double radius = Double.parseDouble(config.getProperty("r"));
        
        GatewayProcess gateway = new GatewayProcess();
        Sensor[] sensors = new Sensor[n];
        for (int i = 0; i < n; i++) {
            String[] coords = config.getProperty(Integer.toString(i)).split(" ");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            sensors[i] = new Sensor(i, x, y, gateway, radius, ts, Tp);
            SignalList.SendSignal(TRANSMIT, sensors[i], sensors[i], time);
        }
        
        System.out.println(Util.withinRange(6195.730920653288, 3342.4517005214507, 3316.754474493253, 2870.808063299285, 7000));
    	SignalList.SendSignal(MEASURE, gateway, gateway, time+6000);
        while (confWidth > 0.005 || time < 20000) {
            actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
        }
        System.out.println("Confidence interval width = " + confWidth);
        System.out.println("Throughput(?) = " + (double)gateway.nbrSuccessful/gateway.nbrTotal);
        System.out.println("Packet loss prob = " + (1 - (double)gateway.nbrSuccessful/gateway.nbrTotal));

    }
}