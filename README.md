# Java-based Scheduling Desktop Application

## PROJECT DESCRIPTION
This project is a GUI-based scheduling desktop application developed in Java. The application pulls data from a pre-existing MySQL database that has been provided by a global consulting organization. The database is used for other systems and its structure cannot be modified. The application is required to meet specific business requirements outlined by the consulting organization.

## REQUIREMENTS
1. The application must provide the ability to add, update, and delete customer records in the database, including name, address, and phone number.
2. The application must provide the ability to add, update, and delete appointments, capturing the type of appointment and a link to the specific customer record in the database.
3. The application must provide the ability to view the calendar by month and by week.
4. The application must provide the ability to automatically adjust appointment times based on user time zones and daylight saving time.
5. The application must provide the ability to generate each of the following reports:
    * number of appointment types by month
    * the schedule for each consultant
    * one additional report of your choice
6. The application must provide the ability to log user activity by recording timestamps for user logins in a .txt file. Each new record should be appended to the log file, if the file already exists.
7. The application must incorporate the following user controls for logging in and logging out, and restricting user access to appropriate parts of the application:
    * a text field for entering a user name
    * a text field for entering a password
    * a control for the user to select the login button
    * a control for the user to select the exit button
    * a control for the user to select the logout button
    * a control for the user to select the cancel button
8. The application must provide error messages for each of the following conditions:
   * the required fields have not been completed
   * the appointment is scheduled outside business hours
   * the start time is after the end time
   * there is an appointment within 15 minutes of the current time
   * the customer has an appointment within 15 minutes of the current time
    * the user has provided an incorrect username and password
    * there is a networking error
    * there is a database error

## SCENARIO

You are working for a software company that has been contracted to develop a GUI-based scheduling desktop application. The contract is with a global consulting organization that conducts business in multiple languages and has main offices in Phoenix, Arizona; White Plains, New York; Montreal, Canada; and London, England. The consulting organization has provided a MySQL database that the application must pull data from. The database is used for other systems, so its structure cannot be modified.

The organization outlined specific business requirements that must be met as part of the application. From these requirements, a system analyst at your company created solution statements for you to implement in developing the application. These statements are listed in the requirements section.

Your company acquires Country and First-Level-Division data from a third party that is updated once per year. These tables are prepopulated with read-only data. Please use the attachment “Locale Codes for Region and Language” to review division data. Your company also supplies a list of contacts, which are prepopulated in the Contacts table; however, administrative functions such as adding users are beyond the scope of the application and done by your company’s IT support staff. 

## SOLUTION STATEMENTS
* Use Java's internationalization feature to support multiple languages.
* Create a class for appointments that contains all necessary attributes and methods for creating, modifying, and deleting appointments.
* Use Java's Timer class to provide reminders for upcoming appointments.
* Use Java's Swing library to create the GUI and display the schedule.
* Use Java's Stream API to filter appointments.
* Use Java's Date and Time API to display available time slots.
* Use Java's validation feature to ensure no two appointments overlap.

## PROJECT STRUCTURE
* src/
    * cms.stephenwongc195/
      * controller
        * AddAppointmentController.java
        * AddCustomerController.java
        * HomeController.java
        * LoginController.java
        * ModAppointmentController.java
        * ModCustomerController.java
        * ReportController.java
      * dao
        * AppointmentDAO.java
        * ContactDao.java
        * CustomerDao.java
        * JDBC.java
        * LocationDao.java
        * Query.java
      * model
        * Appointment.java
        * AppointmentByMonth.java
        * AppointmentsByCustomer.java
        * Contact.java
        * Country.java
        * Customer.java
        * Division.java
      * utils
        * AlertsUtils.java
        * Context.java
        * Navigate.java
        * TimeUtils.java
      * Main.java

## INSTALLATION
* Download and install the version 17 of Java SE Development Kit (JDK) from Oracle: https://www.oracle.com/java/technologies/javase-downloads.html
* Download and install the latest version of MySQL Community Server from Oracle: https://dev.mysql.com/downloads/mysql/
* Download and install the latest version of JAVAFX SDK from Gluon: https://gluonhq.com/products/javafx/
* Download and install the latest version of IntelliJ IDEA Community Edition from JetBrains: https://www.jetbrains.com/idea/download/
* Download and install the latest version of Scene Builder from Gluon: https://gluonhq.com/products/scene-builder/
* Download and install the latest version of MySQL Workbench from Oracle: https://dev.mysql.com/downloads/workbench/
* Download and install the latest version of Git from Git: https://git-scm.com/downloads
* Clone the repository from GitHub using the following command: `git clone
* Create MySql schema client_schedule.
* Create MySql user sqlUser with password PassW0rd!.
* Grant all privileges to sqlUser on client_schedule.
* Import the database schema from the file client_schedule.sql data_for_database_06272019.sql.
* Open the project in IntelliJ IDEA.
* Run the project in IntelliJ IDEA from Main.java.
* The application will open in a new window.
* Login with username test and password test.

## SCREENSHOTS

![Screenshot 2023-04-23 at 1.30.24 PM.png](screenshots%2FScreenshot%202023-04-23%20at%201.30.24%20PM.png)![Screenshot 2023-04-23 at 1.35.33 PM.png](screenshots%2FScreenshot%202023-04-23%20at%201.35.33%20PM.png)
![Screenshot 2023-04-23 at 1.35.37 PM.png](screenshots%2FScreenshot%202023-04-23%20at%201.35.37%20PM.png)
![Screenshot 2023-04-23 at 1.35.42 PM.png](screenshots%2FScreenshot%202023-04-23%20at%201.35.42%20PM.png)
![Screenshot 2023-04-23 at 1.35.53 PM.png](screenshots%2FScreenshot%202023-04-23%20at%201.35.53%20PM.png)
![Screenshot 2023-04-23 at 1.36.01 PM.png](screenshots%2FScreenshot%202023-04-23%20at%201.36.01%20PM.png)

## WIREFRAME

![Wireframe - 1.jpg](wireframe%2FWireframe%20-%201.jpg)
![Wireframe - 2.jpg](wireframe%2FWireframe%20-%202.jpg)
![Wireframe - 3.jpg](wireframe%2FWireframe%20-%203.jpg)
![Wireframe - 4.jpg](wireframe%2FWireframe%20-%204.jpg)
![Wireframe - 5.jpg](wireframe%2FWireframe%20-%205.jpg)

## ENTITY RELATIONSHIP DIAGRAM

![ERD.png](wireframe%2FERD.png)