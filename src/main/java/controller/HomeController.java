package controller;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String filePath = "view" + exchange.getRequestURI().getPath();
        if (filePath.equals("view/")) {
            filePath = "view/index.html";  // Define o index.html como padrão
        }

        // Verifica se o arquivo existe
        if (Files.exists(Paths.get(filePath))) {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

            // Define o tipo de conteúdo com base na extensão do arquivo
            if (filePath.endsWith(".html")) {
                exchange.getResponseHeaders().add("Content-Type", "text/html");
            } else if (filePath.endsWith(".css")) {
                exchange.getResponseHeaders().add("Content-Type", "text/css");
            } else if (filePath.endsWith(".js")) {
                exchange.getResponseHeaders().add("Content-Type", "application/javascript");
            }

            // Envia o conteúdo do arquivo
            exchange.sendResponseHeaders(200, fileContent.length);
            OutputStream os = exchange.getResponseBody();
            os.write(fileContent);
            os.close();
        } else {
            // Se o arquivo não for encontrado, retorna 404
            String response = "404 (Not Found)\n";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
