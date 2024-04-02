package Excel;

import calculations.Stat_Calc;
import java.util.ArrayList;

public class Storage {

    public ArrayList<ArrayList<Double>> excelLists;
    private Stat_Calc calculationStrategy;
    private ArrayList<Double> calculationResults;
    private ArrayList<ArrayList<ArrayList<Double>>> savedResults = new ArrayList<>();

    public Storage() {
        this.excelLists = new ArrayList<>();
        this.calculationStrategy = null;
        this.calculationResults = new ArrayList<>();
        this.savedResults = new ArrayList<>();
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

        System.out.println("Calculated results: ");
        for (double result : currentResults) {
            System.out.print(calculationStrategy);
            System.out.println(result);
        }
    }

    public ArrayList<Double> getCalculationResults() {
        return calculationResults;
    }

    public void saveResults(int calculationType, int sampleIndex, ArrayList<Double> results) {
        while (savedResults.size() <= sampleIndex) {
            savedResults.add(new ArrayList<>());
        }

        while (savedResults.get(sampleIndex).size() <= calculationType) {
            savedResults.get(sampleIndex).add(new ArrayList<>());
        }

        savedResults.get(sampleIndex).set(calculationType, results);
    }

public ArrayList<Double> getSavedResults(String type, int sampleIndex) {
    if (sampleIndex < savedResults.size() && sampleIndex >= 0) {
        ArrayList<ArrayList<Double>> sampleResults = savedResults.get(sampleIndex);

        if (type.equals("Geometric Mean")) {
            return sampleResults.get(0);
        } else if (type.equals("Arithmetic Mean")) {
            return sampleResults.get(1);
        } else if (type.equals("Standard Deviation")) {
            return sampleResults.get(2);
        }
    }
    return new ArrayList<>();
}

}

