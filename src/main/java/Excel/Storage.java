package Excel;

import Main.Calculator;
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

//    public void performCalculations(Calculator calculator) {
//        calculator.performCalc(this);
//    }
    public void storeResults(double[] currentResults) {
        for (double result : currentResults) {
            calculationResults.add(result);
        }
    }

    public ArrayList<Double>[] getData() {
        ArrayList<Double>[] samples = new ArrayList[excelLists.size()];

        for (int i = 0; i < excelLists.size(); i++) {
            samples[i] = excelLists.get(i);
        }

        return samples;
    }

    public ArrayList<Double> getCalculationResults() {
        return calculationResults;
    }
}
