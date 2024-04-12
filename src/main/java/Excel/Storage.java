package Excel;

import calculations.Stat_Calc;
import java.util.ArrayList;

public class Storage {

    private ArrayList<ArrayList<Double>> excelLists;
    private Stat_Calc calculationStrategy;
    private final ArrayList<Double> calculationResults;

    public Storage() {
        excelLists = new ArrayList<>();
        calculationStrategy = null;
        calculationResults = new ArrayList<>();
    }

    public ArrayList<ArrayList<Double>> getExcelLists() {
        return excelLists;
    }

    public void setExcelLists(ArrayList<ArrayList<Double>> excelLists) {
        this.excelLists = excelLists;
    }

    public void setStatCalc(Stat_Calc calculationStrategy) {
        this.calculationStrategy = calculationStrategy;
    }

    public void performCalculations() {
        if (calculationStrategy == null) {
            System.out.println("Error: Calculation strategy not set.");
            return;
        }

        for (ArrayList<Double> sample : excelLists) {
            double[] results = calculationStrategy.stat_Calc(sample);
            for (double result : results) {
                calculationResults.add(result);
            }
        }
    }

    public ArrayList<Double> getCalculationResults() {
        return calculationResults;
    }
}
