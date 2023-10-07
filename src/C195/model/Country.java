package C195.model;

import java.time.LocalDateTime;

/**
 * Model class representing countries stored on the client_schedule database.
 * Most fields are not used and no getter is provided.
 * 
 * @author mattjsharp
 */
public class Country {
    private final int id;
    private final String name;
    private final LocalDateTime createDate;
    private final String createdBy;
    private final LocalDateTime lastUpdate;
    private final String lastUpdatedBy;
    
    /**
     * Constructor method for the country objects.
     * 
     * @param id
     * @param name
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy 
     */
    public Country(int id, String name, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.id  = id;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}

/*
Country_ID INT(10) (PK)
Country VARCHAR(50)
Create_Date DATETIME
Created_By VARCHAR(50)
Last_Update TIMESTAMP
Last_Updated_By VARCHAR(50)
*/