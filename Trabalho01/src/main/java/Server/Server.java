package Server;

import Classes.Pessoa;
import Classes.Turma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
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
                             //Cases

                            default -> writer.println("Operação inválida!");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
