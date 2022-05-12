package T1;

import java.util.*;

public class Util {
    private static Random rand = new Random();
    // borrowed from RobertG @ https://stackoverflow.com/questions/30340551/java-calculate-confidence-interval
	public static double calculateConfidenceWidth(ArrayList<Double> numbers) {
		// calculate mean
		double sum = 0.0;
		for (double num : numbers) {
			sum += num;
		}
		double mean = sum / numbers.size();
	
		// calculate standard deviation
		double squaredDifferenceSum = 0.0;
		for (double num : numbers) {
			squaredDifferenceSum += (num - mean) * (num - mean);
		}
		double variance = squaredDifferenceSum / numbers.size();
		double standardDeviation = Math.sqrt(variance);
	
		// value for 95% confidence interval, source: https://en.wikipedia.org/wiki/Confidence_interval#Basic_Steps
		double confidenceLevel = 1.96;
		double width = confidenceLevel * standardDeviation / Math.sqrt(numbers.size());
		return width;
	}

    public static double getExponential(double mean) {
		double lambda = 1/mean;
		return Math.log(1 - rand.nextDouble())/(-lambda);
	}
}