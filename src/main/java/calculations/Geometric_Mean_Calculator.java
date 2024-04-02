package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;

public class Geometric_Mean_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        GeometricMean geometricMean = new GeometricMean();
        double[] results = new double[samples.length];
        
        for (int i = 0; i < samples.length; i++) {
            double[] values = samples[i].stream().mapToDouble(Double::doubleValue).toArray();
            results[i] = geometricMean.evaluate(values);
        }
        
        return results;
    }
}