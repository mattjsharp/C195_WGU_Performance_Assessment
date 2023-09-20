package C195.model;

import java.time.LocalDateTime;

/**
 *
 * @author LabUser
 */
public class FirstLevelDivision {
    private final int id;
    private final String name;
    private final LocalDateTime createDate;
    private final String createdBy;
    private final LocalDateTime lastUpdate;
    private final String lastUpdatedBy;
    private final int countryId;
    
    public FirstLevelDivision(int id, String name, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.id  = id;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    
    /**
     *
     * @return Who the record was created by.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
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