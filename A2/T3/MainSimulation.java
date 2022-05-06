package T3;

import java.io.*;
import java.util.ArrayList;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	State actState = new State();

		State.SAVINGS_LEVEL = 5000;
		
		ArrayList<Integer> results = new ArrayList<>();
		int simulationRuns = 0;
		double confWidth = 0;

		while (confWidth >= 2.0 || simulationRuns < 50) {

			// Reset simulation env
			actState.reset();
			eventList = new EventListClass();
			time = 0;
			insertEvent(NEW_YEAR, 0);
			insertEvent(NEW_MONTH, 0);
			
			// The main simulation loop
			while (State.savings < 2000000){
				actEvent = eventList.fetchEvent();
				time = actEvent.eventTime;
				actState.treatEvent(actEvent);
			}

			results.add(actState.nbrOfMonths);
			simulationRuns++;
			confWidth = calculateConfidenceWidth(results);
		}
    	// results
    	writeResults(results, "./A2/T3/results/rate_" + actState.SAVINGS_LEVEL);
		double sum = 0.0;
		for (int num : results) {
			sum += num;
		}
		double mean = sum / results.size();
		System.out.println(mean);
    }

	public static void writeResults(ArrayList<Integer> results, String filename) throws IOException {
		// Create result.txt file
		new File(filename).createNewFile();

		// Write results to results.txt
		FileWriter myWriter = new FileWriter(filename);
		String result;
		for (int num : results) {
			result = Integer.toString(num) + "\n";
			myWriter.write(result);
		}
		myWriter.close();
	}

	// borrowed from RobertG @ https://stackoverflow.com/questions/30340551/java-calculate-confidence-interval
	private static double calculateConfidenceWidth(ArrayList<Integer> numbers) {
		// calculate mean
		double sum = 0.0;
		for (int num : numbers) {
			sum += num;
		}
		double mean = sum / numbers.size();
	
		// calculate standard deviation
		double squaredDifferenceSum = 0.0;
		for (int num : numbers) {
			squaredDifferenceSum += (num - mean) * (num - mean);
		}
		double variance = squaredDifferenceSum / numbers.size();
		double standardDeviation = Math.sqrt(variance);
	
		// value for 95% confidence interval, source: https://en.wikipedia.org/wiki/Confidence_interval#Basic_Steps
		double confidenceLevel = 1.96;
		double width = confidenceLevel * standardDeviation / Math.sqrt(numbers.size());
		return width;
	}
}