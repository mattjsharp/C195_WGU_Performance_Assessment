package C195.dao;

import C195.model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Interface containing a method to check if a username and password matches a user record on the client_schedule database.
 * 
 * @author mattjsharp
 */
public interface LoginQuery {

    /**
     * Method to check if a username and password matches a user record on the client_schedule database.
     * 
     * @param username The username provided in the login screen.
     * @param password The password from the login screen.
     * @return A user object that matches a User record on the database.
     * @throws SQLException 
     */
    default User login(String username, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?;";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt("User_ID"),
                    rs.getString("User_Name"),
                    rs.getString("Password"),
                    rs.getTimestamp("Create_Date").toLocalDateTime(),
                    rs.getString("Created_By"),
                    rs.getTimestamp("Last_Update").toLocalDateTime(),
                    rs.getString("Last_Updated_By")
            );
            break;
        }

        return user;
    }
}
