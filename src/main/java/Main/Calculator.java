package Main;

import Excel.Storage;
import calculations.Stat_Calc;
import java.util.ArrayList;

public class Calculator {

    private Stat_Calc calculationStrategy;

    public void setCalculationStrategy(Stat_Calc calculationStrategy) {
        this.calculationStrategy = calculationStrategy;
    }

    public void performCalc(Storage storage) {
        ArrayList<Double>[] samples = storage.getData();

        double[] currentResults = calculationStrategy.stat_Calc(samples);

        storage.storeResults(currentResults);
    }
}
