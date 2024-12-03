package jdbc_prepared_statment_crud.dao;

import jdbc_prepared_statment_crud.connection.CustomerConnection;
import jdbc_prepared_statment_crud.entity.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
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

    // Update Customer by id
    public Customer updateCustomerByIdDao(Customer customer,int id){
        try {
            CallableStatement callableStatement = connection.prepareCall("call updateCustomerById(?,?,?,?)");
            callableStatement.setInt(1,id);
            callableStatement.setString(2,customer.getName());
            callableStatement.setString(3,customer.getEmail());
            callableStatement.setString(4,customer.getAddress());

            int a = callableStatement.executeUpdate();
            return a!=0?customer:null;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // getAllCustomer()
    public List<Customer> getAllCustomerDao(){
        try {
            CallableStatement callableStatement = connection.prepareCall("call getAllCustomer()");
            ResultSet resultSet = callableStatement.executeQuery();
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id,name,email,address);
                customerList.add(customer);
            }
            return customerList;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    // get Customer by id
    public Customer getCustomerById(int idCustomer){
        try {
            CallableStatement callableStatement = connection.prepareCall("call getCustomerById(?)");
            callableStatement.setInt(1,idCustomer);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                return new Customer(id,name,email,address);
            }
            else
                return null;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
