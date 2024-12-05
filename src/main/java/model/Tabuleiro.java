package model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<String> casas;

    public Tabuleiro() {
        casas = new ArrayList<>();
        inicializarCasas();
    }

    private void inicializarCasas() {
        casas.add("Início");
        casas.add("Terreno 1");
        casas.add("Terreno 2");
        casas.add("Sorte ou Revés");
        casas.add("Terreno 3");
        casas.add("Prisão");
        // Adicione mais casas conforme necessário.
    }

    public String getCasa(int posicao) {
        return casas.get(posicao % casas.size());
    }

    public int getTotalCasas() {
        return casas.size();
    }
}
