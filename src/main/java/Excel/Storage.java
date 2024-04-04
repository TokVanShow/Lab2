package Excel;

import calculations.Stat_Calc;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    public ArrayList<ArrayList<Double>> excelLists;
    private Stat_Calc calculationStrategy;
    private ArrayList<Double> calculationResults;
  private HashMap<String, ArrayList<Double>> savedResults; 

    public Storage() {
        this.excelLists = new ArrayList<>();
        this.calculationStrategy = null;
        this.calculationResults = new ArrayList<>();
        this.savedResults = new HashMap<>();
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
        for (double result : currentResults) {
//            System.out.print(calculationStrategy);
//            System.out.println(result);
            
        }
    }

    public ArrayList<Double> getCalculationResults() {
        return calculationResults;
    }

public void saveResults(String calculationType, ArrayList<Double> results) {
    savedResults.put(calculationType, results);
}

public ArrayList<Double> getSavedResults(String calculationType) {
    return savedResults.getOrDefault(calculationType, new ArrayList<>());
}

}

