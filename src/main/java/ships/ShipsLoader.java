package ships;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShipsLoader {

    public static List<Ship> loadExelShips(String fileName) {
        List<Ship> shipList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new File(fileName))) {
            Sheet sheet = workbook.getSheetAt(0);
            String name, type;
            double speed;
            List<String> weapons = new ArrayList<>();
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                try {
                    if (row.getLastCellNum() < 3)
                        throw new Exception("Wrong number of arguments");
                    name = row.getCell(0).getStringCellValue();
                    type = row.getCell(1).getStringCellValue();
                    try {
                        speed = row.getCell(2).getNumericCellValue();
                    } catch (Exception e) {
                        if ("Infinity".equals(row.getCell(2).getStringCellValue()))
                            speed = Double.POSITIVE_INFINITY;
                        else throw new Exception("Wrong speed format");
                    }
                    String weapon;
                    for (int j = 3; j < row.getLastCellNum(); j++) {
                        try {
                            if (!(weapon = row.getCell(j).getStringCellValue()).isBlank())
                                weapons.add(weapon);
                        } catch (Exception e) {
                            System.out.println(e.getClass().getName() + ": " + e.getMessage());
                        }
                    }
                    shipList.add(new Ship(name, type, speed, weapons));
                    weapons.clear();
                }
                catch (Exception e) {
                    weapons.clear();
                    System.out.println(e.getClass().getName() + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return shipList;
    }
}
