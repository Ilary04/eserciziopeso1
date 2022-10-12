import java.io.*;
import java.net.*;
import java.rmi.server.ExportException;

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

    public void comunica() throws IOException{
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


public static void main (String args[]) throws IOException{

    int portaServerascolto = 3000;
    
        ServerSocket ss = new ServerSocket(portaServerascolto);
        System.out.println("Server in ascolto sulla porta " + portaServerascolto);
        Socket s1 = ss.accept();
        System.out.println("Connesso");

        PrintWriter stampa = new PrintWriter(s1.getOutputStream());
        stampa.println("Inserisci l'altezza in metri: ");
        stampa.flush();

        InputStreamReader altezzaP = new InputStreamReader(s1.getInputStream());
        BufferedReader br = new BufferedReader(altezzaP);
        String g = br.readLine();

        PrintWriter pr1 = new PrintWriter(s1.getOutputStream());
        pr1.println("Inserisci il peso in kg: ");
        pr1.flush();

        InputStreamReader peso = new InputStreamReader(s1.getInputStream());
        BufferedReader buffer1 = new BufferedReader(peso);
        String w = buffer1.readLine();

        float h = Float.parseFloat(g);
        float W = Float.parseFloat(w);
        float mol = h*h;
        float bmi = W / mol;

        PrintWriter stampa2 = new PrintWriter(s1.getOutputStream());
        stampa2.println("il bmi e': " + bmi);
        stampa2.flush();

        PrintWriter stampa3 = new PrintWriter(s1.getOutputStream());
        String risultato = "";

        if(bmi < 18.5){
            risultato = "sottopeso";
        }else if(bmi > 24.9){
            risultato = "sorappeso";
        }else{
            risultato = "peso_normale";
        }

        stampa3.println("Il paziente risulta essere " + risultato );
        stampa3.flush();
        
        s1.close();
        ss.close();

}
}


