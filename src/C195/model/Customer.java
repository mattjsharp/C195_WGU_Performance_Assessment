package C195.model;

import java.time.LocalDateTime;

/**
 * Model class representing customers stored on the client_schedule database.
 * 
 * @author mattjsharp
 */
public class Customer {
    private final int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private final LocalDateTime creationDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    
    /**
     * Constructor method for customer objects.
     * 
     * @param id
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param creationDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId 
     */
    public Customer(int id,
            String name,
            String address,
            String postalCode,
            String phone,
            LocalDateTime creationDate,
            String createdBy,
            LocalDateTime lastUpdate,
            String lastUpdatedBy,
            int divisionId
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }
    
    /**
     * 
     * @return The associated customer ID.
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return A String containing the address of the associated customer.
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * 
     * @return A String containing the postal/zip code of the associated customer.
     */
    public String getPostalCode() {
        return postalCode;
    }
    
    /**
     * 
     * @return A String containing the phone number of the associated customer.
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * 
     * @return The date and time that the customer was created on the client_schedule database.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    /**
     * 
     * @return A string containing the userid of the user who created the customer record.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * 
     * @return Date and time of the last time the customer was last modified.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    
    /**
     * 
     * @return String containing the ussername of the last person to modify the customer record.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    /**
     * 
     * @return The Division_ID of the associated customer.
     */
    public int getDivisionId() {
        return divisionId;
    }
}

/*
Customer_ID INT(10) (PK)
Customer_Name VARCHAR(50)
Address VARCHAR(100)
Postal_Code VARCHAR(50)
Phone VARCHAR(50)
Create_Date DATETIME
Created_By VARCHAR(50)
Last_Update TIMESTAMP
Last_Updated_By VARCHAR(50)
Division_ID INT(10) (FK)
*/