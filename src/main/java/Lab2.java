
import Excel.ExcelReader;


public class Lab2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ExcelReader excel = new ExcelReader("src\\main\\java\\Excel\\ДЗ4.xlsx");
        excel.run();
    }
}
