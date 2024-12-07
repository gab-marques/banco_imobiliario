package controller;

import model.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

public class AdicionarCasaHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            try {
                // Obter os parâmetros da URL
                URI requestURI = exchange.getRequestURI();
                String query = requestURI.getQuery();

                Map<String, String> params = parseQueryParams(query);

                String idJogador = params.get("idJogador");
                String idPropriedade = params.get("idPropriedade");

                if (idJogador == null || idPropriedade == null) {
                    sendResponse(exchange, 400, "Erro: Dados incompletos para adicionar casa!");
                    return;
                }

                // Recuperar jogador e propriedade
                Jogador jogador = carregarJogador(idJogador);
                Propriedade propriedade = recuperarPropriedade(idPropriedade);
                Jogador comprador = propriedade.getComprador();
                Integer idcomprador= (comprador != null) ? comprador.getId() : null;
                if (jogador == null || propriedade == null) {
                    sendResponse(exchange, 404, "Erro: Jogador ou propriedade não encontrados!");
                    return;
                }

                // Verificar se o jogador é o proprietário
                if(!idcomprador.equals(jogador.getId())){
                //if (!propriedade.getComprador().equals(jogador.getId())) {
                    System.out.println(idcomprador);
                    System.out.println(jogador.getId());
                    sendResponse(exchange, 403, "Erro: Você não é o dono desta propriedade!");
                    return;
                }

                // Adicionar uma casa à propriedade
                propriedade.adicionarCasa();
                jogador.removerDinheiro(propriedade.getPreco()*0.1);
                sendResponse(exchange, 200, "Uma casa foi adicionada à propriedade " + propriedade.getNome() + " com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Erro interno: " + e.getMessage());
            }
        } else {
            sendResponse(exchange, 405, "Método não permitido");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
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
        Jogador jogador = new Jogador("", 0, 0);
        String fileName = "jogador_" + idJogador + ".txt";
        jogador.carregarJogador(fileName);
        return jogador;
    }

    private Propriedade recuperarPropriedade(String idPropriedade) {
        int id = Integer.parseInt(idPropriedade);
        for (Propriedade propriedade : PropriedadeHandler.propriedades) {
            if (propriedade.getId() == id) {
                return propriedade;
            }
        }
        return null;
    }
}
