/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package C195.dao;

import java.sql.PreparedStatement;

/**
 *
 * @author LabUser
 */
public interface InsertAppointment {

    default boolean insertAppointment(String title, String description, String location, String type, String startDate, String endDate, String creationDate, String createdBy, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        String insertSql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(insertSql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, startDate);
            ps.setString(6, endDate);
            ps.setString(7, creationDate);
            ps.setString(8, createdBy);
            ps.setString(9, lastUpdate);
            ps.setString(10, lastUpdatedBy);
            ps.setInt(11, customerId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    default boolean insertAppointment() {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES ('weenies', 'time for weenies', 'weenie street', 'blankets type', '2012-08-12 09:30:00', '2012-08-12 12:00:00', '2023-09-09 12:45:00', 'joe biden', '2023-09-09 12:45:00', 'hunter biden', 2, 1, 1);";

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
