package model;
import java.util.Random;

public class Carta {
    private String descricao;
    private TipoDeCarta tipo;

    public Carta(String descricao, TipoDeCarta tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoDeCarta getTipo() {
        return tipo;
    }

    public void aplicarEfeito(Jogador jogador) {
        // Aplica o efeito com base no tipo de carta
        System.out.println("Você tirou uma carta: " + descricao);
        Random random = new Random();
        switch (tipo) {
            case SORTE:
                aplicarEfeitoSorte(jogador, random);
                break;
            case AZAR:
                aplicarEfeitoAzar(jogador, random);
                break;
        }
    }

    // Efeito para cartas de sorte
    private void aplicarEfeitoSorte(Jogador jogador, Random random) {
        // Exemplo: Ganhar dinheiro de forma aleatória
        if (descricao.contains("ganha")) {
            double ganho = 100.0 + random.nextInt(100); // Valor aleatório entre 100 e 200
            jogador.adicionarDinheiro(ganho);
            System.out.println("Você ganhou R$" + ganho);
        }
    }

    // Efeito para cartas de azar
    private void aplicarEfeitoAzar(Jogador jogador, Random random) {
        // Exemplo: Perder dinheiro de forma aleatória
        if (descricao.contains("perde")) {
            double perda = 50.0 + random.nextInt(100); // Valor aleatório entre 50 e 150
            jogador.removerDinheiro(perda);
            System.out.println("Você perdeu R$" + perda);
        }
    }
    public enum TipoDeCarta {
        SORTE,
        AZAR
    }
}
