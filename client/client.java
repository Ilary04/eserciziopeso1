package esercizio1.client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client{

String nomeServer ="localhost";
int portaServer = 6789;
Socket miosocket;
BufferedReader tastiera;
String stringaUtente;
String stringaRicevutaDalServer;
DataOutputStream outVersoServer;
BufferedReader inDalServer;

public Socket connetti (){
System.out.println("2 CLIENT partito in esecuzione ...");
try
{
tastiera = new BufferedReader(new InputStreamReader(System.in));
miosocket = new Socket (nomeServer ,portaServer);
outVersoServer = new DataOutputStream (miosocket.getOutputStream());

inDalServer= new BufferedReader (new InputStreamReader (miosocket.getInputStream ()));
}

catch (UnknownHostException e) {

System. err.println("Host sconosciuto"); }

catch (Exception e)

{

System. out.println(e. getMessage ());

System. out.println ("Errore durante la connessione!");

System. exit (1);

return miosocket;

}
return miosocket;
}
public void comunica() {

try{

// leggo una riga

System.out.println("a inseriaci la stringa da trasmettere al server:"+" In");

stringaUtente=tastiera.readLine();

//la spedisco al server

System. out.println("5 ... Invio la stringe al server e attendo ...");

outVersoServer.writeBytes(stringaUtente+'\n');

stringaRicevutaDalServer=inDalServer.readLine();

System.out.println("8 ... risposta dal server "+'\n'+stringaRicevutaDalServer );

System.out.println("9 CLIENT: termina eleborazione e chiude connessione" );

miosocket. close ();

}

catch (Exception e)

{

System.out.println (e. getMessage () ) ;

System.out.println("Errore durante la comunicazione col server!");

System.exit(1);

}
}


public static void main(String args[]) throws UnknownHostException, IOException {

    Socket s= new Socket("localhost",3000);
    PrintWriter stampa = new PrintWriter(s.getOutputStream());

    BufferedReader Strem = new BufferedReader(new InputStreamReader(s.getInputStream())); 
    String stringa1 = Strem.readLine();
    
    BufferedReader altezza =new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Server"+ stringa1);
    String h = altezza.readLine();
    stampa.println(h);
    stampa.flush();

    BufferedReader Strem2 = new BufferedReader(new InputStreamReader(s.getInputStream())); 
    String stringa2 = Strem2.readLine();
    BufferedReader peso = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Server"+ stringa2);
    String p = peso.readLine();

    //qui vengono fatte le stampe.
    stampa.println(p);
    stampa.flush();

    BufferedReader inputStrem1 = new BufferedReader(new InputStreamReader(s.getInputStream())); 
    String string1 = inputStrem1.readLine();
    System.out.println("Server"+ string1);
    BufferedReader Strem3 = new BufferedReader(new InputStreamReader(s.getInputStream())); 
    String string3 = Strem3.readLine();
    System.out.println("Server" + string3);

    s.close();
    
    }
}