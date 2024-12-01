# JDBC-Callablestatement

# JDBC CallableStatement and Stored Procedure Notes  

This document provides an overview of how to use **JDBC CallableStatement** to work with stored procedures in a relational database.  

---

## Example Stored Procedure  

Below is an example of a stored procedure in MySQL:  

```sql
DELIMITER $$  
CREATE PROCEDURE GetEmployeeDetails(IN empId INT, OUT empName VARCHAR(50))  
BEGIN  
    SELECT name INTO empName FROM employees WHERE id = empId;  
END $$  
DELIMITER ;  
```
## Example Java Code
Here is a complete example demonstrating how to call the above stored procedure using CallableStatement in Java:

```
import java.sql.*;

public class CallableStatementExample {
    public static void main(String[] args) {
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", "username", "password");

            // Prepare the callable statement
            CallableStatement callableStatement = connection.prepareCall("{call GetEmployeeDetails(?, ?)}");

            // Set input parameter
            callableStatement.setInt(1, 101); // Replace 101 with your employee ID

            // Register output parameter
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve output parameter
            String empName = callableStatement.getString(2);
            System.out.println("Employee Name: " + empName);

            // Close resources
            callableStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```
# Key Steps Summary
1. Create the stored procedure in your database.
2. Use CallableStatement in Java to call the procedure.
3. Set IN parameters and register OUT parameters.
4. Execute the statement and process the results.
5. Close all resources to prevent resource leaks.
