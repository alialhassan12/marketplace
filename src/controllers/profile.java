package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import app.config;

public class profile {
    private int client_id;
    Connection connect=config.getConnection();
    public profile(int client_id){
        this.client_id=client_id;
    }
    public ResultSet getClientInfo(){
        try{
            String query="Select * From client where client_id= "+this.client_id;
            Statement stmt= connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs;
        }catch(SQLException e){
            System.out.println("Error getting client Info "+e.getMessage());
            return null;
        }
    }
    public boolean editProfile(String name,String email,String phone,String location){
        String newName=name;
        String newEmail=email;
        String newPhone=phone;
        String newLocation=location;
        if(newName.trim().isEmpty() && newEmail.trim().isEmpty() && newPhone.trim().isEmpty()&& newLocation.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Fill all Fields");
            return false;
        }
        try{
            String query="Update client "+
                        "Set name= '"+newName+"', email='"+newEmail+"', phone='"+newPhone+"',location='"+newLocation+"'"+
                        "Where client_id="+this.client_id;
            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("Error updating Profile: "+e.getMessage());
            return false;
        }
    }
    public boolean deleteAccount(){
        int choice=JOptionPane.showConfirmDialog(null,
                    "Are you sure you want delete your account ",
                    "Delete Account",JOptionPane.YES_NO_CANCEL_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            String query="Delete From client where client_id= "+this.client_id;
            try{
                Statement stmt=connect.createStatement();
                stmt.execute(query);
                JOptionPane.showMessageDialog(null,"Account Deleted !");
                return true;
            }catch(Exception e){
                System.out.println("Error deleting user: "+e.getMessage());
                return false;
            }
        }
        return false;
    }
    public boolean profilePic(String destination){
        try{
            String query="update client set profile_image= '"+destination+ "'where client_id="+this.client_id;
            Statement stmt=connect.createStatement();
            stmt.executeUpdate(query);
            return true;
        }catch(Exception e){
            System.out.println("Error updating profile pic: "+e.getMessage());
            return false;
        }
    }
    public ResultSet getMyListingsCars(int client_id){
        try{
            String query="Select * from car c "+
                        "join carimage ci on c.car_id=ci.car_id"+
                        " where owner_id="+client_id+" and is_primary=true";
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
