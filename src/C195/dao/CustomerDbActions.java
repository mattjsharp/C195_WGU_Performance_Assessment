package C195.dao;

import C195.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author LabUser
 */
public interface CustomerDbActions {

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
    
    default HashMap<Integer, Integer> getCustomerDivisionList() {
        HashMap<Integer, Integer> customerAmountMap = new HashMap<>();
        String sql = "SELECT COUNT(Customer_ID) AS customerCount, Division_ID FROM customers GROUP BY Division_ID;";
        
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                customerAmountMap.put(rs.getInt("Division_ID"), rs.getInt("customerCount"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return customerAmountMap;
    }

    /**
     *
     * @param customer
     * @return
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
