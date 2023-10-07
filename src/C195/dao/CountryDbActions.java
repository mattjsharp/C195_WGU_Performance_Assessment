package C195.dao;

import C195.model.Country;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to perform actions relating to countries on the client_schedule database.
 * 
 * @author mattjsharp
 */
public interface CountryDbActions {

    /**
     * Queries the client_schedule database to return every country record.
     * 
     * @return A list of every country represented by Country objects.
     */
    default List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM countries";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                countries.add(new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"),
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

        return countries;
    }
    
    /**
     * Finds a country record matches a particular Country_ID.
     * 
     * @param id A provided country ID.
     * @return A list of Country objects.
     */
    default List<Country> getCountries(int id) {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM countries WHERE Country_ID = " + id + ";";
        
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                countries.add(new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"),
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
        
        return countries;
    }
}
