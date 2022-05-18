package T2;

import java.util.*;

public class Util {
	private static Random rand = new Random();

	// borrowed from RobertG @
	// https://stackoverflow.com/questions/30340551/java-calculate-confidence-interval
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

		// value for 95% confidence interval, source:
		// https://en.wikipedia.org/wiki/Confidence_interval#Basic_Steps
		double confidenceLevel = 1.96;
		double width = confidenceLevel * standardDeviation / Math.sqrt(numbers.size());
		return width;
	}

	public static int generateDirection(Person p) {
		/* Check if the person is outside of the room */
		if (p.getX() < 0) {
			if (p.getY() < 0) {
				return 1; // Go north east
			} else if (p.getY() > Global.L) {
				return 3; // Go south east
			} else {
				return rand.nextInt(3) + 1; // Go east
			}
		} else if (p.getX() > Global.L) {
			if (p.getY() < 0) {
				return 7; // Go north west
			} else if (p.getY() > Global.L) {
				return 5; // Go south west
			} else {
				return rand.nextInt(3) + 5; // Go west
			}
		} else if (p.getY() < 0) {
			// X is now guaranteed to be in bounds
			return (rand.nextInt(3) + 7) % 8; // Go north
		} else if (p.getY() > Global.L) {
			return rand.nextInt(3) + 3; // Go south
		}

		// Inside the room, go in any direction
		return rand.nextInt(8);
	}

	public static int generateDirection() {
		return rand.nextInt(8);
	}

	public static double getExponential(double mean) {
		double lambda = 1 / mean;
		return Math.log(1 - rand.nextDouble()) / (-lambda);
	}
}