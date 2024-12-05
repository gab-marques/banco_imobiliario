package model;

public class Banco {
    private int totalDinheiro;

    public Banco(int dinheiroInicial) {
        this.totalDinheiro = dinheiroInicial;
    }

    public int getTotalDinheiro() {
        return totalDinheiro;
    }

    public void ajustarSaldo(int valor) {
        totalDinheiro += valor;
    }
}
