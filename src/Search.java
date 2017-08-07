import java.sql.*;
import java.util.ArrayList;

/**
 * Search 
 * Searches specified database for employees matching the search parameter.
 * 
 * @author Jeannette Strand and Rachel Mason
 * @since 1.0
 * @version 1.0
 * 
 */
public class Search {
    private ArrayList<Integer> results;
    private int i = 0;
    
    /**
     * Search Constructor
     * Initializes data for the class.
     * 
     * @param conn Connection string from DB Connect
     * @param empSearch Search string. 
     * @throws SQLException If connection string is not valid. 
     * @throws IllegalArgumentException if search string is empty.
     */
    public Search(Connection conn, String empSearch) throws SQLException {
        if (!empSearch.equals("")){
            results = new ArrayList();
            String query = empSearch.toUpperCase() + "%";
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees_index WHERE UPPER(employee_name) LIKE ?");
                stmt.setString(1, query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    Integer get = (Integer) rs.getInt("employee_id");
                    results.add(get);
                }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            throw new SQLException("Invalid Connection");}
        } else 
            throw new IllegalArgumentException("Invalid Search");
    } 
    
    /**
     * HasNext
     * Returns true if there are more search results to return.
     * 
     * @return boolean True if there are more search results to return. 
     */
    public boolean hasNext(){
        return i < results.size();
    }
    
    /**
     * Next Int
     * Returns the next employee ID in the search results.
     * 
     * @return int Employee ID
     */
    public int nextInt() {
        return results.get(i++);
    }
}
    
    
