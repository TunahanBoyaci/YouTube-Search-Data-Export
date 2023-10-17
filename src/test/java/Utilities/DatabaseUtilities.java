package Utilities;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilities {
    static Connection connection;
    public static Statement statement;

    @BeforeTest
    public static void DBConnectionCreate() {
        String url = "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com:3306/sakila";

        String username = "root";
        String password = "'\"-LhCB'.%k[4S]z";
        try {
            connection = DriverManager.getConnection(url, username, password); // Creates a connection to the database
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }
    }

    @AfterTest
    public static void DBConnectionClose() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    public static List<List<String>> getData(String query) {
        List<List<String>> table = new ArrayList<>();
        DBConnectionCreate();
        ResultSet rs;
        try {
            rs = statement.executeQuery(query);
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    row.add(columnValue);
                }
                table.add(row);
            }
        } catch (SQLException e) {
            System.out.println("e.getMessage() = " + e.getMessage());

        }
        DBConnectionClose();
        return table;
    }

    public static void main(String[] args) {
        List<List<String>> list = getData("select name from states");
        for (List<String> innerList: list){
            for (String state: innerList){
                System.out.println("state = " + state);
            }
        }
    }
}
