package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Variation_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] coefficients = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
            for (Double num : samples[i]) {
                descriptiveStatistics.addValue(num);
            }
            coefficients[i] = descriptiveStatistics.getStandardDeviation() / descriptiveStatistics.getMean() * 100.0;
        }

        System.out.println("Коэффициенты вариации для каждой выборки: " + java.util.Arrays.toString(coefficients));
        return coefficients;
    }
}