import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Jeannette Strand and Rachel Mason
 * @since 1.0
 * @version 1.0
 * 
 */
/* Uncomment for GUI Interface

*/public class Main {
      public static void main(String[] args) {
      EmployeeIndexGUI myFrame = new EmployeeIndexGUI();

      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.pack();
      myFrame.setVisible(true);
   }
}
/*
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String search;
        String userName;
        String passWord;
        Boolean pass = false;
        DBConnect conn = null;
        Connection c = null;
        
        while(!pass) {
            System.out.println("Please login.");
            System.out.println("Enter your user name: ");
            userName = sc.next();
            System.out.println("Enter your password: ");
            passWord = sc.next();
            try {
                conn = new DBConnect(userName, passWord);
                c = conn.getConnection();
                pass = true;
            } catch (SQLException se) {
                System.out.println("Your login was unsuccessful, please retry");
            } 
        }
        
        System.out.println("Enter the first few letters of the employee you want to find");
        search = sc.next();
        
        try {
            while (!search.equalsIgnoreCase("exit")){ 
                Search searchObj = new Search(c, search);
                
                if (!searchObj.hasNext())
                    System.out.println("No results from this search.");
                while (searchObj.hasNext()){
                    Employee e = new Employee(c, searchObj.nextInt());
                    System.out.println(e.toString());
                }
            
                System.out.println("\nIf you're all done, type in exit, otherwise...");
                System.out.println("Enter the first few letters of the employee you want to find: ");
                search = sc.next(); 
            } 
            
            if (search.equalsIgnoreCase("exit")) {
                System.out.println("You are logged out. Thanks!");
                c.close();
            }
        } catch (SQLException se) {
            System.out.println("Please start again, your connection is invalid.");
        } catch (IllegalArgumentException iae) {
            System.out.println("Please re-enter, your search cannot be empty.");
        } 
    }
}




*/