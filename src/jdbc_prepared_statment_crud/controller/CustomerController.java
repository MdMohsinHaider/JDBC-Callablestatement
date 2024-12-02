package jdbc_prepared_statment_crud.controller;

import jdbc_prepared_statment_crud.dao.CustomerDao;
import jdbc_prepared_statment_crud.entity.Customer;

import java.util.Scanner;

public class CustomerController {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer Id ");

        Customer customer = customerDao.saveCustomerDao(new Customer(1232,"Mohsin Haider","MohsinHaider@gamil.com","Muz"));
        System.out.println(customer);
    }

}
