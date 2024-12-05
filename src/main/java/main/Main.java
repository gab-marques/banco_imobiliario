// package main;
// import model.*;
// import controller.JogoController;
// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         // Criando jogadores
//         Jogador jogador1 = new Jogador("Jogador 1", 1000.0,1 );
//         Jogador jogador2 = new Jogador("Jogador 2", 500.0, 2);

//         // Criando propriedades
//         Propriedade casa1 = new Propriedade("Casa 1", 300.0);
//         Propriedade casa2 = new Propriedade("Casa 2", 700.0);
//         //Propriedade casa3 = new Propriedade("Casa 3", 150.0);

//         // Criando o controlador de jogo
//         JogoController controlador = new JogoController(jogador1, jogador2);

//         // Criando o scanner para ler as escolhas
//         Scanner scanner = new Scanner(System.in);

//         // Exibindo saldo inicial dos jogadores
//         controlador.exibirStatus();

//         // Jogador 1 comprando propriedades
//         realizarCompra(scanner, controlador, jogador1, casa1);
//         realizarCompra(scanner, controlador, jogador1, casa2);


//         // Exibindo saldo e propriedades finais dos jogadores
//         System.out.println("\nApós as compras e cartas:");
//         controlador.exibirStatus();
//     }

//     private static void realizarCompra(Scanner scanner, JogoController controlador, Jogador jogador, Propriedade propriedade) {
//         // Realiza apenas uma chamada ao método da JogoController
//         controlador.realizarCompra(jogador, propriedade);
//     }

//     private static void perguntarTirarCarta(Scanner scanner, JogoController controlador, Jogador jogador) {
//         System.out.println("\n" + jogador.getNome() + ", você quer tirar uma carta? (s/n)");
//         String resposta = scanner.nextLine().trim().toLowerCase();

//         if (resposta.equals("s")) {
//             controlador.tirarCarta(jogador);
//             double valorNecessario = 100.0; // Exemplo: valor necessário para pagar uma carta de azar

//             // Verificando se o jogador tem saldo suficiente para pagar
//             if (jogador.getSaldo() < valorNecessario) {
//                 System.out.println(jogador.getNome() + " não tem saldo suficiente para pagar a carta de azar.");
//                 // Se o jogador não tem saldo suficiente, chamamos o método de hipoteca
//                 controlador.hipotecaPropriedade(jogador, valorNecessario - jogador.getSaldo());
                
//                 // Após tentar hipotecar, se o saldo ainda for insuficiente, avisa o jogador
//                 if (jogador.getSaldo() < valorNecessario) {
//                     System.out.println(jogador.getNome() + " ainda não tem saldo suficiente para pagar a carta de azar.");
//                 } else {
//                     System.out.println(jogador.getNome() + " conseguiu pagar a carta de azar após hipotecar.");
//                 }
//             } else {
//                 // Caso o jogador tenha saldo suficiente
//                 jogador.adicionarDinheiro(-valorNecessario); // Deduz o valor da carta
//                 System.out.println(jogador.getNome() + " pagou a carta de azar.");
//             }
//         } else {
//             System.out.println(jogador.getNome() + " decidiu não tirar uma carta.");
//         }
//     }

//     // Método que permite ao jogador decidir hipotecar uma propriedade
//     private static void perguntarHipotecar(Scanner scanner, JogoController controlador, Jogador jogador) {
//         System.out.println("\n" + jogador.getNome() + ", você quer hipotecar alguma propriedade? (s/n)");
//         String resposta = scanner.nextLine().trim().toLowerCase();

//         if (resposta.equals("s")) {
//             System.out.println("Quanto dinheiro você precisa?");
//             double valorNecessario = scanner.nextDouble();
//             controlador.hipotecaPropriedade(jogador, valorNecessario); // Chama a hipoteca
//         } else {
//             System.out.println(jogador.getNome() + " decidiu não hipotecar nenhuma propriedade.");
//         }
//     }
// }
