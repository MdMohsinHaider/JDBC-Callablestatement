package jdbc_prepared_statment_crud.dao;

import jdbc_prepared_statment_crud.connection.CustomerConnection;
import jdbc_prepared_statment_crud.entity.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDao {
    Connection connection = CustomerConnection.getConnection();

    public Customer saveCustomerDao(Customer customer){
        try {
            CallableStatement callableStatement = connection.prepareCall("call saveCustomerDetails(?,?,?,?)");
            callableStatement.setInt(1,customer.getId());
            callableStatement.setString(2,customer.getName());
            callableStatement.setString(3,customer.getEmail());
            callableStatement.setString(4,customer.getAddress());

            int a = callableStatement.executeUpdate();
            return a!=0?customer:null;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // deleteCustomerById
    public int deleteCustomerByIdDao(int id){
        try {
            CallableStatement callableStatement = connection.prepareCall("call deleteCustomerById(?)");
            callableStatement.setInt(1,id);
            return callableStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }
}
