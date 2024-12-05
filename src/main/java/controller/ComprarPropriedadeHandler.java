package controller;
import model.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class ComprarPropriedadeHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                // Obter os parâmetros da URL
                URI requestURI = exchange.getRequestURI();
                String query = requestURI.getQuery();
                Map<String, String> params = parseQueryParams(query);

                String idJogador = params.get("idJogador");
                String idPropriedade = params.get("idPropriedade");

                if (idJogador == null || idPropriedade == null) {
                    sendResponse(exchange, 400, "Erro: Dados incompletos para a compra!");
                    return;
                }
                
                // Recuperar jogador e propriedade (simulação, ajuste conforme sua implementação real)
                 Jogador jogador = carregarJogador(idJogador); // Agora estamos carregando o jogador do arquivo
                Propriedade propriedade = recuperarPropriedade(idPropriedade);

                if (jogador == null || propriedade == null) {
                    sendResponse(exchange, 404, "Erro: Jogador ou propriedade não encontrados!");
                    return;
                }

                // Realiza a compra
                List<Jogador> jogadores = List.of(jogador); // Simula apenas um jogador para a compra
                JogoController jogoController = new JogoController(jogadores);
                String resultado = jogoController.realizarCompra(jogador, propriedade);

                sendResponse(exchange, 200, resultado);
            } catch (Exception e) {
                sendResponse(exchange, 500, "Erro interno: " + e.getMessage());
            }
        } else {
            sendResponse(exchange, 405, "Método não permitido");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseQueryParams(String query) {
        Map<String, String> params = new java.util.HashMap<>();
        for (String pair : query.split("&")) {
            String[] keyValue = pair.split("=");
            if (keyValue.length > 1) {
                params.put(keyValue[0], java.net.URLDecoder.decode(keyValue[1], java.nio.charset.StandardCharsets.UTF_8));
            }
        }
        return params;
    }

    private Jogador carregarJogador(String idJogador) {
        // Aqui chamamos o método da classe Jogador para carregar o jogador do arquivo
        Jogador jogador = new Jogador("", 0, 0);
        String fileName = "jogador_" + idJogador+".txt";
        jogador.carregarJogador(fileName);
        return jogador;
    }

        // Método para recuperar a propriedade pelo ID
    private Propriedade recuperarPropriedade(String idPropriedade) {
        // Converte o idPropriedade para inteiro (assumindo que idPropriedade é um String representando um número)
        int id = Integer.parseInt(idPropriedade);
        // Percorre a lista de propriedades estática para encontrar a propriedade com o id correspondente
        for (Propriedade propriedade : PropriedadeHandler.propriedades) {
            if (propriedade.getId() == id) {
                return propriedade;  // Retorna a propriedade encontrada
            }
        }
        return null;  // Retorna null se não encontrar a propriedade com o id correspondente
    }
}
        

