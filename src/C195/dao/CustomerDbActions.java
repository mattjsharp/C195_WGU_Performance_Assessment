package C195.dao;

import C195.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface containing default methods to perform actions relating to customers on the client_schedule database.
 * 
 * @author mattjsharp
 */
public interface CustomerDbActions {

    /**
     * Queries the client_schedule database to retrieve every customer record.
     * 
     * @return A list of Customer objects representing every customer from the database.
     */
    default List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID")
                )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }
    
    /**
     * Provides a list of all customers that are located in a particular country.
     * 
     * @param countryId The country ID representing the country to check.
     * @return A list of Customer objects representing every customer from a certain country.
     */
    default List<Customer> getCustomersByCountry(int countryId) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE Division_ID IN (SELECT Division_ID FROM first_level_divisions WHERE Country_ID = ?);";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID")
                )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    /**
     * Checks if a customer is present in any appointments.
     * 
     * @param customer the customer to check.
     * @return The count of appointments a particular customer is scheduled in.
     */
    default int customerInAppointment(Customer customer) {
        int count = 0;
        String sql = "SELECT * FROM appointments WHERE customer_ID = ?;";

        PreparedStatement ps;
        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return count;
    }

    /**
     * Deletes particular customer record from the client_schedule database.
     * 
     * @param customerId The ID of the customer to be deleted.
     * @return A boolean value representing the result of the operation.
     */
    default boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE Customer_ID = " + customerId + ";";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Inserts a new customer record into the client_schedule database based on the values provided by the arguments.
     * 
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param creationDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     * @return A boolean value representing a value based on the result.
     */
    default boolean insertCustomer(String name, String address, String postalCode, String phone, String creationDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionId) {
        String insertSql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(insertSql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setString(5, creationDate);
            ps.setString(6, createdBy);
            ps.setString(7, lastUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, divisionId);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Updates a particular customer on the client_schedule database based on the ID and the other arguments provided.
     * 
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     * @param id
     * @return A boolean value representing a value based on the result.
     */
    default boolean updateCustomer(String name, String address, String postalCode, String phone, String lastUpdate, String lastUpdatedBy, int divisionId, int id) {
        String updateSql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(updateSql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setString(5, lastUpdate);
            ps.setString(6, lastUpdatedBy);
            ps.setInt(7, divisionId);
            ps.setInt(8, id);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
