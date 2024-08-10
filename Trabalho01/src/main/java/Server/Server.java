package Server;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;

//Buffered Reader e printwriter

public class Server {

    public static void main(String args[]) throws IOException{
        
        ServerSocket server = new ServerSocket(80);
        server.setReuseAddress(true);
        
        while(true){
            System.out.println("Aguardando conexão!");
            try (Socket conn = server.accept();){
                System.out.println("Conectado com: " + conn.getInetAddress().getHostAddress());
                
                OutputStream out = conn.getOutputStream();
                String msg = "Olá, mundo!";
                out.write(msg.getBytes());
            }
        }
    }
}
