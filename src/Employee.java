import java.sql.*;
/**
 * Employee Class
 * Returns the employee record matching the search parameter passed to constructor. 
 * 
 * @author Jeannette Strand and Rachel Mason
 * @since 1.0
 * @version 1.0
 * 
 */
public class Employee {
    private PreparedStatement stmt;
    private ResultSet rs;
    private String phoneNum;
    private String firstName;
    private String lastName;
    private Integer empID;
    
    /**
     * Employee Class Constructor
     * Initializes data state for class
     * 
     * @param conn Connection (session) with a specific database.
     * @param empID Employee ID to search for. 
     */
    public Employee(Connection conn, Integer empID) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
            stmt.setInt(1, empID);
            rs = stmt.executeQuery();
            while (rs.next()){
                phoneNum = rs.getString("phone_number");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                empID = rs.getInt("employee_id");
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {}
    }

    /**
     * toString
     * Returns a string representation of employee records.
     * 
     * @return String Representation of employee record. 
     */
    @Override
    public String toString() {   
        String result = getFirstName() + " " + getLastName() + ": " + getPhoneNum();
        return result;
    }
    
    /**
     * Get Phone Number
     * Returns an employee phone number
     * 
     * @return Integer Employee phone number
     */
    public String getPhoneNum() {
        return phoneNum;
    }
    
    /**
     * Get First Name
     * Returns an employee's first name
     * 
     * @return String Employee first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Get Last Name
     * Returns an employee's last name
     * 
     * @return String Employee last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Get Employee ID
     * Returns an employee's ID number
     * 
     * @return int Employee ID number
     */
    public int getEmpID() {
        return empID;
    }
}
