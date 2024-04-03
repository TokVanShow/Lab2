package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.stat.correlation.Covariance;

public class Covariance_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        Covariance covariance = new Covariance();
        double[] covariances = new double[samples.length];

        for (int i = 0; i < samples.length; i++) {
            for (int j = i + 1; j < samples.length; j++) {
                double[] values1 = samples[i].stream().mapToDouble(Double::doubleValue).toArray();
                double[] values2 = samples[j].stream().mapToDouble(Double::doubleValue).toArray();
                
                double covarianceValue = covariance.covariance(values1, values2);
                
                // Записываем значение ковариации для пары (i, j) и (j, i), чтобы учесть обратные зависимости
//                covariances[i * samples.length + j] = covarianceValue;
           //     covariances[j * samples.length + i] = covarianceValue;
            }
        }

        System.out.println("Коэффициенты ковариации для всех пар случайных чисел: " + java.util.Arrays.toString(covariances));
        return covariances;
    }
}