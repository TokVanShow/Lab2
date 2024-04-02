package Excel;

import calculations.Stat_Calc;
import java.util.ArrayList;

public class Storage {

    private ArrayList<ArrayList<Double>> excelLists;
    private Stat_Calc calculationStrategy;
    private double[] results;

    public Storage() {
        this.excelLists = new ArrayList<>();
        this.calculationStrategy = null;
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

        results = calculationStrategy.stat_Calc(samples);
        System.out.println("Calculated results: ");
        for (double result : results) {
            System.out.println(result);
        }
    }

    // Добавленные методы
    public ArrayList<ArrayList<Double>> getExcelLists() {
        return excelLists;
    }

    public double[] getResults() {
        return results;
    }
}