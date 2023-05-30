package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class HttpConnection {
    private UsersHandler usersHandler;
//    private ProductsHandler productsHandler;
//    private OrdersHandler ordersHandler;

    public HttpConnection(){
        SqlConnection sqlcon = new SqlConnection();
        usersHandler = new UsersHandler(sqlcon);
//        productsHandler = new ProductsHandler(sqlcon);
//        ordersHandler = new OrdersHandler(sqlcon);
    }

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8127), 0);
        httpServer.createContext("/", new Handler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    private class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String[] path = exchange.getRequestURI().getPath().split("/");
            String query = exchange.getRequestURI().getQuery();
            String response = "";
            if(method.equals("GET")){
                if(path[1].equals("users")){
                    response = usersHandler.getUsersMethod(path, query);
                }else if(path[1].equals("products")){
//                    response = productsHandler.getProducts(0);
                }else if(path[1].equals("orders")){
//                    response = ordersHandler.getOrders(path[2]);
                }
            }else if(method.equals("DELETE")){

            }else if(method.equals("POST")){

            }else if(method.equals("PUT")){

            }
            OutputStream outputStream = exchange.getResponseBody();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        private JSONObject parseRequestBody(InputStream requestBody) throws IOException {
            byte[] requestBodyBytes = requestBody.readAllBytes();
            String requestBodyString = new String(requestBodyBytes);
            return new JSONObject(requestBodyString);
        }
    }
}