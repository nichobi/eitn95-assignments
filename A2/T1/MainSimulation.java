package T1;

import java.io.*;
import java.util.Scanner;

public class MainSimulation extends GlobalSimulation {
	public static void main(String[] args) throws IOException {
		State actState = new State();
		actState.setVariables(1000, 100, 8, 1, 1000);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Which subtask should we run?");
		int task = scanner.nextInt();

		// Default values
		switch (task) {
			case 1:
				break;

			case 2:
				actState.setX(10);
				actState.setLambda(80);
				break;

			case 3:
				actState.setX(200);
				actState.setLambda(4);
				break;

			case 4:
				actState.setVariables(100, 10, 4, 4, 1000);
				break;

			case 5:
				actState.setVariables(100, 10, 4, 1, 4000);
				break;

			case 6:
				actState.setVariables(100, 10, 4, 4, 4000);
				break;

			default:
				scanner.close();
				return;
		}

		String filepath = "./A2/T1/results/";
		String filename = filepath + "Task-" + Integer.toString(task) + ".txt";
		runSimulation(actState, filename);
		scanner.close();
	}

	public static void runSimulation(State actState, String filename) {
		Event actEvent;
		// Some events must be put in the event list at the beginning
		insertEvent(ARRIVAL, 0);
		insertEvent(MEASURE, T);

		// The main simulation loop
		while (time < M * T) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}

		// Create file results.txt
		try {
			writeResults(actState, filename);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	public static void writeResults(State actState, String filename) throws IOException {
		// Create result.txt file
		new File(filename).createNewFile();

		// Write results to results.txt
		FileWriter myWriter = new FileWriter(filename);
		String result;
		for (int i = 0; i < M; i++) {
			result = Integer.toString(i) + ", " +
					Integer.toString(actState.results[i]) + "\n";
			myWriter.write(result);
		}
		myWriter.close();
	}
}