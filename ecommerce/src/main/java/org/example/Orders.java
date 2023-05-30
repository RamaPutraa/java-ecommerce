package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
public class Orders {
    private SqlConnection sqlCon;

    public Orders(SqlConnection sqlCon){
        this.sqlCon = sqlCon;
    }

    public String getOrders(String orderId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM orders INNER JOIN users ON orders.buyer=users.id WHERE orders.id =" + orderId;
        String queryDetail = "SELECT * FROM orders_details INNER JOIN products ON products.id=orders_details.'product' WHERE orders_details.'order'=" + orderId;
        String queryReview = "SELECT * FROM reviews WHERE reviews.'order'=" + orderId;
        try {
            Connection connection = sqlCon.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonOrder = new JSONObject();
                jsonOrder.put("id", resultSet.getInt("id"));
                jsonOrder.put("buyer", resultSet.getInt("buyer"));
                jsonOrder.put("note", resultSet.getInt("note"));
                jsonOrder.put("total", resultSet.getInt("total"));
                jsonOrder.put("discount", resultSet.getInt("discount"));
                jsonOrder.put("is_paid", resultSet.getString("is_paid"));
                JSONArray jsonDetail = new JSONArray();
                try{
                    Statement statementDetail = connection.createStatement();
                    ResultSet resultSetDetail = statementDetail.executeQuery(queryDetail);
                    while (resultSetDetail.next()){
                        JSONObject jsonOrderDetail = new JSONObject();
                        jsonOrderDetail.put("product", resultSetDetail.getString("title"));
                        jsonOrderDetail.put("quantity", resultSetDetail.getInt("quantity"));
                        jsonOrderDetail.put("price", resultSetDetail.getInt("price"));
                        jsonDetail.put(jsonOrderDetail);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                jsonOrder.put("order_detail", jsonDetail);
                jsonArray.put(jsonOrder);

                JSONArray jsonReviewArray = new JSONArray();
                try {
                    Statement statementReview = connection.createStatement();
                    ResultSet resultSetReview = statementReview.executeQuery(queryReview);
                    while (resultSetReview.next()){
                        JSONObject jsonReview = new JSONObject();
                        jsonReview.put("star", resultSetReview.getInt("star"));
                        jsonReview.put("description", resultSetReview.getString("description"));
                        jsonReviewArray.put(jsonReview);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                jsonOrder.put("reviews", jsonReviewArray);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

}
