package controllers;

import java.sql.*;
import app.config;

public class getCars {
    public ResultSet getAllCars(){
        try{
            String query="Select * From cars";
            Connection connect =config.getConnection();
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            ResultSet rs =null;    
            System.out.println("Error getting all cars "+e.getMessage());
            return rs;
        }
    }
}
