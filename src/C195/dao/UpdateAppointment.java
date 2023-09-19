package C195.dao;

import java.sql.PreparedStatement;

/**
 *
 * @author LabUser
 */
public interface UpdateAppointment {
    default boolean updateAppointment(String title, String description, String location, String type, String startDate, String endDate, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId, int id) {
        String updateSql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(updateSql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, startDate);
            ps.setString(6, endDate);
            ps.setString(7, lastUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, id);
            
            ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
