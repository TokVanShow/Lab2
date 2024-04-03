package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class Variance_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] variances = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            Variance varianceCalculator = new Variance();
            variances[i] = varianceCalculator.evaluate(samples[i].stream().mapToDouble(Double::doubleValue).toArray());
        }

        System.out.println("Оценка дисперсии для каждой выборки: " + java.util.Arrays.toString(variances));
        return variances;
    }
}