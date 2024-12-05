package controller;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
public class JogarDadosHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            // Simula o lançamento dos dados
            int dado1 = (int)(Math.random() * 6) + 1;
            int dado2 = (int)(Math.random() * 6) + 1;
            String response = "Você rolou: " + dado1 + " e " + dado2;
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "Método não permitido!";
            exchange.sendResponseHeaders(405, response.getBytes().length); 
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
