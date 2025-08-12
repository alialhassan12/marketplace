import java.sql.*;
public class config {
    private static String url="jdbc:sqlserver://DESKTOP-JO4LG69\\SQLEXPRESS02;databaseName=CarManagementDB;encrypt=false";
    private static String username="sa";
    private static String password="root";
    public static Connection getConnection(){
        Connection con=null;
        try{
            con=DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
