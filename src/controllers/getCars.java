package controllers;

import java.sql.*;
import app.config;

public class getCars {
    private Connection connect=config.getConnection();
    public ResultSet getFeaturedCars(){
        try{
            String query="Select * From car c join carimage ci on c.car_id=ci.car_id where status='available' and is_primary=true";
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            ResultSet rs =null;
            System.out.println("Error getting all cars "+e.getMessage());
            return rs;
        }
    }

    public ResultSet getLatestCars(){
        try{
            String query="Select * From car c"+
                            " Join carimage ci on c.car_id = ci.car_id "+
                            "where posted_at >= NOW() - INTERVAL '2 days' and is_primary=true";
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            ResultSet rs =null;    
            System.out.println("Error primary images for latest Cars "+e.getMessage());
            return rs;
        }
    }
    
}
