package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.config;

public class newListing {
    private Connection connect=config.getConnection();
    private String brand;
    private String model;
    private int year;
    private String location;
    private String transaction;

    public int addCar(int client_id,String brand,String model,String price,String year,String description,String location,String transaction){
        //validating user input
        if(brand.isEmpty() || model.isEmpty()|| description.isEmpty()){
            JOptionPane.showMessageDialog(null,"Must Fill All Fields!");
            return -1;
        }
        
        int castYear;
        int currentYear=java.time.Year.now().getValue();

        try{    
            castYear =Integer.parseInt(year);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Year Must be a number!");
            return -1;
        }

        if(castYear<1886 || castYear >currentYear+1){
            JOptionPane.showMessageDialog(null,"Enter a valid Year between 1886 and "+(currentYear+1));
            return -1;
        }

        double castPrice;

        try{
            castPrice=Double.parseDouble(price);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Enter a valid price number");
            return -1;
        }

        if(castPrice<=0){
            JOptionPane.showMessageDialog(null,"Price must be greater than 0");
            return -1;
        }

        if(castPrice>=10000000){
            JOptionPane.showMessageDialog(null,"Price seems too high!");
            return -1;
        }
        
        try{
            String query="INSERT INTO car (owner_id,brand,model,year,price,description,location,transaction)"+
                        "Values("+client_id+",'"+brand+"','"+model+"',"+castYear+","+castPrice+",'"+description+"','"+location+"','"+transaction+"') returning car_id";
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            if(rs.next()){
                return rs.getInt("car_id");
            }
        }catch(Exception e){
            System.out.println("Error adding car "+e.getMessage());
        }
        return -1;
    }
    public boolean addPrimaryImage(int car_id,String imagePath){
        try{
            String query="Insert into carimage(car_id,image_path,is_primary)"+
                            "Values ("+car_id+",'"+imagePath+"',TRUE)";
            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("Error adding primary image "+e.getMessage());
            return false;
        }
    }
    public boolean addImages(int car_id, ArrayList<String> imageNames){
        try{
            String query="Insert into carimage (car_id,image_path,is_primary) Values ";
            for(int i=0;i<imageNames.size();i++){
                query+="("+car_id+",'"+imageNames.get(i)+"',false)";
                if(i<imageNames.size() -1){
                    query+=",";
                }
            }

            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("error adding images "+e.getMessage());
            return false;
        }
    }
}
