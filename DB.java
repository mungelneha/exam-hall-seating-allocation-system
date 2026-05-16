import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    public static Connection getConnection() {

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/examdb",
                    "root",
                    "Neha@2411" // 👉 CHANGE this to your MySQL password
            );

            System.out.println("Database Connected Successfully ✅");
            return con;

        } catch (Exception e) {
            System.out.println("Connection Failed ❌");
            e.printStackTrace();
            return null;
        }
    }
}