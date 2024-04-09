package Excel;

import calculations.Stat_Calc;
import java.util.ArrayList;


public class Storage {

    public ArrayList<ArrayList<Double>> excelLists;
    private Stat_Calc calculationStrategy;
    private final ArrayList<Double> calculationResults;

    public Storage() {
        this.excelLists = new ArrayList<>();
        this.calculationStrategy = null;
        this.calculationResults = new ArrayList<>();
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

        ArrayList<Double>[] samples = new ArrayList[excelLists.size()];

        for (int i = 0; i < excelLists.size(); i++) {
            samples[i] = excelLists.get(i);
        }

        double[] currentResults = calculationStrategy.stat_Calc(samples);

        for (double result : currentResults) {
            calculationResults.add(result);
        }
        System.out.println("");
    }

    public ArrayList<Double> getCalculationResults() {
        return calculationResults;
    }
}