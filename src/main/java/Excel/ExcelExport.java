package Excel;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelExport {

    public static void exportToExcel(Storage storage, String path) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Calculation Results");

        ArrayList<ArrayList<Double>> excelLists = storage.getExcelLists();
        String[] calculationTypes = {"Geometric Mean", "Arithmetic Mean", "Standard Deviation"};
        int rowNum = 0;

        // Create the header with sample names
        Row headerRow = sheet.createRow(rowNum++);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Samples");

        for (int j = 0; j < excelLists.size(); j++) {
            headerCell = headerRow.createCell(j + 1);
            headerCell.setCellValue("Sample_" + (j + 1));
        }

        // Fill the table with calculation results
        for (String type : calculationTypes) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(type);

            ArrayList<Double> results = storage.getSavedResults(type);

            for (int j = 0; j < excelLists.size(); j++) {
                if (j < results.size()) {
                    cell = row.createCell(j + 1);
                    cell.setCellValue(results.get(j));
                }
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}