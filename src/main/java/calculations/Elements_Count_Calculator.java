package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class Elements_Count_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] counts = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            SummaryStatistics stats = new SummaryStatistics();
            for (Double num : samples[i]) {
                stats.addValue(num);
            }
            counts[i] = stats.getN();
        }

        System.out.println("Количество элементов в каждой выборке: " + java.util.Arrays.toString(counts));
        return counts;
    }
}