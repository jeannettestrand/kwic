import java.sql.SQLException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Employee Index Search GUI 
 * Provides a basic user interface for employee name searches.
 * 
 * @author Jeannette Strand and Rachel Mason
 * @since 1.0
 * @version 1.0
 * 
 */
public class EmployeeIndexGUI extends JFrame implements ActionListener {
    private JLabel userLabel;     
    private JLabel passLabel; 
    private JLabel searchLabel; 
    private JTextField userName;
    private JPasswordField passWord;
    private JTextField searchItem;
    private JTextArea resultSet;
    private JButton loginButton; 
    private JButton searchButton;  
    private JButton exitButton;
    private Connection c;

    /**
     * GUI Constructor
     * Initializes data for the class.
     *  
     */
    public EmployeeIndexGUI() {
        GridBagConstraints positionConst = null;
        setTitle("Employee Search");
        
        userLabel = new JLabel("Enter Username:");
        passLabel = new JLabel("Enter Password:");
        searchLabel = new JLabel("Search for:");
        
        userName = new JTextField(15);
        userName.setEditable(true);
        userName.setText("");

        passWord = new JPasswordField(15);
        passWord.setEditable(true);
        passWord.setText("");
      
        searchItem = new JTextField(15);
        searchItem.setEditable(false);
        searchItem.setText("");
      
        resultSet = new JTextArea(15, 20);
        resultSet.setEditable(false);
        resultSet.setLineWrap(true);
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setEnabled(true);
        
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setEnabled(false);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setEnabled(true);
        
        setLayout(new GridBagLayout());
        positionConst = new GridBagConstraints();

        positionConst.gridx = 0;
        positionConst.gridy = 0;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(userLabel, positionConst);
      
        positionConst.gridx = 1;
        positionConst.gridy = 0;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(userName, positionConst);
      
        positionConst.gridx = 0;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(passLabel, positionConst);      
      
        positionConst.gridx = 1;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(passWord, positionConst);

        positionConst.gridx = 1;
        positionConst.gridy = 2;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(loginButton, positionConst);
        
        positionConst.gridx = 0;
        positionConst.gridy = 3;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(searchLabel, positionConst);

        positionConst.gridx = 1;
        positionConst.gridy = 3;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(searchItem, positionConst);

        positionConst.gridx = 1;
        positionConst.gridy = 4;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(searchButton, positionConst);

        positionConst.gridx = 1;
        positionConst.gridy = 6;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(resultSet, positionConst);
        
        positionConst.gridx = 1;
        positionConst.gridy = 7;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(exitButton, positionConst);
        
   }

    /**
     * Action Listener
     * Listens for action on form buttons to provoke searches. 
     * 
     * @param event Action perceived from form buttons.
     */
   @Override
   public void actionPerformed(ActionEvent event) {
        String userInputUserName = userName.getText();
        String userInputPassWord = passWord.getText();
       
        JButton sourceEvent = (JButton) event.getSource();
        DBConnect connect; 
        
        if (sourceEvent == loginButton) {
            
            try {
                connect = new DBConnect(userInputUserName, userInputPassWord);
                c = connect.getConnection();
                searchItem.setEditable(true);
                searchItem.setText("");
                searchButton.setEnabled(true);
            } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Your login was unsuccessful, please retry!");
                    userName.setText("");
                    passWord.setText("");
            } 
        }
  
        if (sourceEvent == searchButton){
            try {
                String userInputEmpSearch = searchItem.getText();
                
                if (!userInputEmpSearch.equals("")) {
                    Search searchObj = new Search(c, userInputEmpSearch);
                
                    if (!searchObj.hasNext())
                        JOptionPane.showMessageDialog(this, "No employees found for this search.");
                
                    String display = "";
                
                    while (searchObj.hasNext()) {
                        Employee e = new Employee(c, searchObj.nextInt());
                        display += e.toString() + "\n"; 
                    }
                resultSet.setText(display);
                searchItem.setText("");}
                else {
                    JOptionPane.showMessageDialog(this, "Please enter the first few letters of the employee you wish to find.");
                    userInputEmpSearch = searchItem.getText();
                }
            } catch (SQLException | IllegalArgumentException ex) {}
        }
        if (sourceEvent == exitButton) {
            try {
                c.close();
                System.exit(0);
                JOptionPane.showMessageDialog(this, "You are logged out now.");    
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Please exit.");
            }
        }
    }
}

 
