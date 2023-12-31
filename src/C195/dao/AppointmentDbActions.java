package C195.dao;

import C195.helper.PieChartSection;
import C195.model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface with default methods relating to appointments related to
 * appointments on the client_schedule database.
 *
 * @author mattjsharp
 */
public interface AppointmentDbActions {

    /**
     * Queries the client_schedule database to return every appointment record.
     * 
     * @return A list representing every appointment from the database
     * represented by Appointment objects.
     */
    default List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments;";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();

                appointments.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        start,
                        end,
                        createDate,
                        rs.getString("Created_By"),
                        lastUpdate,
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return appointments;
    }

    /**
     * Counts the records in a database that have date ranges that overlap with a provided one.
     * 
     * @return A list of Appointment objects that overlap with the date range.
     */
    default List<Appointment> customerOverlapCheck(String startDate, String endDate, int customerId, int appointmentId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ? AND (? < end AND start < ?) AND Appointment_ID != ?;";
        // start1.isBefore(end2) && start2.isBefore(end1);

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setString(2, startDate);
            ps.setString(3,endDate);
            ps.setInt(4, appointmentId);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();

                appointments.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        start,
                        end,
                        createDate,
                        rs.getString("Created_By"),
                        lastUpdate,
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return appointments;
    }
    
    /**
     * Queries the database to find appointment records that reference a certain Contact_ID.
     * 
     * @param id The ID of a particular contact.
     * @return A list of Appointment objects containing the same contact ID.
     */
    default List<Appointment> getAppointmentsByContact(int id) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE Contact_ID = " + id + ";";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();

                appointments.add(new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        start,
                        end,
                        createDate,
                        rs.getString("Created_By"),
                        lastUpdate,
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return appointments;
    }

    /**
     * Generates sections to be used the appointment piechart report.
     * 
     * @param month The month to check.
     * @param year The year to check.
     * @return A list of Piechart sections that match the database.
     */
    default List<PieChartSection> getDistinctAppointments(int month, int year) {
        List<PieChartSection> sections = new ArrayList<>();
        int nextYear = (month == 12 ? year + 1 : year), nextMonth = (month == 12 ? 1 : month + 1);
        String sql = "SELECT COUNT(Appointment_ID) AS appointmentCount, Type FROM appointments WHERE Start >= \'" + year + "-" + month + "-01\' AND Start < \'" + nextYear + "-" + nextMonth + "-01\' GROUP BY Type;";
        
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sections.add(new PieChartSection(rs.getInt("appointmentCount"), rs.getString("Type")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sections;
    }

    /**
     * Deletes a particular appointment from the database.
     * 
     * @param appointmentId The ID of the appointment to be deleted.
     * @return A boolean value representing the result of the database operation.
     */
    default boolean deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentId + ";";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Inserts a new appointment record into the database based off of the values provided in the arguments.
     * 
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
     * @return A boolean result representing the result of the database operation.
     */
    default boolean insertAppointment(String title, String description, String location, String type, String startDate, String endDate, String creationDate, String createdBy, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        String insertSql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(insertSql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, startDate);
            ps.setString(6, endDate);
            ps.setString(7, creationDate);
            ps.setString(8, createdBy);
            ps.setString(9, lastUpdate);
            ps.setString(10, lastUpdatedBy);
            ps.setInt(11, customerId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Updates a particular appointment record based off of the values provided by the arguments.
     * 
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDate
     * @param endDate
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerId
     * @param userId
     * @param contactId
     * @param id
     * @return A boolean result representing the result of the database operation.
     */
    default boolean updateAppointment(String title, String description, String location, String type, String startDate, String endDate, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId, int id) {
        String updateSql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(updateSql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, startDate);
            ps.setString(6, endDate);
            ps.setString(7, lastUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, id);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Provides a tuple (or close enough to) containing the earliest and latest year of appointment records.
     * 
     * @return A tuple containing the earliest and latest year of the appointments.
     */
    default int[] getYearRange() {
        int[] range = {1950, LocalDateTime.now().getYear() + 50};

        try {
            PreparedStatement ps = JDBC.connection.prepareStatement("SELECT MAX(Start) AS Start FROM Appointments;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                range[0] = rs.getTimestamp("Start").toLocalDateTime().getYear();
            }

            ps = JDBC.connection.prepareStatement("SELECT MIN(Start) AS Start FROM Appointments;");
            rs = ps.executeQuery();
            while (rs.next()) {
                range[1] = rs.getTimestamp("Start").toLocalDateTime().getYear();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return range;
    }
}
