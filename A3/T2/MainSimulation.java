package T2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSimulation extends Global {
	private static String resultsFile = GenerateSimulation.FOLDER_PATH + "results.txt";
	public static void main(String[] args) throws IOException {
		run();
	}

	public static void run() {
		State actState = new State();
		Event actEvent;
		// Some events must be put in the event list at the beginning
		insertEvent(MOVE, 0);
		insertEvent(MEASURE, 0);

		initResultsFile();

		// The main simulation loop
		while (actState.field.peopleDone() < Global.STUDENTS) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
			System.out.println("Time: " + Double.toString(time));
			System.out.println("People done: " + Integer.toString(actState.field.peopleDone()));
		}

		writeResults(actState.results);
		System.out.println("Done");
	}

	private static void initResultsFile() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter file name to write results to:");
		String str = sc.nextLine();
		if(str.length() != 0) {
			resultsFile = GenerateSimulation.FOLDER_PATH + str;
		}
		sc.close();
	}

	private static void writeResults(ArrayList<Integer> results) {
		try {
			FileWriter fw = new FileWriter(resultsFile);
			for(Integer i : results) {
				fw.write(Double.toString(time) + ", " + Integer.toString(i) + "\n");
			}
			fw.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}