import com.sun.net.httpserver.HttpServer;
import controller.HomeController;
import controller.IniciarJogoHandler;
import controller.JogarDadosHandler;
import controller.PropriedadeHandler;
import controller.ComprarPropriedadeHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ServidorHTTP {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        

        // Cria as rotas e associa os handlers
        server.createContext("/", new HomeController());
        server.createContext("/jogarDados", new JogarDadosHandler());  // Rota para jogar dados
        server.createContext("/comprarPropriedade", new ComprarPropriedadeHandler()); // Rota para comprar propriedade
        server.createContext("/propriedades", new PropriedadeHandler()); 
        server.createContext("/iniciarJogo", new IniciarJogoHandler());
        server.setExecutor(null); // Usa o executor padrão
        System.out.println("Servidor HTTP iniciado na porta 8080...");
        server.start();
    }
}
