package Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private File file;
    private XSSFWorkbook workbook;

    public ExcelReader(String path) {
        setFile(path);
    }

    private void setFile(String path) {
        this.file = new File(path);
    }

    public void loadFile() {
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        loadFile();
        readFile();
        close();
    }

    private void close() {
        try {
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ArrayList<Double>> readFile() {
        XSSFSheet sheet = workbook.getSheetAt(0); // Выбираем первый лист

        ArrayList<ArrayList<Double>> columns = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Пропускаем заголовок

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

        return columns;
    }
}
