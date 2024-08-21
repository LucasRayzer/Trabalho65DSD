package trabalho01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            String ipServidor = JOptionPane.showInputDialog("Informe o endereço do servidor:", "10.15.120.65");
           
            while (true) {
                String choiceStr = JOptionPane.showInputDialog("Escolha a operação: \n"
                        + "1: Inserir dados de pessoa\n"
                        + "2: Atualizar dados de pessoa\n"
                        + "3: Obter dados de pessoa\n"
                        + "4:Remover pessoa\n"
                        + "5: Listar todas as pessoas\n"
                        + "6: Criar nova turma\n"
                        + "7: Atualizar dados de turma\n"
                        + "8: Obter dados de turma\n"
                        + "9: Remover turma\n"
                        + "10: Retornar todas as turma\n"
                        + "11: Adicionar participante a turma\n"
                        + "12: Remover participante da turma");

                if (choiceStr.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    continue;
                }
                
                Socket socket = new Socket(ipServidor, 80);
                BufferedReader server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1 ->
                        inserirPessoa(server, reader, writer);
                    case 2 ->
                        atualizarPessoa(server, reader, writer);
                    case 3 ->
                        obterPessoa(server, reader, writer);
                    case 4 ->
                        removerPessoa(server, reader, writer);
                    case 5 ->
                        listarPessoas(server, writer);
                    case 6 ->
                        adicionarTurma(server, reader, writer);
                    case 7 ->
                        editarTurma(server, reader, writer);
                    case 8 ->
                        buscarTurma(server, reader, writer);
                    case 9 ->
                        removerTurma(server, reader, writer);
                    case 10 ->
                        buscarTodasTurma(server, writer);
                    case 11 ->
                        adicionarPessoaTurma(server, reader, writer);
                    case 12 ->
                        removerPessoaTurma(server, reader, writer);
                    default ->
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
                socket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inserirPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("INSERT_PESSOA");

        boolean continuar = true;
        String escolha = "";
        while (continuar) {
            escolha = JOptionPane.showInputDialog("1: Criar partipante\n2: Criar palestrante");
            if (escolha.equals("1") || escolha.endsWith("2")) {
                continuar = false;
                writer.println(escolha);
            } else {
                JOptionPane.showMessageDialog(null, "Escolha uma das opções cadastradas.");
            }
        }

        String cpf = JOptionPane.showInputDialog("Insira o CPF");
        writer.println(cpf);
        
        String nome = JOptionPane.showInputDialog("Insira o nome");
        writer.println(nome);

        String endereco = JOptionPane.showInputDialog("Insira o endereço");
        writer.println(endereco);

        if (escolha.equals("1")) {
            String valorContribuicao = JOptionPane.showInputDialog("Entre com o valor da contribuição");
            writer.println(valorContribuicao);
        } else {
            String titulo = JOptionPane.showInputDialog("Entre com o título da palestra");
            writer.println(titulo);
        }

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void atualizarPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("UPDATE_PESSOA");

        String cpf = JOptionPane.showInputDialog("Insira o CPF da pessoa que deseja atualizar:");
        writer.println(cpf);

        String nome = JOptionPane.showInputDialog("Insira o novo nome:");
        writer.println(nome);

        String endereco = JOptionPane.showInputDialog("Insira o novo endereço:");
        writer.println(endereco);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void obterPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("GET_PESSOA");
        
        String cpf = JOptionPane.showInputDialog("Insira o CPF da pessoa que deseja obter os dados:");
        writer.println(cpf);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void removerPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("DELETE_PESSOA");

        String cpf = JOptionPane.showInputDialog("Insira o CPF da pessoa que deseja remover:");
        writer.println(cpf);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void listarPessoas(BufferedReader server, PrintWriter writer) throws IOException {
        writer.println("LIST_PESSOA");
        String response = server.readLine();
        int quantidade = Integer.parseInt(response);

        if (quantidade == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma pessoa cadastrada.");
        } else {
            String pessoas = "";
            for (int i = 0; i < quantidade; i++) {
                String pessoa = server.readLine();
                pessoas += pessoa + "\n";
            }
            JOptionPane.showMessageDialog(null, "Quantidade de registros: " + quantidade + "\n" + pessoas);
        }
    }

    public static void adicionarTurma(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("INSERT_TURMA");

        String descricao = JOptionPane.showInputDialog("Entre com a descrição da turma");
        writer.println(descricao);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void removerTurma(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("DELETE_TURMA");
       
        String codigo = JOptionPane.showInputDialog("Entre com o código da turma");
        writer.println(codigo);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void editarTurma(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("UPDATE_TURMA");

        String codigo = JOptionPane.showInputDialog("Entre com o código da turma");
        writer.println(codigo);
        
        String descricao = JOptionPane.showInputDialog("Atualize a descrição da turma");
        writer.println(descricao);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void buscarTurma(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("GET_TURMA");

        String codigo = JOptionPane.showInputDialog("Entre com o código da turma");
        writer.println(codigo);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void buscarTodasTurma(BufferedReader server, PrintWriter writer) throws IOException {
        writer.println("LIST_TURMA");

        String response = server.readLine();
        int quantidade = Integer.parseInt(response);

        if (quantidade == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma pessoa cadastrada.");
        } else {
            String turmas = "";
            for (int i = 0; i < quantidade; i++) {
                String turma = server.readLine();
                turmas += turma + "\n";
            }
            JOptionPane.showMessageDialog(null, "Quantidade de registros: " + quantidade + "\n" + turmas);
        }
    }

    public static void adicionarPessoaTurma(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("INSERT_PESSOA_TURMA");

        String codigo = JOptionPane.showInputDialog("Entre com o código da turma");
        writer.println(codigo);

        String cpf = JOptionPane.showInputDialog("Entre com o CPF da pessoa");
        writer.println(cpf);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }

    public static void removerPessoaTurma(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("REMOVE_PESSOA_TURMA");

        String codigo = JOptionPane.showInputDialog("Entre com o código da turma");
        writer.println(codigo);
        
        String cpf = JOptionPane.showInputDialog("Entre com o CPF da pessoa");
        writer.println(cpf);

        String response = server.readLine();
        JOptionPane.showMessageDialog(null, response);
    }
}
