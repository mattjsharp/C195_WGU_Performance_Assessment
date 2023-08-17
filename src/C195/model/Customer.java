/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int division;
    
    public Customer(int id,
            String name,
            String address,
            String postalCode,
            String phone,
            LocalDateTime creationDate,
            String createdBy,
            LocalDateTime lastUpdate,
            String lastUpdatedBy,
            int division
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
        this.division = division;
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
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getPhone() {
        return phone;
    }
    
}
