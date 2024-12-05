package controller;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Jogador;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class IniciarJogoHandler implements HttpHandler {
    private List<Jogador> jogadores;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // Lê o corpo da requisição
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            // Extrai o número de jogadores do corpo da requisição
            String requestData = requestBody.toString();
            int numJogadores = Integer.parseInt(requestData.split(":")[1].replaceAll("[^0-9]", ""));

            // Inicializa a lista de jogadores
            jogadores = new ArrayList<>();
            for (int i = 1; i <= numJogadores; i++) {
                Jogador jogador = new Jogador("Player " + i, 2000, i);
                jogador.criarArquivo(); // Salva cada jogador em um arquivo único
                jogadores.add(jogador);
            }

            // Prepara a resposta com os dados dos jogadores
            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("{\"jogadores\": [");

            for (int i = 0; i < jogadores.size(); i++) {
                Jogador jogador = jogadores.get(i);
                responseBuilder.append("{\"nome\": \"").append(jogador.getNome())
                               .append("\", \"saldo\": ").append(jogador.getSaldo()).append("}");
                if (i < jogadores.size() - 1) {
                    responseBuilder.append(", ");
                }
            }
            responseBuilder.append("]}");

            // Envia a resposta com cabeçalho adequado para JSON
            String response = responseBuilder.toString();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            byte[] responseBytes = response.getBytes();
            exchange.sendResponseHeaders(200, responseBytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        } else {
            exchange.sendResponseHeaders(405, -1); // Método não permitido
        }
    }
}

