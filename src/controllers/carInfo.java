package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import app.config;

public class carInfo {
    Connection connect = config.getConnection();
    private int car_id;

    public carInfo(int car_id) {
        this.car_id = car_id;
    }

    public ResultSet getCarImages() {
        try {
            String query = "Select * from carimage where car_id=" + this.car_id + " and is_primary=false";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            System.out.println("Error getting car images " + e.getMessage());
            return null;
        }
    }
}
