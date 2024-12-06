package controller;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Propriedade;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PropriedadeHandler implements HttpHandler {
    public static List<Propriedade> propriedades = new ArrayList<>();
    public PropriedadeHandler() {
        // Inicializa as propriedades (exemplo)
        propriedades.add(new Propriedade("Hogwarts", 500, 1));
        propriedades.add(new Propriedade("Beco Diagonal", 350, 2));
        propriedades.add(new Propriedade("Café do Três Vassouras", 250, 3));
        propriedades.add(new Propriedade("Floresta Proibida", 300, 4));
        propriedades.add(new Propriedade("Prisão", -20, 5));
        propriedades.add(new Propriedade("Cemitério de Little Hangleton", 350, 6));
        propriedades.add(new Propriedade("Vila dos Weasley", 300, 7));
        propriedades.add(new Propriedade("Fazenda de Abóbora", 250, 8));
        propriedades.add(new Propriedade("A Lufa-Lufa", 200, 9));
        propriedades.add(new Propriedade("O Expresso", 350, 10));
        propriedades.add(new Propriedade("Ministério da Magia", 550, 11));
        propriedades.add(new Propriedade("Sala de Poções", 400, 12));
        propriedades.add(new Propriedade("Estacionamento",-5, 13));
        propriedades.add(new Propriedade("A toca", 450, 14));
        propriedades.add(new Propriedade("Quarto em baixo da escada", 450, 15));
        propriedades.add(new Propriedade("Casa de Sirius Black", 450, 16));
        propriedades.add(new Propriedade("Prisão", -50,17));
        propriedades.add(new Propriedade("Quarto do Dobbly", 450, 18));
        propriedades.add(new Propriedade("Banco Gringotes", 550, 19));
        propriedades.add(new Propriedade("Câmara Secreta", 500, 20));
        propriedades.add(new Propriedade("Campo de Quadribol", 400, 21));
        propriedades.add(new Propriedade("Lago Negro", 300,22));
        propriedades.add(new Propriedade("Salão Principal de Hogwarts", 450,23));
        propriedades.add(new Propriedade("Cabana do Lupin", 350, 24));       
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Configura o tipo de resposta para JSON
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        // Converte a lista de propriedades em formato JSON manualmente
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("[");     
        for (int i = 0; i < propriedades.size(); i++) {
            Propriedade propriedade = propriedades.get(i);
            jsonResponse.append("{")
                    .append("\"nome\": \"").append(propriedade.getNome()).append("\", ")
                    .append("\"preco\": ").append(propriedade.getPreco())
                    .append("}");
            
            if (i < propriedades.size() - 1) {
                jsonResponse.append(", ");
            }
        }        
        jsonResponse.append("]");
        // Envia a resposta
        byte[] responseBytes = jsonResponse.toString().getBytes();
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
    
}

