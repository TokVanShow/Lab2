package Main;

import Excel.ExcelExport;
import Excel.ExcelReader;
import Excel.Storage;
import calculations.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Manager {

    private final Storage storage;
    private final ExcelReader excelReader;
    private String selectedFilePath; // New class variable to store the selected file path

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
            storage.performCalculations();
        }
    }

    public void loadExcelFile(String path) {
        excelReader.setFile(path);
        try {
            excelReader.loadFile();
            storage.setExcelLists(excelReader.readFile());
        } catch (IOException | InvalidFormatException e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
        }

    }

    public void exportToExcel(String filePath) {
        ExcelExport excelExport = new ExcelExport(storage);
        excelExport.exportToExcel(filePath);
        // Store the selected file path for use in showResultsFile
        selectedFilePath = filePath;
    }

    public void showResultsFile() {
        if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
            File file = new File(selectedFilePath);
            try {
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Файл не найден");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при открытии файла: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный аргумент: " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.out.println("Операция не поддерживается: " + e.getMessage());
            }
        } else {
            System.out.println("Файл не был выбран для экспорта");
        }
    }

}
