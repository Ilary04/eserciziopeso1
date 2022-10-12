import java.io.*;
import java.net.*;
import java.rmi.server.ExportException;
import java.util.*;

public class server{
    
    ServerSocket server=null; //tutte le comunicazioni devono arrivare da questo socket
    Socket client=null;//il client si collega su questo socket denominato client 
    String stringaricevuta=null;//
    String stringaModificata=null;//
    BufferedReader  inDaClient;//serve per  ricevere string dal client
    DataOutputStream outVersoclient;//serve per iniviare messaggi verso il client.

    public Socket attendi(){
        try{
           System.out.println("1 server partito in esecuzioe");
           //creo un server sulla porta 6789
            server= new ServerSocket(6789);
            //rimane in attesa di un client
            client=server.accept();
            //chiudo il server per inibire gli altri client
            server.close();
            //associo 2 oggetti al socket del client per effettuare la scittura e la lettura
            inDaClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoclient=new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println((e.getMessage()));
            System.out.println("errore durante istanza");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            System.out.println("benvenuto client scrivi frase e trasforma in maiuscolo");
            stringaricevuta=inDaClient.readLine();
            System.out.println("ricevuta  stringa :"+stringaricevuta);
            
            stringaModificata=stringaricevuta.toUpperCase();
            System.out.println("invio stringa client ");
            outVersoclient.writeBytes(stringaModificata+'\n');
            System.out.println("fine elaborazione");
            client.close();

        }
        catch(ExportException e){
            System.out.println("errore");
        }
    }
}

public static void main (String args[]){
    server servente=new server();
    servente.attendi();
    servente.comunica();

}


