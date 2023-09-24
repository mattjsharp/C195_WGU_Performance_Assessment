package C195.model;

import java.time.LocalDateTime;

/**
 *
 * @author LabUser
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
    
    public static User getUser() {
        return loggedOnUser;
    }
    
    public static void setUser(User user) {
        loggedOnUser = user;
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
}
