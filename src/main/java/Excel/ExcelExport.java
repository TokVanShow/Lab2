package Excel;

import calculations.Covariance_Calculator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import calculations.Stat_Calc;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExport {
    
    public static void exportToExcel(Storage storage, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet calculationResultsSheet = workbook.createSheet("Calculations Results");
            Sheet covarianceMatrixSheet = workbook.createSheet("Covariance Matrix");

            ArrayList<Double> calculationResults = storage.getCalculationResults();
            ArrayList<ArrayList<Double>> excelLists = storage.getExcelLists();

            // Write calculation results to the first sheet
            int rownum = 0;
            for (double result : calculationResults) {
                Row row = calculationResultsSheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result);
            }

            // Write covariance matrix to the second sheet
            Stat_Calc covarianceCalculator = new Covariance_Calculator();
            double[] covarianceValues = covarianceCalculator.stat_Calc(excelLists.toArray(new ArrayList[0]));

            int matrixSize = (int) Math.sqrt(covarianceValues.length); // Calculate the size of covariance matrix dynamically

            for (int i = 0; i < matrixSize; i++) {
                Row row = covarianceMatrixSheet.createRow(i);
                for (int j = 0; j < matrixSize; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(covarianceValues[i * matrixSize + j]);
                }
            }

            // Auto-size columns for better appearance
            for (int i = 0; i < matrixSize; i++) {
                covarianceMatrixSheet.autoSizeColumn(i);
            }

            // Write the Excel file
            try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
                workbook.write(fileOut);
            }
            System.out.println("Excel file exported successfully!");
        } catch (IOException e) {
            System.err.println("Error exporting Excel file: " + e.getMessage());
        }
    }
}