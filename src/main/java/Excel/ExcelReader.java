package Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private File file;
    private XSSFWorkbook workbook;


    public void setFile(String path) {
        this.file = new File(path);
    }

    public void loadFile() throws IOException, InvalidFormatException {
        this.workbook = new XSSFWorkbook(file);
    }

    public void run(Storage storage) {
        try {
            loadFile();
            storage.setExcelLists(readFile());
            close();
        } catch (IOException | InvalidFormatException e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
        }
    }

    private void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing Excel file: " + e.getMessage());
        }
    }

    public ArrayList<ArrayList<Double>> readFile() {
        XSSFSheet sheet = workbook.getSheetAt(0); // Выбираем первый лист

        ArrayList<ArrayList<Double>> columns = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Пропускаем заголовок
        } else {
            System.out.println("The Excel file is empty");
            return columns;
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            for (int i = 0; i < row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    if (columns.size() <= i) {
                        columns.add(new ArrayList<>());
                    }
                    columns.get(i).add(cell.getNumericCellValue());
                } else {
                    if (columns.size() <= i) {
                        columns.add(new ArrayList<>());
                    }
                    columns.get(i).add(0.0); // Добавляем 0, если значение не числовое
                }
            }
        }

        for (int j = 0; j < columns.size(); j++) {
            System.out.println("Столбец " + (j + 1) + ": " + columns.get(j).toString());
        }
        System.out.println("");
        return columns;
    }
}