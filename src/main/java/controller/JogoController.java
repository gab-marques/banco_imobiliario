package controller;

import model.*;
import model.Carta.TipoDeCarta;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogoController {
    private List<Jogador> jogadores;
    private List<Carta> cartas;
    private Jogador jogadorAtivo;

    // Mudança no construtor para suportar múltiplos jogadores
    public JogoController(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.jogadorAtivo = jogadores.get(0);
        // Criando um baralho simples de cartas de sorte e azar
        cartas = new ArrayList<>();
        cartas.add(new Carta("Você perdeu R$200!", TipoDeCarta.AZAR));
        cartas.add(new Carta("Você perdeu R$100!", TipoDeCarta.AZAR));
        cartas.add(new Carta("Você ganhou uma propriedade gratuita!", TipoDeCarta.SORTE));
        cartas.add(new Carta("Você pagou multa de R$500!", TipoDeCarta.AZAR));
    }
    public Jogador getJogadorAtivo() {
        return jogadorAtivo;
    }

    public void setJogadorAtivo(Jogador jogadorAtivo) {
        this.jogadorAtivo = jogadorAtivo;
    }

    public String realizarCompra(Jogador jogador, Propriedade propriedade) {
        JogadorController jogadorController = new JogadorController();
        if (propriedade.estaComprada()) {
            return "Erro: A propriedade " + propriedade.getNome() + " já foi comprada por outro jogador.";
        }
        if (jogador.getSaldo() >= propriedade.getPreco()) {
            jogadorController.comprarPropriedade(propriedade, jogador);
            return "Sucesso: Jogador " + jogador.getNome() + " comprou a propriedade " + propriedade.getNome() + ".";
        } else {
            return "Erro: Jogador " + jogador.getNome() + " não tem saldo suficiente.";
        }
    }  
    // Método para tirar uma carta para qualquer jogador
    public void tirarCarta(Jogador jogador) {
        Random random = new Random();
        Carta carta = cartas.get(random.nextInt(cartas.size()));  // Sorteia uma carta aleatória
        carta.aplicarEfeito(jogador);
    }

    public void exibirStatus() {
        for (Jogador jogador : jogadores) {
            System.out.println(jogador);
        }
    }
}
