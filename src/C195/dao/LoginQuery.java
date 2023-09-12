/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author LabUser
 */
public interface LoginQuery {

    default boolean login(String username, String password) throws SQLException {
        int count = 0;
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?;";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            count++;
        }
        return count > 0;
    }
}
