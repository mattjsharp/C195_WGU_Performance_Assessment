package C195.model;

import java.time.LocalDateTime;

/**
 * Model class representing first level divisions stored on the client_schedule database.
 * Most fields are not used and no getter is provided.
 * 
 * @author mattjsharp
 */
public class FirstLevelDivision {
    private final int id;
    private final String name;
    private final LocalDateTime createDate;
    private final String createdBy;
    private final LocalDateTime lastUpdate;
    private final String lastUpdatedBy;
    private final int countryId;
    
    /**
     * Constructor method for FirstLevelDivision objects.
     * 
     * @param id
     * @param name
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryId 
     */
    public FirstLevelDivision(int id, String name, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.id  = id;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }
    
    /**
     * 
     * @return The id of the division.
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @return The associated name of the division.
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return The Country_ID of whichever country the record references.
     */
    public int getCountryId() {
        return countryId;
    }
}

/*
Division_ID INT(10) (PK)
Division VARCHAR(50)
Create_Date DATETIME
Created_By VARCHAR(50)
Last_Update TIMESTAMP
Last_Updated_By VARCHAR(50)
Country_ID INT(10) (FK)
*/