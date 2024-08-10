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
				
				Socket socket = new Socket(ipServidor, 80);
				BufferedReader server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				
				System.out.println("Conectado ao servidor.");
				
				switch (choice) {
					case 1 -> inserirPessoa(server, reader, writer);
					
					default -> System.out.println("Opção inválida");
				}
				
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void inserirPessoa(BufferedReader server, BufferedReader reader, PrintWriter writer) throws IOException{           
        writer.println("INSERT_PESSOA");    
        
        System.out.println("1: Criar partipante\n2: Criar palestrante");
        String escolha = reader.readLine();
        
        System.out.println("Insira o CPF");
        String cpf = reader.readLine();
	writer.println(cpf);
        
        System.out.println("Insira o nome");
        String nome = reader.readLine();
	writer.println(nome);
        
        System.out.println("Insira o endereço");
        String endereco = reader.readLine();
	writer.println(endereco);
        
        if (escolha.equals("1")){
            System.out.println("Entre com a inscrição");
            String inscricao = reader.readLine();
            
            System.out.println("Deseja fazer uma contribuição? S/N");
            String contribuir = reader.readLine();
            if(contribuir.equalsIgnoreCase("S")){
                System.out.println("Entre com o valor da contribuição");
                String valorContribuição = reader.readLine();
            }else{
                contribuir = "N";
                String valorContribuição = "00,00";
            }
        }else{
            System.out.println("Entre com o título da palestra");
            String titulo = reader.readLine();
            
            System.out.println("Entre com a descrição da palestra");
            String descricao = reader.readLine();
        }
        
        String response = server.readLine();
	System.out.println(response);
    }
}

