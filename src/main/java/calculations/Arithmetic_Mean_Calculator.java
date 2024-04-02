package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Arithmetic_Mean_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] results = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
            for (Double value : samples[i]) {
                descriptiveStatistics.addValue(value);
            }
            results[i] = descriptiveStatistics.getMean();
        }
        return results;
    }
}
