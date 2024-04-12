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
            storage.performCalculations();
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
                    showErrorMessage("Файл не найден");
                }
            } catch (IOException | IllegalArgumentException | UnsupportedOperationException e) {
                showErrorMessage("Ошибка открытия файла: " + e.getMessage());
            }
        } else {
            showErrorMessage("Для экспорта не выбран файл");
        }
    }

    public void loadExcelSheetByIndex(String filePath, int sheetIndex) {
        try {
            excelReader.setFile(filePath);
            excelReader.loadFile();
            int totalSheets = excelReader.getTotalSheets();

            if (sheetIndex >= 0 && sheetIndex < totalSheets) {
                ArrayList<ArrayList<Double>> excelData = excelReader.readSheetByIndex(sheetIndex);
                storage.setExcelLists(excelData);
            } else {
                showErrorMessage("Недопустимый номер листа: " + sheetIndex);
            }
        } catch (IOException | InvalidFormatException e) {
            showErrorMessage("Ошибка обработки файла Excel: " + e.getMessage());
        }
    }

    public void loadExcelSheetByName(String filePath, String sheetName) {
        excelReader.setFile(filePath);
        try {
            excelReader.loadFile();
            storage.setExcelLists(excelReader.readSheetByName(sheetName));
        } catch (IOException | InvalidFormatException e) {
            showErrorMessage("Ошибка обработки файла Excel: " + e.getMessage());
        }
    }

    public void handleIndexSelection(String selectedFilePath, String input) {
        if (input != null && !input.isEmpty()) {
            try {
                int sheetIndex = Integer.parseInt(input);
                loadExcelSheetByIndex(selectedFilePath, sheetIndex);
            } catch (NumberFormatException e) {
                showErrorMessage("Введите корректное число для номера листа.");
            }
        } else {
            showErrorMessage("Ошибка: Номер листа не введен.");
        }
    }

    public void handleNameSelection(String selectedFilePath, String sheetName) {
        if (sheetName != null && !sheetName.isEmpty()) {
            loadExcelSheetByName(selectedFilePath, sheetName);
        } else {
            showErrorMessage("Ошибка: Имя листа не введено.");
        }
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

}
