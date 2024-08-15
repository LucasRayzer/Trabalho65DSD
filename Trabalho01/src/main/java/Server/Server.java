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

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String operation;
                    while ((operation = reader.readLine()) != null) {
                        System.out.println("Operação recebida: " + operation);

                        switch (operation) {
                            case "INSERT_PESSOA" ->
                                inserirPessoa(reader, writer);
                            case "UPDATE_PESSOA" ->
                                atualizarPessoa(reader, writer);
                            case "GET_PESSOA" ->
                                obterPessoa(reader, writer);
                            case "DELETE_PESSOA" ->
                                removerPessoa(reader, writer);
                            case "LIST_PESSOA" ->
                                listarPessoas(reader, writer);
                            case "INSERT_TURMA" ->
                                adicionarTurma(reader, writer);
                            case "UPDATE_TURMA" ->
                                editarTurma(reader, writer);
                            case "GET_TURMA" ->
                                buscarTurma(reader, writer);
                            case "DELETE_TURMA" ->
                                removerTurma(reader, writer);
                            case "LIST_TURMA" ->
                                buscarTodasTurma(writer);
                            case "INSERT_PESSOA_TURMA" ->
                                adicionarPessoaTurma(reader, writer);
                            case "REMOVE_PESSOA_TURMA" ->
                                removerPessoaTurma(reader, writer);

                            default -> {
                                writer.println("Operação inválida!");
                                System.out.println("Operação inválida recebida");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inserirPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String escolha = reader.readLine();
        System.out.println("Tipo de pessoa selecionado: " + escolha);

        String cpf = reader.readLine();
        System.out.println("CPF cadastrado: " + cpf);

        String nome = reader.readLine();
        System.out.println("Nome cadastrado: " + nome);

        String endereco = reader.readLine();
        System.out.println("Endereço cadastrado: " + endereco);

        if (escolha.equals("1")) {
            String valorContribuicao = reader.readLine();
            System.out.println("Valor da contribuição: " + valorContribuicao);
            Participante participante = new Participante(valorContribuicao, cpf, nome, endereco);
            pessoas.add(participante);
            writer.println("Participante inserido com sucesso!");
            System.out.println("Participante inserido: " + participante);
        } else {
            String titulo = reader.readLine();

            Palestrante palestrante = new Palestrante(cpf, nome, endereco, titulo);
            pessoas.add(palestrante);

            writer.println("Palestrante inserido com sucesso!");
            System.out.println("Palestrante inserido: " + palestrante);
        }
    }

    private static void atualizarPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();
        System.out.println("Tentando atualizar pessoa com CPF: " + cpf);

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            String nome = reader.readLine();
            String endereco = reader.readLine();

            //Atualização
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);

            writer.println("Pessoa atualizada com sucesso!");
            System.out.println("Pessoa atualizada: " + pessoa);
        } else {
            writer.println("Pessoa não encontrada.");
            System.out.println("Pessoa com CPF " + cpf + " não encontrada.");

        }
    }

    private static void obterPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();
        System.out.println("Tentando obter pessoa com CPF: " + cpf);

        if (pessoas.isEmpty()) {
            writer.println("Sem pessoas cadastradas");
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            writer.println(pessoa.getCpf() + ";" + pessoa.getNome() + ";" + pessoa.getEndereco());
            System.out.println("Dados da pessoa enviados: " + pessoa);
        } else {
            writer.println("Pessoa não encontrada");
            System.out.println("Pessoa com CPF " + cpf + " não encontrada.");
        }
    }

    private static void removerPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();
        System.out.println("Tentando remover pessoa com CPF: " + cpf);

        if (pessoas.isEmpty()) {
            writer.println("Sem pessoas cadastradas");
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            pessoas.remove(pessoa);
            writer.println("Pessoa removida com sucesso");
            System.out.println("Pessoa removida: " + pessoa);
        } else {
            writer.println("Pessoa não encontrada");
            System.out.println("Pessoa com CPF " + cpf + " não encontrada.");
        }
    }

    private static void listarPessoas(BufferedReader reader, PrintWriter writer) throws IOException {
        if (pessoas.isEmpty()) {
            writer.println("0");
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        writer.println(pessoas.size());

        for (Pessoa pessoa : pessoas) {
            writer.println(pessoa.getCpf() + ";" + pessoa.getNome() + ";" + pessoa.getEndereco());
            System.out.println("Enviando dados: " + pessoa);
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
    
    private static void adicionarTurma(BufferedReader reader, PrintWriter writer) throws IOException{
        String descricao = reader.readLine();
        Turma turma = new Turma(descricao);
        
        turmas.add(turma);
        
        writer.println("Turma adicionada com sucesso!");
        System.out.println("Turma adicionada: " + turma.toString());
    }
    
    private static void editarTurma(BufferedReader reader, PrintWriter writer) throws IOException{
        String codigo = reader.readLine();
        String descricao = reader.readLine();
        
        Turma turma = buscarTurmaPeloCodigo(Integer.parseInt(codigo));
        if (turma == null){
            writer.println("Turma não encontrada!");
        }else{
            turma.setDescricao(descricao);       
            writer.println("Turma atualizada com sucesso!");
        }
    }
    
    private static void buscarTurma(BufferedReader reader, PrintWriter writer) throws IOException{
        String codigo = reader.readLine();
        
        Turma turma = buscarTurmaPeloCodigo(Integer.parseInt(codigo));
        if (turma == null){
            writer.println("Turma não encontrada!");
        }else{
            writer.println(turma.toString());
        }
    }
    
    private static void removerTurma(BufferedReader reader, PrintWriter writer) throws IOException{
        String codigo = reader.readLine();
        
        Turma turma = buscarTurmaPeloCodigo(Integer.parseInt(codigo));
        if (turma == null){
            writer.println("Turma não encontrada!");
        }else{
            turmas.remove(turma);
            writer.println("Turma removida com sucesso!");
        }
    }
    
    private static void buscarTodasTurma(PrintWriter writer) throws IOException{
        for (Turma turma : turmas){
            writer.println(turma.toString());
        }
        
        if (turmas.isEmpty()){
            writer.println("Nenhuma turma cadastrada!");
        }
    }
    
    private static void adicionarPessoaTurma(BufferedReader reader, PrintWriter writer) throws IOException{
        String codigo = reader.readLine();
        String cpf = reader.readLine();
        
        Turma turma = buscarTurmaPeloCodigo(Integer.parseInt(codigo));
        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        
        if (turma == null){
            writer.println("Turma não encontrada!");
        }
        
        if (pessoa == null){
            writer.println("Pessoa não encontrada!");
        }else{
            turma.addPessoa(pessoa);
            writer.println("Pessoa adicionada a turma com sucesso!");
        }
    }
    
    private static void removerPessoaTurma(BufferedReader reader, PrintWriter writer) throws IOException{
        String codigo = reader.readLine();
        String cpf = reader.readLine();
        
        Turma turma = buscarTurmaPeloCodigo(Integer.parseInt(codigo));
        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        
        if (turma == null){
            writer.println("Turma não encontrada!");
        }
        
        if (pessoa == null){
            writer.println("Pessoa não encontrada!");
        }else{
            turma.removePessoa(pessoa);
            writer.println("Pessoa removida da turma com sucesso!");
        }
    }
    
    private static Turma buscarTurmaPeloCodigo(int codigo) {
        for (Turma turma : turmas) {
            if (turma.getCodigo() == codigo) {
                return turma;
            }
        }
        return null;
    }
}
