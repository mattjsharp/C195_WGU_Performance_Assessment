/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.model;

import java.time.LocalDateTime;

/**
 *
 * @author LabUser
 */
public class Appointment {

    private int id;
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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
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
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedBy() {
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
