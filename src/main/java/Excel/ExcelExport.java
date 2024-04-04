package Excel;

import calculations.Covariance_Calculator;
import calculations.Stat_Calc;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelExport {

    private final ArrayList<ArrayList<Double>> excelLists;
    private final ArrayList<Double> calculationResults;

    public ExcelExport(Storage storage) {

        this.excelLists = storage.getExcelLists();
        this.calculationResults = storage.getCalculationResults();
    }

    public void exportToExcel(String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet calculationResultsSheet = workbook.createSheet("Calculations Results");
            Sheet covarianceMatrixSheet = workbook.createSheet("Covariance Matrix");

            writeResultsToFirstSheet(calculationResultsSheet);
            Stat_Calc covarianceCalculator = new Covariance_Calculator();
            writeCovarianceMatrixToSheet(covarianceCalculator, covarianceMatrixSheet);

            try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
                workbook.write(fileOut);
            }
            System.out.println("Excel file exported successfully!");
        } catch (IOException e) {
            System.err.println("Error exporting Excel file: " + e.getMessage());
        }
    }

    public void writeResultsToFirstSheet(Sheet sheet) {
        // Writing sample names in the first row starting from the second cell
        Row firstRow = sheet.createRow(0);
        for (int i = 0; i < excelLists.size(); i++) {
            Cell cell = firstRow.createCell(i + 1);
            cell.setCellValue("Sample_" + (i + 1));
        }

        // Writing method names in the first column starting from the second row
        String[] methodNames = {"Method1", "Method2", "Method3", "Method4", "Method5", "Method6", "Method7", "Method8", "Method9", "Method10"};
        for (int j = 0; j < methodNames.length; j++) {
            Row row = sheet.createRow(j + 1);
            Cell methodCell = row.createCell(0);
            methodCell.setCellValue(methodNames[j]);

            // Writing calculation results in each row starting from the second cell
            for (int k = 0; k < excelLists.size(); k++) {
                Cell valueCell = row.createCell(k + 1);
                if (j * excelLists.size() + k < calculationResults.size()) {
                    valueCell.setCellValue(calculationResults.get(j * excelLists.size() + k));
                }
            }
        }
    }

    private void writeCovarianceMatrixToSheet(Stat_Calc covarianceCalculator, Sheet sheet) {
        {
            double[] covarianceValues = covarianceCalculator.stat_Calc(excelLists.toArray(ArrayList[]::new));
            int matrixSize = (int) Math.sqrt(covarianceValues.length);

            // Add row and column labels for sample names
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0); // empty cell in the top-left corner
            for (int i = 0; i < matrixSize; i++) {
                Cell cell = firstRow.createCell(i + 1);
                cell.setCellValue("Sample_" + (i + 1));
            }

            for (int i = 0; i < matrixSize; i++) {
                Row row = sheet.createRow(i + 1); // Start from the second row
                Cell labelCell = row.createCell(0);
                labelCell.setCellValue("Sample_" + (i + 1));
                for (int j = 0; j < matrixSize; j++) {
                    Cell cell = row.createCell(j + 1);
                    cell.setCellValue(covarianceValues[i * matrixSize + j]);
                }
            }

            autoSizeColumns(sheet, matrixSize + 1); // Increase the column count for the labels
        }
    }

    private void autoSizeColumns(Sheet sheet, int columns) {
        for (int i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
