package Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
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

    private void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing Excel file: " + e.getMessage());
        }
    }

    public ArrayList<ArrayList<Double>> readSheetByIndex(int sheetIndex) {
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex - 1);
        return readSheet(sheet);
    }

    public ArrayList<ArrayList<Double>> readSheetByName(String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            JOptionPane.showMessageDialog(null, "Ошибка: Лист Excel с именем " + sheetName + " не существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return readSheet(sheet);
    }

    public ArrayList<ArrayList<Double>> readSheet(XSSFSheet sheet) {
        ArrayList<ArrayList<Double>> columns = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        boolean firstRowSkipped = false;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (!firstRowSkipped) {
                firstRowSkipped = true;
                continue;
            }

            for (int i = 0; i < row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    while (columns.size() <= i) {
                        columns.add(new ArrayList<>());
                    }
                    columns.get(i).add(cell.getNumericCellValue());
                } else {
                    while (columns.size() <= i) {
                        columns.add(new ArrayList<>());
                    }
                }
            }
        }

        close(); // Вызов метода закрытия листа после завершения чтения

        for (int j = 0; j < columns.size(); j++) {
            System.out.println("Column " + (j + 1) + ": " + columns.get(j).toString());
        }
        System.out.println("");

        return columns;
    }

    public int getTotalSheets() {
        if (workbook != null) {
            return workbook.getNumberOfSheets();
        }
        return 0;
    }
}
