
                    WGU C195 Performance Assessment
                    
    Author: Matthew Sharp / mattjsharp 
    ID: 008211210
    Last Revised: 10/09/2023

    Java: 17.0.1 2021-10-19 LTS
    MySQL Connector: 8.0.25
    IDE: Apache NetBeans IDE 12.6
    JavaFX: openJFX 11.0.2
    SceneBuilder: 17.0.0

--PURPOSE-----------------------------------------------------------------------

    Create a GUI-based desktop application for a global consulting organization
    to provide Appointment scheduling and customer management utilities as well
    as provide some basic data analytic reports. A pre-existing MySQL database
    was provided for use. The application provides support for both english and
    french languages, adjusts times to support any timezone the user may be 
    located in, and conforms to and abides to all requirements outlined in the
    QAM2 TASK 1: JAVA APPLICATION DEVELOPMENT Rubric.

--USAGE-------------------------------------------------------------------------

  Logging in:
    When starting the application, users are greeted with a login screen in 
    either English or French, depending on the system's settings. Necessary
    warnings and notifications are displayed depending on the values provided in
    the text fields. All Login attempts and statuses are recorded and tracked in
    a text document titled login-activity.txt in the root folder of the
    application.

  Main Screen:
    Upon successfully login in, the main screen is displayed and all each tab
    is initialized with its respective data. A notification is then displayed
    that warns users if there are, or are not, any upcoming appointments 
    (within the next 15 minutes) is displayed. On the bottom the username is
    displayed and options for logging out or exiting the application are
    available and each action provides confirmation to confirm if the action was
    intended.

  Appointments Tab:
    Displays a table of appointments and filters to display all (default) or
    ones within the current work week or month. It also provides controls to
    Add/Modify/Delete any appointment. Adding an appointment displays a modal
    that allows the user to insert an appointment into the client_schedule
    database. Various validation and logical checks are performed to ensure that
    appointments are created correctly. Modifying an appointment displays a
    similar dialog but is pre-populated with data from the selected appointment.
    When deleting an appointment, a confirmation prompt is displayed to warn the
    user before actually deleting the appointment. Both modifying and deleting
    must have an appointment selected, otherwise an error prompt is shown.

  Customers Tab:
    Displays a table of all customers stored in the client_schedule database
    as well as controls to Add/Modify/Delete entries. A dialog window is
    displayed whenever a user presses the "Add" button. The windows contains
    several fields an boxes for user input, all of which are required to be
    filled out and match certain conditions. Modifying a customer opens a
    similar window but with the fields pre-populated with data from the selected
    customer. Warnings are displayed if the input in any/all of the boxes is
    incorrect. When deleting an customer, either a confirmation prompt is 
    displayed to warn the user before actually deleting the customer, or the
    selected customer is currently scheduled for an appointment.Both modifying 
    and deleting must have an appointment selected, otherwise an error prompt is
    shown.

  Reports Tab:
    3 reports are available for basic analytics, 2 of which were outlined in the
    requirements. The pie charts categorizes appointments by type and amount for
    a particular month and year and displays a pie chart based off that input.
    The second shows a table of appointments for a particular contact selected
    in a combo box. The third shows a bar graph of how many customers are in a
    First Level Division for a particular country.

--Directions--------------------------------------------------------------------

    To run the project from the command line, go to the dist folder and
    type the following:

    java --module-path "lib" --add-modules javafx.controls,javafx.fxml -jar .\C195_Appointment_Schedulder.jar

* This README.txt is also a requirement for the assignment.