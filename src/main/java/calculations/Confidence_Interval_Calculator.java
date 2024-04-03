package calculations;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Confidence_Interval_Calculator implements Stat_Calc {

    @Override
    public double[] stat_Calc(ArrayList<Double>... samples) {
        double[] confidenceIntervals = new double[samples.length];
        
        double alpha = 0.05; // Уровень значимости (для 95% доверительного интервала)
        
        for (int i = 0; i < samples.length; i++) {
            double mean = samples[i].stream().mapToDouble(Double::doubleValue).average().orElse(0.0); // Среднее значение выборки
            double stdDev = new NormalDistribution().getNumericalVariance(); // Оценка стандартного отклонения
            
            int n = samples[i].size(); // Размер выборки
            double criticalValue = new TDistribution(n-1).inverseCumulativeProbability(1.0 - alpha/2); // Критическое значение
            
            double marginOfError = criticalValue * (stdDev / Math.sqrt(n)); // Предел погрешности
            confidenceIntervals[i] = mean - marginOfError; // Нижняя граница доверительного интервала
        }

        System.out.println("Доверительные интервалы для математического ожидания каждой выборки: " + java.util.Arrays.toString(confidenceIntervals));
        return confidenceIntervals;
    }
}