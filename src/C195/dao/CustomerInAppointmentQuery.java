package C195.dao;

import C195.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LabUser
 */
public interface CustomerInAppointmentQuery {

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
}
