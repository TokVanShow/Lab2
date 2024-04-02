package Excel;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import calculations.*;
import java.io.IOException;

public class ExcelExport {
    
    public static void exportToExcel(Storage storage, String path) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Calculation Results");
        
        String[] headers = {"Sample Name", "Geometric Mean", "Arithmetic Mean", "Standard Deviation"};

        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
        
        String[] calculationTypes = {"Geometric Mean", "Arithmetic Mean", "Standard Deviation"};
        for (int j = 0; j < storage.getExcelLists().size(); j++) {
            row = sheet.createRow(rowNum++);
            for (int k = 0; k < calculationTypes.length; k++) {
                Cell cell = row.createCell(k);
                cell.setCellValue(calculationTypes[k]);
                cell = row.createCell(k + 1);
                switch (k) {
                    case 0 -> storage.setStatCalc(new Geometric_Mean_Calculator());
                    case 1 -> storage.setStatCalc(new Arithmetic_Mean_Calculator());
                    case 2 -> storage.setStatCalc(new Standard_Deviation_Calculator());
                    default -> {
                    }
                }
                storage.performCalculations();
                for (double result : storage.getResults()) {
                    cell = row.createCell(k + 1);
                    cell.setCellValue(result);
                }
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.write(fileOut);
        } catch (Exception e) {
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
            }
        }
    }
}