package jdbc_prepared_statment_crud.controller;

import jdbc_prepared_statment_crud.dao.CustomerDao;
import jdbc_prepared_statment_crud.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();

        System.out.println("Customer\n1. Insert\n2. Delete\n3. update\n4. Display All\n5. Display by id");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Chooses :");
        int chooses = scanner.nextInt();

        switch (chooses){
            case 1:
                // insert Customer
                System.out.print("Enter Customer Id : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Customer Name : ");
                String name = scanner.nextLine();
                System.out.print("Enter Customer Email : ");
                String email = scanner.nextLine();
                System.out.print("Enter Customer Address : ");
                String address = scanner.nextLine();

                Customer customer = new Customer(id,name,email,address);
                Customer customer1 = customerDao.saveCustomerDao(customer);
                if (customer1 != null){
                    System.out.println("Successfully Insert");
                }
                break;
            case 2:
                // Delete Customer
                System.out.print("Enter Customer : ");
                int id_ = scanner.nextInt();
                int a = customerDao.deleteCustomerByIdDao(id_);
                String result = a!=0?"Deleted Successfully":"Not Successfully";
                System.out.println(result);
                break;
            case 3:
                // update Customer
                System.out.print("Enter Customer Id : ");
                int id1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n✔️✔️ Update Details of customer id is "+id1+" ✔️✔️\n");
                System.out.print("Enter Customer Name : ");
                String name1 = scanner.nextLine();
                System.out.print("Enter Customer Email : ");
                String email1 = scanner.nextLine();
                System.out.print("Enter Customer Address : ");
                String address1 = scanner.nextLine();

                Customer customer_ = new Customer(name1,email1,address1);
                Customer customer0 = customerDao.updateCustomerByIdDao(customer_,id1);
                System.out.println(customer0);
                break;
            case 4:
                // Display All Customer
                List<Customer> customerList= customerDao.getAllCustomerDao();
                for (Customer c : customerList){
                    System.out.println(c);
                }
                break;
            case 5:
                // Display Customer by id.
                System.out.print("Enter Customer id : ");
                int id__ = scanner.nextInt();
                Customer customer2 = customerDao.getCustomerById(id__);
                System.out.println(customer2);
                break;

        }
        scanner.close();

    }

}
