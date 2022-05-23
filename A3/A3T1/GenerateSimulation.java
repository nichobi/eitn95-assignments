package A3T1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenerateSimulation {

    private final static String CONFIG_PATH = "A3/T1/.config";
    private static Properties config;
    private static double[] coordinates;
    private static Random random = new Random();

    private static void loadConfig(String path) throws IOException {
        config = new Properties();
		if (path != null) {
			FileInputStream fis = new FileInputStream(path);
			config.load(fis);
			fis.close();
		}
    }
    public static void main(String[] args) {
        try {
            loadConfig(CONFIG_PATH);

            for (String n : config.getProperty("n").split(" ")) {
                FileWriter fw = new FileWriter("A3/T1/" + n + ".conf");
                fw.write("n = " + n + "\n");
                fw.write("Tp = " + config.getProperty("Tp") + "\n");
                fw.write("ts = " + config.getProperty("ts") + "\n");
                fw.write("r = "  + config.getProperty("r")  + "\n");
                coordinates = generateCoordinates(Integer.parseInt(n));
                saveCoordinates(fw);
                fw.close();
            }
        } catch(Exception err) {
            err.printStackTrace();
        };
        
    }
    
    private static double[] generateCoordinates(int n) {
        coordinates = new double[2*n];
        for (int i = 0; i < 2*n; i++) {
            coordinates[i] = random.nextDouble()*10000;
        }
        return coordinates;
    }

    private static void saveCoordinates(FileWriter fw) {
        int n = coordinates.length/2;
        try {
            for(int i = 0; i < n; i++)  {
                fw.write(Integer.toString(i) + "=" + coordinates[2*i] + " " + coordinates[2*i+1] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}