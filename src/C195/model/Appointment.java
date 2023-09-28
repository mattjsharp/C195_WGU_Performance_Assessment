package C195.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import static C195.helper.QuickDateTimeFormatter.*;

/**
 * Appointment Model Class.
 * Model class representing appointments stored on the client_schedule database.
 * Many of the getters here are only for access by the property value factories to be displayed in tables without formatting.
 * @author LabUser
 */
public class Appointment {

    private final int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private final LocalDateTime creationDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * Constructor for the Appointment class.
     * 
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDate
     * @param endDate
     * @param creationDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerId
     * @param userId
     * @param contactId 
     */
    public Appointment(int id,
            String title,
            String description,
            String location,
            String type,
            LocalDateTime startDate,
            LocalDateTime endDate,
            LocalDateTime creationDate,
            String createdBy,
            LocalDateTime lastUpdate,
            String lastUpdatedBy,
            int customerId,
            int userId,
            int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     *
     * @return Appointment ID.
     */
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }
    
    public LocalDate getDate() {
        return startDate.toLocalDate();
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public String getStartTime() {
        return quickTimeFormater(startDate);
    }

    public String getFormattedStartDate() {
        return quickDateFormatter(startDate);
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public String getEndTime() {
        return quickTimeFormater(endDate);
    }

    public String getFormattedEndDate() {
        return quickDateFormatter(endDate);
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getFormattedCreationDate() {
        return quickDateFormatter(creationDate);
    }

    public String getCreatedBy() {
        return createdBy;
    }
    
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getFormattedLastUpdate() {
        return quickDateFormatter(lastUpdate);
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }
    
    public int getContactId() {
        return contactId;
    }
}

/*
Appointment_ID INT(10) (PK)
Title VARCHAR(50)
Description VARCHAR(50)
Location VARCHAR(50)
Type VARCHAR(50)
Start DATETIME
End DATETIME
Create_Date DATETIME
Created_By VARCHAR(50)
Last_Update TIMESTAMP
Last_Updated_By VARCHAR(50)
Customer_ID INT(10) (FK)
User_ID INT(10) (FK)
Contact_ID INT(10) (FK)
*/
