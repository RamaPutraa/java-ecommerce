package org.example;
import java.io.IOException;
import org.json.simple.JSONObject;

import com.sun.net.httpserver.HttpExchange;

public  class Request {
    public void handleGetRequest(HttpExchange exchange, String[] path, String query) throws IOException{
        Response response = new Response();
        Data data = new Data();
        if ("users".equals(path[1])) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = data.selectDatabase(path, query);
            // System.out.print(jsonObject.toString());
            response.getResponse(exchange, jsonObject.toString(), path, "USERS",200);
        } else if ("orders".equals(path[1])) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = data.selectDatabase(path, query);
            response.getResponse(exchange, jsonObject.toString(), path, "ORDERS", 200);
        } else if ("products".equals(path[1])) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = data.selectDatabase(path, query);
            response.getResponse(exchange, jsonObject.toString(), path, "PRODUCTS", 200);
        }else {
            response.sendResponse(exchange, "\n<h1><center>404</center></h1>\n<h3><center>Not Found!</center></h3>");
        }
    }

    public void handlePostRequest(){

    }

    public void handlePutRequest(){

    }

    public void handleDeleteRequest(){

    }

}