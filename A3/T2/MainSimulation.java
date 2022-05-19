package T2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainSimulation extends Global {
	private static String resultsFile = GenerateSimulation.FOLDER_PATH + "results.txt";

	public static void main(String[] args) throws IOException {
		ArrayList<Double> timeResults = new ArrayList<>();
		HashMap<Integer, Integer> meetingDistr = new HashMap<>();
		int meetingTime[] = new int[20];

		initResultsFile();

		while (Util.calculateConfidenceWidth(timeResults) > 50 || timeResults.size() < 100) {
			Global.eventList = new EventListClass();
			ArrayList<Person> persons = run();
			timeResults.add(time);
			for (Person p : persons) {
				for (int time : p.interactions.values()) {
					meetingTime[p.getId()] += time;
					if (meetingDistr.get(time) != null) {
						meetingDistr.put(time, meetingDistr.get(time) + 1);
					} else {
						meetingDistr.put(time, 1);
					}
				}
			}
			//System.out.println("Iteration " + Integer.toString(timeResults.size()) + ": ----- Conf width: " + Util.calculateConfidenceWidth(timeResults));
		}
		
		double total = 0;
		for(double time : timeResults) {
			total += time;
		}
		System.out.println("Mean: " + total / timeResults.size());
		System.out.println("Conf width: " + Util.calculateConfidenceWidth(timeResults));
		System.out.println("meeting distr2: " + meetingDistr.toString());
		writeResults(meetingDistr);
	}

	public static ArrayList<Person> run() {
		State actState = new State();
		Event actEvent;
		time = 0;
		// Some events must be put in the event list at the beginning
		insertEvent(MOVE, 0);
		insertEvent(MEASURE, 0);

		// The main simulation loop
		while (actState.field.peopleDone() < Global.STUDENTS) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}
		return actState.field.people;
	}

	private static void initResultsFile() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter file name to write results to:");
		try {
			String str = sc.nextLine();
			if(str.length() != 0) {
				resultsFile = GenerateSimulation.FOLDER_PATH + str;
			}
			sc.close();
		
		} catch(NoSuchElementException e) {
			sc.close();
			return;
		} 
	}

	private static void writeResults(HashMap<Integer, Integer> results) {
		try {
			FileWriter fw = new FileWriter(resultsFile);
			for(Integer i : results.keySet()) {
				fw.write(Integer.toString(i) + ", " + Integer.toString(results.get(i)) + "\n");
			}
			fw.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}