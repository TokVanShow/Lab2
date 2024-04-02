
package calculations;



import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;

public class Standard_Deviation_Calculator implements Stat_Calc {
    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] results = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
            for (Double value : samples[i]) {
                descriptiveStatistics.addValue(value);
            }
            results[i] = descriptiveStatistics.getStandardDeviation();
        }
        return results;
    }
}