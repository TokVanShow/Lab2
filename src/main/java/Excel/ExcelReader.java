package Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public List readFile() {
    XSSFSheet sheet = workbook.getSheetAt(0); // Выбираем первый лист

    List<Double> column1 = new ArrayList<>();
    List<Double> column2 = new ArrayList<>();
    List<Double> column3 = new ArrayList<>();

    Iterator<Row> rowIterator = sheet.iterator();
    rowIterator.next(); // Пропускаем заголовок

    while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        
        Cell cell1 = row.getCell(0);
        Cell cell2 = row.getCell(1);
        Cell cell3 = row.getCell(2);

        if (cell1 != null && cell1.getCellType() == CellType.NUMERIC) {
            column1.add(cell1.getNumericCellValue());
        } else {
            column1.add(0.0); // Добавляем 0, если значение не числовое
        }

        if (cell2 != null && cell2.getCellType() == CellType.NUMERIC) {
            column2.add(cell2.getNumericCellValue());
        } else {
            column2.add(0.0);
        }

        if (cell3 != null && cell3.getCellType() == CellType.NUMERIC) {
            column3.add(cell3.getNumericCellValue());
        } else {
            column3.add(0.0);
        }
    }

    System.out.println("Столбец 1: " + column1.toString());
    System.out.println("Столбец 2: " + column2.toString());
    System.out.println("Столбец 3: " + column3.toString());
    return(column1);
}
}
