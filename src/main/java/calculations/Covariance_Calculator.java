package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.correlation.Covariance;

public class Covariance_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        int sampleCount = samples.length;
        double[] covariances = new double[sampleCount * sampleCount];
        Covariance covariance = new Covariance();

        int index = 0;
        for (int i = 0; i < sampleCount; i++) {
            for (int j = 0; j < sampleCount; j++) {
                double[] values1 = samples[i].stream().mapToDouble(Double::doubleValue).toArray();
                double[] values2 = samples[j].stream().mapToDouble(Double::doubleValue).toArray();
                double covarianceValue = covariance.covariance(values1, values2);

                covariances[index++] = covarianceValue;


                System.out.println("Ковариация между выборками " + i + " и " + j + ": " + covarianceValue);
            }
        }
        return covariances;
    }
}