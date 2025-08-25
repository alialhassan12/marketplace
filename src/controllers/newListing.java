package controllers;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;

import app.config;

public class newListing {
    private Connection connect=config.getConnection();
    private String brand;
    private String model;
    private String year;
    private String location;
    private String transaction;

    public boolean addCar(String brand,String model,double price,String year,String description,String location,String transaction){
        try{
            String query="INSERT INTO car (brand,model,year,price,description,location,transaction)"+
                        "Values('"+brand+"','"+model+"','"+year+"','"+price+"','"+description+"','"+location+"','"+transaction+"')";
            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("Error adding car "+e.getMessage());
            return false;
        }
    }
}
