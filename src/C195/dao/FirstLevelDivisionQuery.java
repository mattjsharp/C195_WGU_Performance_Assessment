package C195.dao;

import C195.model.FirstLevelDivision;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LabUser
 */
public interface FirstLevelDivisionQuery {
    default List<FirstLevelDivision> getDivisions(int countryId) {
        List<FirstLevelDivision> divisions = new ArrayList<>();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryId + ";";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                divisions.add(new FirstLevelDivision(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Country_ID")
                            )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return divisions;
    }
    
    default int getCountryCode(int code) {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + code + ";";
        
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("Country_ID");
            } else {
                throw new Exception();
            }
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
