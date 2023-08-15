/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package C195.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LabUser
 */
public interface AppointmentQuery {
    default List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
               appointments.add(new Appointment(
                       rs.getInt("Appointment_ID"), 
                       rs.getString("Title"), 
                       rs.getString("Description"), 
                       rs.get, 
                       type, 
                       startDate, 
                       endDate, 
                       creationDate, 
                       rs.getString("Created_By"), 
                       lastUpdate, 
                       rs.getString("Last_Updated_By"), 
                       rs.getInt("Customer_ID"), 
                       rs.getInt("User_ID"), 
                       rs.getInt("Contact_ID"))); 
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        
        
        
        return appointments;
    }
}
