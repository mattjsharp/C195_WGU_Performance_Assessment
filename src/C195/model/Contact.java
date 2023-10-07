package C195.model;

/**
 * Model class representing contacts stored on the client_schedule database.
 * 
 * @author mattjsharp
 */
public class Contact {
    private final int id;
    private String name;
    private String emailAddress;
    
    /**
     * Constructor method for the contact objects.
     * 
     * @param id
     * @param name
     * @param emailAddress 
     */
    public Contact(int id, String name, String emailAddress) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
    }
    
    /**
     * 
     * @return The customer's ID.
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return The customer's email address.
     */
    public String getEamilAddress() {
        return emailAddress;
    }
}

/*
Contact_ID INT(10) (PK)
Contact_Name VARCHAR(50)
Email VARCHAR(50)
*/