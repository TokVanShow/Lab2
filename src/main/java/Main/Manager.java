package Main;

import Excel.ExcelExport;
import Excel.ExcelReader;
import Excel.Storage;
import calculations.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Manager {

    private Storage storage;
    private ExcelReader excelReader;
    private String selectedFilePath;

    public Manager() {
        this.storage = new Storage();
        this.excelReader = new ExcelReader();
    }

 public void performCalculations() {
        Stat_Calc[] calculators = {
            new Geometric_Mean_Calculator(),
            new Arithmetic_Mean_Calculator(),
            new Standard_Deviation_Calculator(),
            new Range_Calculator(),
            new Elements_Count_Calculator(),
            new Variation_Calculator(),
            new Confidence_Interval_Calculator(),
            new Variance_Calculator(),
            new Extremum_Calculator()
        };

        for (Stat_Calc calculator : calculators) {
            storage.setStatCalc(calculator);
            try {
                storage.performCalculations();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Empty list in calculations: " + e.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void exportToExcel(String filePath) {
        ExcelExport excelExport = new ExcelExport(storage);
        excelExport.exportToExcel(filePath);
        selectedFilePath = filePath;
    }

    public void showResultsFile() {
        if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
            File file = new File(selectedFilePath);
            try {
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(null, "File not found", "File Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error opening the file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Invalid argument: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedOperationException e) {
                JOptionPane.showMessageDialog(null, "Operation not supported: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected for export", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadExcelSheetByIndex(String filePath, int sheetIndex) {
        excelReader.setFile(filePath);
        try {
            excelReader.loadFile();
            ArrayList<ArrayList<Double>> excelData = excelReader.readSheetByIndex(sheetIndex);

            if (sheetIndex < excelData.size()) { // Check if there is data for the specified index
                storage.setExcelLists(excelData);
            } else {
                JOptionPane.showMessageDialog(null, "Index out of bounds: " + sheetIndex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InvalidFormatException e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
        }
    
    }

    public void loadExcelSheetByName(String filePath, String sheetName) {
        excelReader.setFile(filePath);
        try {
            excelReader.loadFile();
            storage.setExcelLists(excelReader.readSheetByName(sheetName));
        } catch (IOException | InvalidFormatException e) {
            JOptionPane.showMessageDialog(null, "Error processing Excel file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
