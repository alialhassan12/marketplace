package controllers;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import app.config;

public class editCar {
    private Connection connect=config.getConnection();
    private int car_id;
    private String brand;
    private String model;
    private int year;
    private String location;
    private String transaction;

    public editCar(int car_id){
        this.car_id=car_id;
    }

    public boolean editCarInfo(String brand,String model,String price,String year,String description,String location,String transaction){
        //validating user input
        if(brand.isEmpty() || model.isEmpty()|| description.isEmpty()){
            JOptionPane.showMessageDialog(null,"Must Fill All Fields!");
            return false;
        }
        
        int castYear;
        int currentYear=java.time.Year.now().getValue();

        try{    
            castYear =Integer.parseInt(year);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Year Must be a number!");
            return false;
        }

        if(castYear<1886 || castYear >currentYear+1){
            JOptionPane.showMessageDialog(null,"Enter a valid Year between 1886 and "+(currentYear+1));
            return false;
        }

        double castPrice;

        try{
            castPrice=Double.parseDouble(price);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Enter a valid price number");
            return false;
        }

        if(castPrice<=0){
            JOptionPane.showMessageDialog(null,"Price must be greater than 0");
            return false;
        }

        if(castPrice>=10000000){
            JOptionPane.showMessageDialog(null,"Price seems too high!");
            return false;
        }
        
        try{
            String query="Update car "+
                    "set brand='"+brand+"' ,"+
                        "model='"+model+"', year="+castYear+","+
                        " price= "+castPrice+", description= '"+description+"',"+
                        "location='"+location+"', transaction='"+transaction+"'"+
                        "where car_id="+car_id;
            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("Error Editing car "+e.getMessage());
        }
        return false;
    }
}
