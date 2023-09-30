package C195.dao;

import C195.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LabUser
 */
public interface UserDbActions {
    default List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("User_ID"),
                        rs.getString("User_Name"),
                        rs.getString("Password"),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_Updated_By")
                    )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
}
