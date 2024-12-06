package controller;
import java.util.ArrayList;
import java.util.List;

import model.Jogador;
import model.Propriedade;

public class JogadorController {
    public void comprarPropriedade(Propriedade propriedade, Jogador jogador) {
        if (!propriedade.estaComprada()) {
            if (jogador.getSaldo() >= propriedade.getPreco()) {
                // Atualizar o saldo do jogador
                jogador.setSaldo(jogador.getSaldo() - propriedade.getPreco());          
                // Adiciona a propriedade diretamente à lista do jogador
                jogador.adicionarPropriedade(propriedade);
                // Marca a propriedade como comprada
                propriedade.setComprada(true); 
                propriedade.setComprador(jogador);   
                // Salva o jogador atualizado
                jogador.salvarNoArquivo();
                
                System.out.println(jogador.getNome() + " comprou a propriedade " + propriedade.getNome());
            } else {
                System.out.println(jogador.getNome() + " não tem saldo suficiente para comprar " + propriedade.getNome());
            }
        } else {
            System.out.println("A propriedade " + propriedade.getNome() + " já foi comprada por outro jogador.");
        }
    }
    
}
    

