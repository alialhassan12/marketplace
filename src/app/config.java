package app;
import functions.*;
import java.sql.*;
public class config {
    static readingEnv readEnv=new readingEnv();
    private static  String url = readEnv.getEnv("DB_URL");
    private static  String username=readEnv.getEnv("DB_USER");
    private static  String password=readEnv.getEnv("DB_PASS");
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
