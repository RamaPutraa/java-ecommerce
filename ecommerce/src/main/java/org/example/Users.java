package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class Users {
    private SqlConnection sqlConnection;

    public Users(SqlConnection sqlConnection){
        this.sqlConnection = sqlConnection;
    }

    public String getUsers(int usersId, String addedQuery){
        JSONArray jsonArray = new JSONArray();
        String querySQL = "";

        if (usersId == 0) {
            querySQL = "SELECT * FROM users";
        }else if(addedQuery != null) {
            if(addedQuery.contains("type=buyer")){
                querySQL = "SELECT * FROM users WHERE type='Buyer'";
            }else if(addedQuery.contains("type=seller")){
                querySQL = "SELECT * FROM users WHERE type='Seller'";
            }else{
                String[] query = addedQuery.split("&");
                String queryField = "";
                String queryCondition = "";
                int queryValue = 0;
                for(int i = 0; i < query.length; i++){
                    if(query[i].contains("field")){
                        queryField = query[i].substring(query[i].lastIndexOf("=") + 1);
                    }else if(query[i].contains("val")){
                        queryValue = Integer.parseInt(query[i].substring(query[i].lastIndexOf("=") + 1));
                    }else if(query[i].contains("cond")){
                        String cond = query[i].substring(query[i].lastIndexOf("=") + 1);
                        if(cond.equals("larger")){
                            queryCondition = ">";
                        }else if(cond.equals("largerEqual")){
                            queryCondition = ">=";
                        }else if(cond.equals("smaller")){
                            queryCondition = "<";
                        }else if(cond.equals("smallerEqual")){
                            queryCondition = "<=";
                        }
                    }
                }
                querySQL = "SELECT * FROM users WHERE " + queryField + queryCondition + " " + queryValue + " ";
            }
        }else{
            querySQL = "SELECT * FROM users WHERE id=" + usersId;
        }

        try {
            Connection connection = sqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySQL);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("firstName", resultSet.getString("first_name"));
                jsonUSer.put("lastName", resultSet.getString("last_name"));
                jsonUSer.put("email", resultSet.getString("email"));
                jsonUSer.put("phoneNumber", resultSet.getString("phone_number"));
                jsonUSer.put("type", resultSet.getString("type"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUsersMethod(String[] path, String query){
        String response = "";
        if(path.length == 2){
            if(query != null){
                response = getUsers(-100, query);
            }else{
                response = getUsers(0, query);
            }
        }else if(path.length == 3){
            response = getUsers(Integer.parseInt(path[2]), query);
        }else if(path.length == 4){
            if(path[3].equals("products")){
                response = getUserProducts(Integer.parseInt(path[2]));
            }else if(path[3].equals("orders")){
                response = getUsersOrders(path[2]);
            }else if(path[3].equals("reviews")){
                response = getUserReview(path[2]);
            }
        }
        return response;
    }

    public String getUsersOrders(String usersId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM orders WHERE buyer=" + usersId;
        try{
            Connection connection = sqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("buyer", resultSet.getInt("buyer"));
                jsonUSer.put("note", resultSet.getInt("note"));
                jsonUSer.put("total", resultSet.getInt("total"));
                jsonUSer.put("discount", resultSet.getInt("discount"));
                jsonUSer.put("is_paid", resultSet.getString("is_paid"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUserProducts(int userId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM products WHERE seller=" + userId;
        try {
            Connection connection = sqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("seller", resultSet.getInt("seller"));
                jsonUSer.put("title", resultSet.getString("title"));
                jsonUSer.put("description", resultSet.getString("description"));
                jsonUSer.put("price", resultSet.getString("price"));
                jsonUSer.put("stock", resultSet.getInt("stock"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUserReview(String userId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM reviews INNER JOIN orders ON orders.id=reviews.'order' WHERE orders.buyer=" + userId;
        try {
            Connection connection = sqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("order", resultSet.getInt("order"));
                jsonUSer.put("star", resultSet.getInt("star"));
                jsonUSer.put("description", resultSet.getString("description"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }
}