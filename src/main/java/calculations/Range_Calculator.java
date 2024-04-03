package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.StatUtils;

public class Range_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] ranges = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            double[] values = samples[i].stream().mapToDouble(Double::doubleValue).toArray();
            double max = StatUtils.max(values);
            double min = StatUtils.min(values);
            ranges[i] = max - min;
        }

        System.out.println("Размах выборок= " + java.util.Arrays.toString(ranges));
        return ranges;
    }
}