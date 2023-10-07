package C195.dao;

import C195.model.FirstLevelDivision;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface containing default methods to perform actions related to first_level_divisions on the client_schedule database.
 * 
 * @author mattjsharp
 */
public interface FirstLevelDivisionDbActions {
    /**
     * Returns a list containing a list of all first level divisions from the database that reference a given country id.
     * 
     * @param countryId The country id to check.
     * @return A list of divisions.
     */
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
    
    /**
     * Returns the country ID associated with the given country ID.
     * 
     * @param code The division ID to check.
     * @return The country ID associated with the associated division.
     */
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
