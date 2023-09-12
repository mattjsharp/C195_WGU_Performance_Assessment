package C195.dao;

import C195.model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LabUser
 */
public interface ContactQuery {

    default List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contacts.add(new Contact(rs.getInt("Contact_ID"), rs.getString("Contact_Name"), rs.getString("Email")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return contacts;
    }
}
