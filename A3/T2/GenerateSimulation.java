package T2;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenerateSimulation {
    public static final String FOLDER_PATH = "A3/T2/conf/";
    private final static String CONFIG_PATH = FOLDER_PATH + ".config";
    public static String CUSTOM_CONFIG_PATH = FOLDER_PATH + "default.conf";
    private static Properties config;
    private static double[] coordinates;
    private static Random random = new Random();
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Press enter for default input");
        System.out.println("Enter config file name:");
        String str = sc.nextLine();
        if(!str.equals("")) {
            CUSTOM_CONFIG_PATH = FOLDER_PATH + str;
        }
        sc.close();

        try {
            loadConfig(CONFIG_PATH);

            FileWriter fw = new FileWriter(CUSTOM_CONFIG_PATH);
            fw.write("t = " + config.getProperty("t") + "\n");
            fw.write("v = " + config.getProperty("v") + "\n");
            coordinates = generateCoordinates(Global.STUDENTS);
            saveCoordinates(fw);
            fw.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private static void loadConfig(String path) throws IOException {
        config = new Properties();
        if (path != null) {
            FileInputStream fis = new FileInputStream(path);
            config.load(fis);
            fis.close();
        }
    }

    private static double[] generateCoordinates(int n) {
        coordinates = new double[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            coordinates[i] = random.nextDouble() * Global.L;
        }
        return coordinates;
    }

    private static void saveCoordinates(FileWriter fw) {
        int n = coordinates.length / 2;
        try {
            for (int i = 0; i < n; i++) {
                fw.write(Integer.toString(i) + Global.EQUALS + "[" + coordinates[2 * i] +
                        Global.COMMA + coordinates[2 * i + 1] + "]\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}