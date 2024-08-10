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

				String choiceStr = reader.readLine();
				if (choiceStr.isBlank()) {
					System.out.println("Opção inválida");
					continue;
				}
				
				int choice = Integer.parseInt(choiceStr);
				
				Socket socket = new Socket("ipServidor", 64000);
				BufferedReader server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				
				System.out.println("Conectado ao servidor.");
				
				switch (choice) {
					case 1 -> inserirDados(server, reader, writer);
					
					default -> System.out.println("Opção inválida");
				}
				
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void inserirDados(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException{           
        System.out.println("");
        System.out.println("Inserir dados no servidor...");
    }
}

