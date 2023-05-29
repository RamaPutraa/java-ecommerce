package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import org.json.simple.JSONArray;

public class Data {
    public JSONObject selectDatabase(String[] path, String query){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../../SQLite/Database/ecommerce.db");
            Statement statement = connection.createStatement();
            System.out.println("connection berhasil");
            if(path[1].equals("users")){
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                if(path.length == 2){
                    ResultSet rs = statement.executeQuery("select * from " + path[1]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Id", rs.getInt("id_user"));
                        record.put("First_Name", rs.getString("first_name"));
                        record.put("Last_Name", rs.getString("last_name"));
                        record.put("Email", rs.getString("email"));
                        record.put("Phone Number", rs.getString("phone_number"));
                        record.put("Type", rs.getString("type"));
                        array.add(record);
                    }
                    jsonObject.put("User Information", array);
                    return jsonObject;
                }
                if(path.length == 3){
                    ResultSet rs = statement.executeQuery("select * from " + path[1] + " where id=" + path[2]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Id", rs.getInt("id_user"));
                        record.put("First_Name", rs.getString("first_name"));
                        record.put("Last_Name", rs.getString("last_name"));
                        record.put("Email", rs.getString("email"));
                        record.put("Phone Number", rs.getString("phone_number"));
                        record.put("Type", rs.getString("type"));
                        array.add(record);
                    }
                    jsonObject.put("User Information", array);
                    return jsonObject;
                }
            }
            else if(path[1].equals("products")){
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                System.out.println(query);
                if(path.length == 2){
                    ResultSet rs = statement.executeQuery("select * from " + path[1]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Stock", rs.getInt("stock"));
                        record.put("Price", rs.getString("price"));
                        record.put("Description", rs.getString("description"));
                        record.put("Title", rs.getString("title"));
                        record.put("Id", rs.getInt("id_product"));
                        array.add(record);
                    }
                    jsonObject.put("Product Information", array);
                    return jsonObject;
                }
                if(path.length == 3){
                    ResultSet rs = statement.executeQuery("select * from " + path[1] + " where id_products=" + path[2]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Stock", rs.getInt("stock"));
                        record.put("Price", rs.getString("price"));
                        record.put("Description", rs.getString("description"));
                        record.put("Title", rs.getString("title"));
                        record.put("Id", rs.getInt("id_product"));
                        array.add(record);
                    }
                    jsonObject.put("Product Information", array);
                    return jsonObject;
                }
            }
            else if(path[1].equals("orders")){
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                if(path.length == 2){
                    ResultSet rs = statement.executeQuery("select * from " + path[1]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("isPaid", rs.getInt("is_paid"));
                        record.put("Discount", rs.getInt("discount"));
                        record.put("Total", rs.getInt("total"));
                        record.put("Note", rs.getInt("note"));
                        record.put("Id", rs.getInt("id_order"));
                        array.add(record);
                    }
                    jsonObject.put("Orders Information", array);
                    return jsonObject;
                }
                if(path.length == 3){
                    ResultSet rs = statement.executeQuery("select * from " + path[1] + " where id=" + path[2]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("isPaid", rs.getInt("isPaid"));
                        record.put("Discount", rs.getInt("discount"));
                        record.put("Total", rs.getInt("total"));
                        record.put("Note", rs.getInt("note"));
                        record.put("Id", rs.getInt("id_order"));
                        array.add(record);
                    }
                    jsonObject.put("Orders Information", array);
                    return jsonObject;
                }
            }

        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    // private JSONObject getData(JSONObject object, JSONArray array, ResultSet rs){

    // }
}