package controllers;

import java.sql.*;
import app.config;

public class getCars {
    private Connection connect=null;
    public ResultSet getAllCars(){
        try{
            String query="Select * From car";
            connect =config.getConnection();
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            ResultSet rs =null;
            System.out.println("Error getting all cars "+e.getMessage());
            return rs;
        }
    }
    public ResultSet getPrimaryCarImages(){
        try{
            String query="Select car_id, image_path From carimage where is_primary = true";
            connect =config.getConnection();
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            ResultSet rs =null;    
            System.out.println("Error getting all cars primary images "+e.getMessage());
            return rs;
        }
    }
    public ResultSet getLatestCars(){
        try {
            String query="Select * from car where posted_at >= NOW() - INTERVAL '2 days' ";
            connect =config.getConnection();
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            ResultSet rs=null;
            System.out.println("Error getting latest Cars "+e.getMessage());
            return rs; 
        }
    }
    public ResultSet getLatestPrimaryImages(){
        try{
            String query="Select ci.* From carimage ci"+
                            " Join car c on ci.car_id = c.car_id "+
                            "where c.posted_at >= NOW() - INTERVAL '2 days'";
            connect =config.getConnection();
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
