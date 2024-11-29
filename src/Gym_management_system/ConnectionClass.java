
package Gym_Management_system;

import java.sql.*;

public class ConnectionClass {
    public Connection con;
    public Statement stm;

    public ConnectionClass() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gymmanagementsystem", "root", "Aapple"
            );
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }
}
