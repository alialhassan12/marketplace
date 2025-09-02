package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import app.config;

public class search {
    Connection connect=config.getConnection();
    private ArrayList<Double> allPrices=new ArrayList<>();

    public ResultSet searchCar(String brand,int year,double price){
        try{
            String query="Select c.*, ci.image_path From car c left join carimage ci on ci.car_id=c.car_id "+
                            " where c.brand='"+brand+"' and year="+year+" and price="+price +" and ci.is_primary=true";
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
    public ArrayList<Double> getAllPrices(){
        try{
            String query="SELECT DISTINCT price FROM car";
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            
            while (rs.next()) {
                allPrices.add(rs.getDouble("price"));
            }
            return allPrices;
        }catch(Exception e){
            System.out.println("error getting all prices "+e.getMessage());
            return null;
        }
    }
}
