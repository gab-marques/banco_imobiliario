package model;

public class Propriedade {
    private String nome;
    private int id;
    private double preco;
    private double valorHipoteca;
    private boolean hipotecada;
    private boolean comprada;
    private Jogador comprador;
    private int casa;
    

    public Propriedade(String nome, double preco, int id) {
        this.nome = nome;
        this.preco = preco;
        this.valorHipoteca = preco / 2; 
        this.hipotecada = false; 
        this.comprada = false;
        this.comprador = null; 
        this.id = id;
        this.casa = casa;
    }
  

    public String getNome() {
        return nome;
    }
    
    public int getId() {
        return id;
    }
    public double getPreco() {
        return preco;
    }
    public double getValorHipoteca() {
        return valorHipoteca;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }
    public void setComprada(boolean comprada) {
        this.comprada = comprada;
    }
    public boolean estaComprada() {
        return this.comprada;
    }
    public Jogador getComprador() {
        return this.comprador;
    }
    public void setComprador(Jogador comprador) {
        this.comprador = comprador;
    }
     // Método para hipotecar a propriedade
     public double hipotecar() {
        if (!hipotecada) {
            hipotecada = true;
            System.out.println("Propriedade " + nome + " foi hipotecada. Valor da hipoteca: R$" + valorHipoteca);
            return valorHipoteca; // Retorna o valor da hipoteca
        } else {
            System.out.println("Propriedade já está hipotecada.");
            return 0;
        }
    }
    public void quitarHipoteca() {
        if (hipotecada) {
            hipotecada = false;
            System.out.println("Propriedade " + nome + " foi deshipotecada.");
        } else {
            System.out.println("Propriedade não está hipotecada.");
        }
    }
    public void comprar(Jogador comprador) {
        if (estaComprada()) {
            System.out.println("A propriedade " + nome + " já foi comprada.");
        } else {
            this.comprada = true;
            this.comprador = comprador; 
            comprador.getPropriedades().add(this); 
            System.out.println("A propriedade " + nome + " foi comprada por " + comprador.getNome() + ".");
        }
    }

    @Override
    public String toString() {
        return nome + " - Preço: R$" + preco;
    }
}
