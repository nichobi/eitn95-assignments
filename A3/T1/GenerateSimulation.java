package T1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GenerateSimulation {

    private final static String CONFIG_PATH = ".config";
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
        loadConfig(CONFIG_PATH);
        coordinates = generateCoordinates(n); // Error
        saveCoordinates();
    }
    
    private static double[] generateCoordinates(int n) {
        coordinates = new double[2*n];
        for (int i = 0; i < 2*n; i++) {
            coordinates[i] = random.nextDouble()*10000;
        }
        return coordinates;
    }

    private static void saveCoordinates() {
    }
}