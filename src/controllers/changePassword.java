package controllers;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import app.config;
import app.hashPassword;

public class changePassword {
    Connection connect =config.getConnection();
    private int client_id;
    private String newPassword;
    private String confirmPassword;
    public changePassword(int client_id,String newPass,String confirmPass){
        this.client_id=client_id;
        this.newPassword=newPass;
        this.confirmPassword=confirmPass;
    }
    public boolean change(){
        if(!this.newPassword.equals(confirmPassword)){
            JOptionPane.showMessageDialog(null,"Passwords Dont Match!");
            return false;
        }
        if(this.newPassword.trim().isEmpty() && this.confirmPassword.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Passwords Cant Be Empty! ");
            return false;
        }
        try{
            String query="Update client set password_hash ='"+new hashPassword().hashPasswords(this.newPassword)+"'"+
                            "where client_id="+this.client_id;
            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("Error changing password: "+e.getMessage());
            return false;
        }
    }
}
