package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("Informe o endereço do servidor");
            String ipServidor = reader.readLine();

            while (true) {
                System.out.println("Escolha a operação:");

                System.out.println("1: Inserir dados de pessoa");
                System.out.println("2: Atualizar dados de pessoa");
                System.out.println("3: Obter dados de pessoa");
                System.out.println("4: Remover pessoa");
                System.out.println("5: Listar todas as pessoas");

                String choiceStr = reader.readLine();
                if (choiceStr.isBlank()) {
                    System.out.println("Opção inválida");
                    continue;
                }

                int choice = Integer.parseInt(choiceStr);

                Socket socket = new Socket(ipServidor, 80);
                BufferedReader server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Conectado ao servidor.");

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
                    default ->
                        System.out.println("Opção inválida");
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
            System.out.println("1: Criar partipante\n2: Criar palestrante");
            escolha = reader.readLine();
            if (escolha.equals("1") || escolha.endsWith("2")) {
                continuar = false;
                writer.println(escolha);
            }else{
                System.out.println("Escolha uma das opções cadastradas.");
            }
        }
        
        System.out.println("Insira o CPF");
        String cpf = reader.readLine();
        writer.println(cpf);

        System.out.println("Insira o nome");
        String nome = reader.readLine();
        writer.println(nome);

        System.out.println("Insira o endereço");
        String endereco = reader.readLine();
        writer.println(endereco);

        if (escolha.equals("1")) {
            System.out.println("Entre com o valor da contribuição");
            String valorContribuição = reader.readLine();
            writer.println(valorContribuição);
        } else {
            System.out.println("Entre com o título da palestra");
            String titulo = reader.readLine();
            writer.println(titulo);
        }

        String response = server.readLine();
        System.out.println(response);
    }

    public static void atualizarPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("UPDATE");

        System.out.println("Insira o CPF da pessoa que deseja atualizar:");
        String cpf = reader.readLine();
        writer.println(cpf);

        System.out.println("Insira o novo nome:");
        String nome = reader.readLine();
        writer.println(nome);

        System.out.println("Insira o novo endereço:");
        String endereco = reader.readLine();
        writer.println(endereco);

        //Aqui é onde vai vir a resposta do servidor
        String response = server.readLine();
        System.out.println(response);
    }

    public static void obterPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("GET");

        System.out.println("Insira o CPF da pessoa que deseja obter os dados:");
        String cpf = reader.readLine();
        writer.println(cpf);

        String response = server.readLine();
        System.out.println(response);
    }

    public static void removerPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.println("DELETE");

        System.out.println("Insira o CPF da pessoa que deseja remover:");
        String cpf = reader.readLine();
        writer.println(cpf);

        String response = server.readLine();
        System.out.println(response);
    }

    public static void listarPessoas(BufferedReader server, PrintWriter writer) throws IOException {
        writer.println("LIST");
        //quantidade de registros
        String response = server.readLine();
        int quantidade = Integer.parseInt(response);

        if (quantidade == 0) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("Quantidade de registros: " + quantidade);
            for (int i = 0; i < quantidade; i++) {
                String pessoa = server.readLine();
                System.out.println(pessoa);
            }
        }
    }
}
