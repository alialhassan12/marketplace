package app;
import java.sql.*;
public class config {
private static String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres";
    private static String username="postgres.jjrtlhqvivxhiuorlauo";
    private static String password="d@t@b@$eiul2025";
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
