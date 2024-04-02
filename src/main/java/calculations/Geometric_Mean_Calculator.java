
package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;


public class Geometric_Mean_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double> sample1, ArrayList<Double> sample2, ArrayList<Double> sample3) {
        GeometricMean geometricMean = new GeometricMean();
        double[] results = new double[3];
        ArrayList<ArrayList<Double>> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);
        samples.add(sample3);

        for (int i = 0; i < 3; i++) {
            double[] values = samples.get(i).stream().mapToDouble(Double::doubleValue).toArray();
            results[i] = geometricMean.evaluate(values);
        }
        
        return results;
    }
}