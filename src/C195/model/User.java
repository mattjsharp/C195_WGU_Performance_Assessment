package C195.model;

import java.time.LocalDateTime;

/**
 * Model class representing users stored on the client_schedule database.
 * Some fields are never referenced so no getter is provided.
 * 
 * @author mattjsharp
 */
public class User {
    
    private final int id;
    private String name;
    private String password;
    private final LocalDateTime creationDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    
    public User(int id,
        String name,
        String password,
        LocalDateTime creationDate,
        String createdBy,
        LocalDateTime lastUpdate,
        String lastUpdatedBy) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    private static User loggedOnUser = null;
    
    /**
     * Static method used to retrieve the user logged into the application.
     * 
     * @return The user currently logged into the application.
     */
    public static User getUser() {
        return loggedOnUser;
    }
    
    /**
     * Static method used to set the user currently logged into the application.
     * User is set to null whenever a logout is performed.
     * 
     * @param user The user to be logged in.
     */
    public static void setUser(User user) {
        loggedOnUser = user;
    }
    
    /**
     * 
     * @return The id of the associated user on the client_schedule database.
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @return The username of the associated user.
     */
    public String getName() {
        return name;
    }
}

/*
User_ID INT(10) (PK)
User_Name VARCHAR(50) (UNIQUE)
Password TEXT
Create_Date DATETIME
Created_By VARCHAR(50)
Last_Update TIMESTAMP
Last_Updated_By VARCHAR(50)
*/
