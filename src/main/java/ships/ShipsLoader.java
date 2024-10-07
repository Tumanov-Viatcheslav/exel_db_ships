package ships;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.sql.*;
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

    public static void createShipTable() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db_ships";
        String username = "postgres";
        String password = "123";

        try {
            // Register the PostgreSQL driver
            //Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Interaction with DB
            try {
                String createTable = "create table ships (\n" +
                        "  ship_id serial primary key, \n" +
                        "  ship_name VARCHAR (50) NOT NULL, \n" +
                        "  ship_type VARCHAR (50) NOT NULL, \n" +
                        "  ship_speed real NOT NULL, \n" +
                        "  weapons VARCHAR (255)\n" +
                        ")";
                Statement statement = connection.createStatement();
                statement.executeQuery(createTable);
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }


            String sql = "insert into ships (ship_name, ship_type, ship_speed, weapons) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "name1");
            preparedStatement.setString(2, "type1");
            preparedStatement.setDouble(3, 10);
            preparedStatement.setString(4, "weapon1,weapon2,weapon3");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "name2");
            preparedStatement.setString(2, "type2");
            preparedStatement.setDouble(3, 100);
            preparedStatement.setString(4, "weapon1,weapon2,weapon3");
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "name3");
            preparedStatement.setString(2, "type3");
            preparedStatement.setDouble(3, 10.5);
            preparedStatement.setString(4, "weapon2,weapon3");
            preparedStatement.executeUpdate();

            // Close the connection
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static List<Ship> loadDBShips() {
        List<Ship> shipList = new ArrayList<>();
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db_ships";
        String username = "postgres";
        String password = "123";

        try {
            // Register the PostgreSQL driver
            //Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Interaction with DB
            String sql = "select * from ships";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String shipName = result.getString("ship_name");
                String shipType = result.getString("ship_type");
                double speed = result.getDouble("ship_speed");
                List<String> weapons = List.of(result.getString("weapons").split(","));
                try {
                    shipList.add(new Ship(shipName, shipType, speed, weapons));
                } catch (Exception e) {
                    System.out.println(e.getClass().getName() + ": " + e.getMessage());
                }
            }
            // Close the connection
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return shipList;
    }
}
