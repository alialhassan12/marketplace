package controllers;

import java.sql.Connection;
import java.sql.Statement;

import app.config;

public class supportAndReports {
    private Connection connect=config.getConnection();
    private int client_id;
    private int owner_id;
    private int reportedCar_id;
    public supportAndReports(int client_id,int owner_id,int reportedCar_id){
        this.client_id=client_id;
        this.owner_id=owner_id;
        this.reportedCar_id=reportedCar_id;
    }

    public boolean sendReport(String messageContent){
        try{
            String query="Insert into messages (client_id,reportedcar_id,message_content,message_type)"+
                        "Values("+this.client_id+","+this.reportedCar_id+",'"+messageContent+"','car report')";
            Statement stmt=connect.createStatement();
            stmt.execute(query);
            return true;
        }catch(Exception e){
            System.out.println("Error sending reports "+e.getMessage());
            return false;
        }
    }
}
