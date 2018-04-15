package Servidor;

import java.net.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Servidor {

    public static void main(String args[]){
        
        ArrayList<PrintStream> clientes = new ArrayList<>();
        
        try{
            //Abre a conexão
            ServerSocket server = new ServerSocket(5000);
            Socket socket;

            //pra ele sempre ficar aberto a novas conexões
            while (true){
                socket = server.accept();
                //Testar isso rodando no servidor para ver como aparece
                //aqui da pra implementar um contador de pessoas conectadas
                //Tentar colocar como mensagem de pessoa entrando no Server
                System.out.println("Conectou!");

                //Guarda o endereço do cliente
                clientes.add(new PrintStream(socket.getOutputStream()));
                
                Mensagem mensagem = new Mensagem(socket, clientes);

            }
        }
        catch(IOException e){
            
            e.printStackTrace();
        }
        
    }
}


