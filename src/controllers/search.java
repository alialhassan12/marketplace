package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import app.config;

public class search {
    Connection connect=config.getConnection();

    public ResultSet searchCar(String brand,int year,double price){
        try{
            String query="Select c.*, ci.image_path From car c left join carimage ci on ci.car_id=c.car_id "+
                            " where c.brand='"+brand+"' and year="+year+" and price="+price;
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            System.out.println("Error search Car: "+e.getMessage());
            return null;
        }
    }   
    public ResultSet searchCar(String name){
        try{
            String query="Select c.*, ci.image_path From car c left join carimage ci on ci.car_id=c.car_id"+
                            "where brand='"+name+"'";
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            System.out.println("Error search Car: "+e.getMessage());
            return null;
        }
    }
}
