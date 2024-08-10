package Server;

import Classes.Pessoa;
import Classes.Participante;
import Classes.Palestrante;
import Classes.Turma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(80)) {
            System.out.println("Servidor Iniciado. Aguardando conexão na porta 80...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String operation;
                    while ((operation = reader.readLine()) != null) {
                        switch (operation) {
                            case "INSERT_PESSOA" -> insertPessoa(reader, writer);
                            case "UPDATE" -> updatePessoa(reader, writer);

                            default -> writer.println("Operação inválida!");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();

       
        Pessoa pessoaCpf = encontrarPessoaPeloCpf(cpf);
        if (pessoaCpf != null) {
            writer.println("Pessoa já cadastrada. Tente um novo CPF.");
        } else {
            String nome = reader.readLine();
            String endereco = reader.readLine();
            String tipoPessoa = reader.readLine();  

            if ("1".equals(tipoPessoa)) {
                
                String inscricao = reader.readLine();
                String contribuir = reader.readLine();
                String valorContribuicao = reader.readLine();
                Boolean contribuira = false;
                   if(contribuir =="S"){
                       contribuira = true; 
                   }else contribuira=false;
                Participante participante = new Participante(contribuira, valorContribuicao,  cpf, nome, endereco);
                pessoas.add(participante);

                writer.println("Participante inserido com sucesso!");
            } else if ("2".equals(tipoPessoa)) {
                
                String titulo = reader.readLine();
                String descricao = reader.readLine();

                Palestrante palestrante = new Palestrante(cpf, nome, endereco, titulo, descricao);
                pessoas.add(palestrante);

                writer.println("Palestrante inserido com sucesso!");
            } else {
                writer.println("Opção inválida.");
            }
        }
    }
    private static void updatePessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            String nome = reader.readLine();
            String endereco = reader.readLine();

            //Atualização
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);

            writer.println("Pessoa atualizada com sucesso!");
        } else {
            writer.println("Pessoa não encontrada.");
        }
    }


    private static Pessoa encontrarPessoaPeloCpf(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }
}
