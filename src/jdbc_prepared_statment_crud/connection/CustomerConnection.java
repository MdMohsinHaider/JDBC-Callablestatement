package jdbc_prepared_statment_crud.connection;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class CustomerConnection {
    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/DATABASE_NAME","USERNAME","USER PASSWORD");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
