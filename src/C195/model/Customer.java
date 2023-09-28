package C195.model;

import java.time.LocalDateTime;

/**
 *
 * @author LabUser
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
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public int getDivisionId() {
        return divisionId;
    }
    
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
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