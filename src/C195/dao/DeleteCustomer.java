package C195.dao;

import java.sql.PreparedStatement;

/**
 *
 * @author LabUser
 */
public interface DeleteCustomer {
    default boolean  deleteCustomer(int customerId) {
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
}
