package C195.model;

import java.time.LocalDateTime;
import java.time.LocalDate;
import static C195.helper.QuickDateTimeFormatter.*;

/**
 * Appointment Model Class representing appointments stored on the client_schedule database.
 * Many of the getters here are only for access by the property value factories to be displayed in tables without formatting.
 * 
 * @author mattjsharp
 */
public class Appointment {

    private final int id;
    private final String title;
    private final String description;
    private final String location;
    private final String type;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final LocalDateTime creationDate;
    private final String createdBy;
    private final LocalDateTime lastUpdate;
    private final String lastUpdatedBy;
    private final int customerId;
    private final int userId;
    private final int contactId;

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
     * @return The appointment ID.
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return the appointment title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @return The appointment description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @return An address string representing the location of the appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * @return The type of Appointment scheduled.
     */
    public String getType() {
        return type;
    }
    
    /**
     * 
     * @return A local date object with set for the day the appointment takes place.
     */
    public LocalDate getDate() {
        return startDate.toLocalDate();
    }
    
    /**
     * 
     * @return Date and time that the appointment takes place.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    /**
     * 
     * @return A String that signifies what time the appointment takes place on a 12 hour clock.
     */
    public String getStartTime() {
        return quickTimeFormater(startDate);
    }

    /**
     * Same as calling getStartTime method but with the year included.
     * @return String with the start date and time on a 12 hour clock.
     */
    public String getFormattedStartDate() {
        return  quickDateFormatter(startDate) + " " + startDate.getYear();
    }
    
    /**
     * 
     * @return Date and time that the appointment ends. 
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    /**
     * 
     * @return A string containing the time that the appointment ends in 12-hour time.
     */
    public String getEndTime() {
        return quickTimeFormater(endDate);
    }

    /**
     * 
     * @return A string containing the date 12-time the appointment ends.
     */
    public String getFormattedEndDate() {
        return quickDateFormatter(endDate);
    }
    
    /**
     * 
     * @return The Date and time the appointment was created.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @return A string containing a formatted dat and time that the appointment was created.
     */
    public String getFormattedCreationDate() {
        return quickDateFormatter(creationDate);
    }

    /**
     * 
     * @return The username who created the appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * 
     * @return The date and time the appointment was created.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 
     * @return A string containing a formatted date and time that the appointment was last updated.
     */
    public String getFormattedLastUpdate() {
        return quickDateFormatter(lastUpdate);
    }

    /**
     * 
     * @return The username of the last user to update the appointment.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * 
     * @return The ID of the customer who this appointment is scheduled for.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * The ID of who this appointment was last scheduled by.
     * @return 
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * 
     * @return The ID of the associated contact with this appointment.
     */
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
