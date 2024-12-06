package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Jogador {
    private String nome;
    private double saldo;
    private int id;
    private ArrayList<Propriedade> propriedades;
    private static final String FILE_PATH = "jogadores.txt"; // Local para salvar os dados

    public Jogador(String nome, double saldoInicial, int id) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.id = id;
        this.propriedades = new ArrayList<>();
    }
    public Jogador(String id){   
        Jogador jogador = carregarDeArquivo("jogador_"+id+".txt");
        this.id = jogador.id;
        this.nome = jogador.nome;
        this.saldo = jogador.saldo;
        this.propriedades = jogador.propriedades;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
        salvarNoArquivo(); 
    }

    public int getId() {
        return id;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }
    public void adicionarPropriedade(Propriedade propriedade) {
        if (this.propriedades == null) {
            this.propriedades = new ArrayList<>();
        }
        this.propriedades.add(propriedade);  // Adiciona a propriedade individualmente
    }

    public void salvarNoArquivo() {
        String fileName = "jogador_" + this.id + ".txt";
        
        // Montar o conteúdo atualizado
        StringBuilder conteudoArquivo = new StringBuilder();
        conteudoArquivo.append("Nome: ").append(this.nome).append(System.lineSeparator());
        conteudoArquivo.append("Saldo: ").append(this.saldo).append(System.lineSeparator());
        conteudoArquivo.append("ID: ").append(this.id).append(System.lineSeparator());
    
        // Adicionar as propriedades
        if (this.propriedades != null && !this.propriedades.isEmpty()) {
            if (this.propriedades.size() == 1) {
                conteudoArquivo.append("Propriedades: ");
            }
            for (Propriedade propriedade : this.propriedades) {
                conteudoArquivo.append(propriedade.getNome())
                        .append(" (ID: ").append(propriedade.getId())
                        .append(", Preço: ").append(propriedade.getPreco())
                        .append("); ");
            }
        }
        conteudoArquivo.append(System.lineSeparator());
    
        // Escrever o conteúdo atualizado no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(conteudoArquivo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void criarArquivo() {
        String fileName = "jogador_" + this.id + ".txt";
        
        // Montar o conteúdo atualizado
        StringBuilder conteudoArquivo = new StringBuilder();
        conteudoArquivo.append("Nome: ").append(this.nome).append(System.lineSeparator());
        conteudoArquivo.append("Saldo: ").append(this.saldo).append(System.lineSeparator());
        conteudoArquivo.append("ID: ").append(this.id).append(System.lineSeparator());
        // Adicionar as propriedades
        if (this.propriedades != null && !this.propriedades.isEmpty()) {
            conteudoArquivo.append("Propriedades: ");
            for (Propriedade propriedade : this.propriedades) {
                conteudoArquivo.append(propriedade.getNome())
                        .append(" (ID: ").append(propriedade.getId())
                        .append(", Preço: ").append(propriedade.getPreco())
                        .append("); ");
            }
        }
        conteudoArquivo.append(System.lineSeparator());
    
        // Escrever o conteúdo atualizado no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(conteudoArquivo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
// Lê o jogador de um arquivo
public Jogador carregarDeArquivo(String fileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String nome = reader.readLine().split(": ")[1].trim();
        double saldo = Double.parseDouble(reader.readLine().split(": ")[1].trim());
        int id = Integer.parseInt(reader.readLine().split(": ")[1].trim());

        // Lê a linha "Propriedades: "
        String linhaPropriedades = reader.readLine();
        List<Propriedade> propriedades = new ArrayList<>();

        if (linhaPropriedades != null && linhaPropriedades.startsWith("Propriedades: ")) {
            String propriedadesStr = linhaPropriedades.substring(14);  // Retira "Propriedades: "
            String[] props = propriedadesStr.split("; ");
            for (String prop : props) {
                String[] propDetails = prop.split(" \\(ID: |, Preço: ");
                String propNome = propDetails[0];
                int propId = Integer.parseInt(propDetails[1]);
                double propPreco = Double.parseDouble(propDetails[2].replace(")", ""));
                this.propriedades.add(new Propriedade(propNome, propPreco, propId));
            }
        }

        Jogador jogador = new Jogador(nome, saldo, id);

        // Adiciona as propriedades carregadas individualmente
        for (Propriedade propriedade : propriedades) {
            jogador.adicionarPropriedade(propriedade);  // Adiciona uma por vez
        }

        return jogador;

    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

public void carregarJogador(String fileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String nome = reader.readLine().split(": ")[1].trim();
        double saldo = Double.parseDouble(reader.readLine().split(": ")[1].trim());
        int id = Integer.parseInt(reader.readLine().split(": ")[1].trim());
        // Atualizar os atributos do jogador
        this.nome = nome;
        this.saldo = saldo;
        this.id = id;
        // Ler a linha das propriedades
        String propriedadesLinha = reader.readLine();

        if (propriedadesLinha != null && propriedadesLinha.contains(": ")) {
            // Realiza o split usando ": "
            String[] partes = propriedadesLinha.split(": ");
            
            if (partes.length > 1) {
                // Se o array tiver mais de um elemento, então podemos acessar o índice 1
                String valor = partes[1].trim();
                System.out.println("Valor extraído: " + valor);
            } else {
                // Caso o split não tenha gerado mais de um elemento
                System.out.println("Linha não contém um valor após ': '.");
            }
        }
        
        // Dividir as propriedades e verificar duplicatas
        if (this.propriedades == null) {
            this.propriedades = new ArrayList<>();
        }
        String[] propriedadesArray = propriedadesLinha.split(";");

        for (String prop : propriedadesArray) {
            prop = prop.trim(); // Remover espaços em branco extras
            if (!prop.isEmpty()) {
                // Extrair informações da propriedade
                String nomePropriedade = prop.substring(0, prop.indexOf(" (")).trim();
                String idStr = prop.substring(prop.indexOf("ID: ") + 4, prop.indexOf(", Preço")).trim();
                String precoStr = prop.substring(prop.indexOf("Preço: ") + 7, prop.indexOf(")")).trim();
                int idPropriedade = Integer.parseInt(idStr);
                double precoPropriedade = Double.parseDouble(precoStr);

                // Criar o objeto Propriedade
                Propriedade propriedade = new Propriedade(nomePropriedade, precoPropriedade, idPropriedade);

                // Adicionar somente se ainda não existe na lista
                if (!this.propriedades.contains(propriedade)) {
                    this.propriedades.add(propriedade);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void adicionarDinheiro(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Você adicionou R$" + valor + ". Novo saldo: R$" + saldo);
            salvarNoArquivo();
        } else {
            System.out.println("Valor inválido para adicionar.");
        }
    }
        public void removerDinheiro(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            salvarNoArquivo(); // Salva o jogador após modificar o dinheiro
        } else {
            System.err.println("Jogador não tem dinheiro suficiente.");
        }
    }
        public void hipotecarPropriedade(String nomePropriedade) {
            for (Propriedade propriedade : propriedades) {
                if (propriedade.getNome().equals(nomePropriedade)) {
                    double valorHipoteca = propriedade.hipotecar(); 
                    adicionarDinheiro(valorHipoteca);
                    return;
                }
            }
            System.out.println("Propriedade não encontrada.");
        }
    @Override
    public String toString() {
        return nome + " - Saldo: R$" + saldo + " - Propriedades: " + propriedades.size();
    }
}