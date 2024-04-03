package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Extremum_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] results = new double[samples.length * 2]; // Умножаем на 2 для хранения как минимумов, так и максимумов

        int index = 0;
        for (ArrayList<Double> sample : samples) {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
            for (Double value : sample) {
                descriptiveStatistics.addValue(value);
            }
            results[index] = descriptiveStatistics.getMin();
            results[index + samples.length] = descriptiveStatistics.getMax();
            index++;
        }

        System.out.println("Минимумы и максимумы для каждой выборки: " + java.util.Arrays.toString(results));
        return results;
    }
}