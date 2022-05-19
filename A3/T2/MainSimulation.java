package T2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainSimulation extends Global {
	private static final String RESULTS_FOLDER_PATH = "A3/T2/results/";
	private static String resultsFile = RESULTS_FOLDER_PATH + "results.txt";

	public static void main(String[] args) throws IOException {
		ArrayList<Double> timeResults = new ArrayList<>();
		HashMap<Integer, Integer> meetingDistr = new HashMap<>();
		HashMap<Integer, Integer> timeTalkedDistr = new HashMap<>();

		initResultsFile();

		while (Util.calculateConfidenceWidth(timeResults) > 30 || timeResults.size() < 100) {
			Global.eventList = new EventListClass();
			ArrayList<Person> persons = run();
			timeResults.add(time);
			int interactionTime;
			for (Person p : persons) {
				interactionTime = 0;
				for (int time : p.interactions.values()) {
					// How many times you talk to a certain person
					// compared to talking to other people
					if (meetingDistr.get(time) != null) {
						meetingDistr.put(time, meetingDistr.get(time) + 1);
					} else {
						meetingDistr.put(time, 1);
					}

					interactionTime += time;
				}

				// How many total hours you have talked
				if (timeTalkedDistr.get(interactionTime) != null) {
					timeTalkedDistr.put(interactionTime, meetingDistr.get(interactionTime) + 1);
				} else {
					timeTalkedDistr.put(interactionTime, 1);
				}
			}
			//System.out.println("Iteration " + Integer.toString(timeResults.size()) +
			//		": ----- Conf width: " + Util.calculateConfidenceWidth(timeResults));
		}
		
		double total = 0;
		for(double time : timeResults) {
			total += time;
		}
		System.out.println("Mean: " + total / timeResults.size());
		System.out.println("Conf width: " + Util.calculateConfidenceWidth(timeResults));
		System.out.println("meeting distr2: " + meetingDistr.toString());
		writeResults(meetingDistr, resultsFile);
		writeResults(timeTalkedDistr, resultsFile + ".time");
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

		System.out.println("Enter file name to write results to (press enter for results.txt)");
		try {
			String str = sc.nextLine();
			if(str.length() != 0) {
				resultsFile = RESULTS_FOLDER_PATH + str;
			}
			sc.close();
		
		} catch(NoSuchElementException e) {
			sc.close();
			return;
		} 
	}

	private static void writeResults(HashMap<Integer, Integer> results, String path) {
		try {
			FileWriter fw = new FileWriter(path);
			for(Integer i : results.keySet()) {
				fw.write(Integer.toString(i) + ", " + Integer.toString(results.get(i)) + "\n");
			}
			fw.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}