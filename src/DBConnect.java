import java.sql.*;

/**
 * DB Connect
 * Provides a connection (session) with a specific database.
 * 
 * @author Jeannette Strand and Rachel Mason
 * @version 1.0
 * @since1.0
 */
public class DBConnect {
    private String userName;
    private String passWord;
    
    /**
     * DB Connect Constructor
     * Initializes data for the Class. 
     * 
     * @param userName Username for database login.
     * @param passWord Password for database login.
     * @throws java.sql.SQLException If connection cannot be established.
     * 
     */
    public DBConnect(String userName, String passWord) throws SQLException {
        this.userName = userName;
        this.passWord = passWord;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("I need to add the ojdbc7.jar file to the project.");
            System.exit(0);
        }
    }
    
    /**
     * Get Connection
     * Initializes database session. 
     * 
     * @return Connection Connection (session) with a specific database.
     * 
     */
    public Connection getConnection() throws SQLException{
        Connection conn;
        try {
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@babbage.cs.camosun.bc.ca:1521:CALEB",
                userName, 
                passWord);
        } 
        catch (SQLException e) {
            throw new SQLException("Login trouble.");
        }
        return conn;
    }
}