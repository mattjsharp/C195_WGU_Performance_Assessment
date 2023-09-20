package C195.model;

import java.time.LocalDateTime;
import static C195.helper.DateFormatter.formatDate;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    public LocalDate getDate() {
        return startDate.toLocalDate();
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public LocalTime getStartTime() {
        return startDate.toLocalTime();
    }

    public String getFormattedStartDate() {
        return formatDate(startDate);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public LocalTime getEndTime() {
        return endDate.toLocalTime();
    }

    public String getFormattedEndDate() {
        return formatDate(endDate);
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getFormattedCreationDate() {
        return formatDate(creationDate);
    }

    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getFormattedLastUpdate() {
        return formatDate(lastUpdate);
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getFormattedLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setContactId(int contactId) {
        this.contactId = contactId;
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
