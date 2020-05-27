package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {

    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        JDBCExecutor jdbcExecutor = new JDBCExecutor();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            System.out.println(order);

            /*CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("Farnaz");
            customer.setLastName("Mozaffari");
            customer.setEmail("farnazsm72@gmail.com");
            customer.setAddress("1234 Main St");
            customer.setCity("Toronto");
            customer.setState("VA");
            customer.setPhone("(555) 555-9845");
            customer.setZipCode("12345");
            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail("abcd@gmail.com");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);
            customerDAO.delete(dbCustomer.getId());*/

        } catch (SQLException e) {
            jdbcExecutor.logger.error(e.getMessage(), e);

        }
    }

}