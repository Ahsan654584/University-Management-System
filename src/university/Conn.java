package university;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conn {
    
    Connection c;
    Statement s;

    public Conn() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
            s = c.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
