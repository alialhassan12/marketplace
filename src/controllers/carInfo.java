package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;

import app.config;

public class carInfo {
    Connection connect = config.getConnection();
    private int car_id;

    public carInfo(int car_id) {
        this.car_id = car_id;
    }

    public CompletableFuture<ResultSet> getCarImages() {
        return CompletableFuture.supplyAsync(()->{
            try {
                String query = "Select * from carimage where car_id=" + this.car_id + " and is_primary=false";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                return rs;
            } catch (Exception e) {
                throw new RuntimeException("Error getting carImages: "+e.getMessage(),e);
            }
        });
    }

    public CompletableFuture<ResultSet> getCarInfo(){
        return CompletableFuture.supplyAsync(()->{
            try {
            String query = "Select * from car where car_id=" + this.car_id;
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException("Error getting car Info "+e.getMessage(),e);
        }
        });
    }

    public CompletableFuture<ResultSet> getOwnerInfo(int owner_id){
        return CompletableFuture.supplyAsync(()->{
            try {
                String query = "Select name,phone,profile_image from client where client_id=" + owner_id;
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                return rs;
            } catch (Exception e) {
                throw new RuntimeException("Error getting owner info "+e.getMessage(),e);
            }
        });
    }
}
