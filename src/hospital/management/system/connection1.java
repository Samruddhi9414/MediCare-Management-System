package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connection1 {

    Connection connection;
    Statement statement;


    public connection1() {
    try {
            connection= DriverManager .getConnection("jdbc:mysql://localhost:3306/hospital_management_system","root","sam9414");
            statement =connection.createStatement();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
