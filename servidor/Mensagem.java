package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Mensagem {

    private Socket socket;
    private ArrayList<PrintStream> clientes;

    public Mensagem (Socket socket, ArrayList<PrintStream> clientes) {
        this.socket = socket;
        this.clientes = clientes;

        Thread();
    }

    //Thread que vai enviar as mensagens
    private void Thread() {

        Thread thread = new Thread ( new Runnable() {
            
            @Override
            public void run() {
                
                String mensagem = "";

                try {
                    
                    InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while ((mensagem = br.readLine()) != null) {

                        enviarMensagem(mensagem);
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        });
        //para iniciar a thread
        thread.start();
    }

    //Metodo para enviar a mensagem a todos os Clientes conectados
    private void enviarMensagem(String mensagem) {
        //Este for anda de acordo com o tamanho do array da mensagem
        for ( int a = 0; a < clientes.size(); a++ ) {
            //Enquanto o cliente estiver enviado ele pega a mensagem
            clientes.get(a).println(mensagem);
            clientes.get(a).flush();
        }
    }

}
