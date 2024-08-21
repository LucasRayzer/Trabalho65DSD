/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Classes.Palestrante;
import Classes.Turma;
import Classes.Pessoa;
import Classes.Participante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
        String cpf = reader.readLine();
        String nome = reader.readLine();
        String endereco = reader.readLine();

        if (escolha.equals("1")) {
            String valorContribuicao = reader.readLine();
            Participante participante = new Participante(valorContribuicao, cpf, nome, endereco);
            pessoas.add(participante);
            writer.println("Participante inserido com sucesso!");
        } else {
            String titulo = reader.readLine();

            Palestrante palestrante = new Palestrante(titulo ,cpf, nome, endereco);
            pessoas.add(palestrante);

            writer.println("Palestrante inserido com sucesso!");
        }
    }

    private static void atualizarPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            String nome = reader.readLine();
            String endereco = reader.readLine();
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);

            writer.println("Pessoa atualizada com sucesso!");
        } else {
            writer.println("Pessoa não encontrada.");

        }
    }

    private static void obterPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();

        if (pessoas.isEmpty()) {
            writer.println("Sem pessoas cadastradas");
            return;
        }

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            writer.println(pessoa.getCpf() + " - " + pessoa.getNome() + " - " + pessoa.getEndereco());
        } else {
            writer.println("Pessoa não encontrada");
        }
    }

    private static void removerPessoa(BufferedReader reader, PrintWriter writer) throws IOException {
        String cpf = reader.readLine();

        if (pessoas.isEmpty()) {
            writer.println("Sem pessoas cadastradas");
            return;
        }

        Pessoa pessoa = encontrarPessoaPeloCpf(cpf);
        if (pessoa != null) {
            pessoas.remove(pessoa);
            writer.println("Pessoa removida com sucesso");
        } else {
            writer.println("Pessoa não encontrada");
        }
    }

    private static void listarPessoas(BufferedReader reader, PrintWriter writer) throws IOException {
        if (pessoas.isEmpty()) {
            writer.println("0");
            return;
        }

        writer.println(pessoas.size());

        for (Pessoa pessoa : pessoas) {
            writer.println(pessoa.getCpf() + " - " + pessoa.getNome() + " - " + pessoa.getEndereco());
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
        }else if (pessoa == null){
            writer.println("Pessoa não encontrada!");
        }else if (pessoa instanceof Palestrante && turmaTemPalestrante(turma)){
            writer.println("A turma já possui um palestrante cadastrado!");
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
    
    public static boolean turmaTemPalestrante(Turma turma){
        
        for (Pessoa pessoa : turma.getPessoas()){
            if (pessoa instanceof Palestrante ){ 
                return true;
            }
        }
        
        return false;
    }
}
