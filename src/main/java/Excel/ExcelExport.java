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

        // Сначала создадим заголовок с названиями выборок
        Row headerRow = sheet.createRow(rowNum++);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Samples");
        for (int j = 0; j < excelLists.size(); j++) {
            headerCell = headerRow.createCell(j + 1);
            headerCell.setCellValue("Sample_" + (j + 1));
        }

        // Затем заполним таблицу с результатами калькуляций
        for (String type : calculationTypes) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(type);

            for (int j = 0; j < excelLists.size(); j++) {
                ArrayList<Double> results = storage.getSavedResults(type, j);

                cell = row.createCell(j + 1);
                if (!results.isEmpty()) {
                    cell.setCellValue(results.get(0)); // Для примера, здесь используется только первое значение
                } else {
                    cell.setCellValue("N/A"); // Записываем пустую ячейку, если результат отсутствует
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
