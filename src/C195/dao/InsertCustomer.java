package C195.dao;

import java.sql.PreparedStatement;

/**
 *
 * @author LabUser
 */
public interface InsertCustomer {
    
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
            
            System.out.println(ps);
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
