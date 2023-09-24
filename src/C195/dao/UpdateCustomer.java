package C195.dao;

import java.sql.PreparedStatement;

/**
 *
 * @author LabUser
 */
public interface UpdateCustomer {
    default boolean updateCustomer(String name, String address, String postalCode, String phone, String lastUpdate, String lastUpdatedBy, int divisionId, int id) {
        String updateSql = "UPDATE customers SET Customer_NAME = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update, Last_Updated_By, Division_ID = ? WHERE Customer_ID = ?";
        
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
            
            System.out.println(ps);
            
            ps.executeUpdate();
            return true;           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
