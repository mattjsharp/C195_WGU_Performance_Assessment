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
public interface DeleteAppointment {
    default boolean deleteAppointment(int appointmentId) {
        
        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentId + ";";
        
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
