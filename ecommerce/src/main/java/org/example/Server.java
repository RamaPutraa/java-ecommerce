package org.example;

import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


// import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public  class Server implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();
        Request request = new Request();
        // Connect connect = new Connect();
        if("GET".equals(exchange.getRequestMethod())){
            // connect.getConnection(path);
            request.handleGetRequest(exchange,path,query);
        }
        if("POST".equals(exchange.getRequestMethod())){
            // handleGetRequest(exchange,path,query);
        }
        if("PUT".equals(exchange.getRequestMethod())){
            // handleGetRequest(exchange,path,query);
        }
        if("DELETE".equals(exchange.getRequestMethod())){
            // handleGetRequest(exchange,path,query);
        }
    }
}