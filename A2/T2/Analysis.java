package T2;

import java.util.List;
import java.util.stream.Collectors;

public class Analysis {
    public static double sum(List<Double> list) {
        return(list.stream().reduce(0.0 , (x, y) -> x + y));
    }
    public static double mean(List<Double> list) {
        return(sum(list)/list.size());
    }
    public static double stdVariance(List<Double> list) {
        double mean = mean(list);
        List<Double> variances = list.stream().map(x -> Math.pow(x-mean, 2.0)).collect(Collectors.toList());
        return Math.sqrt(mean(variances));
    }

    public static double[] confidenceInterval(List<Double> list) {
        double mean = mean(list);
        double diff = 1.960*stdVariance(list)/Math.sqrt(list.size());
        return new double[]{mean - diff, mean + diff};
    }
}
