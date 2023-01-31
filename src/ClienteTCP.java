import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            Scanner sc=new Scanner(System.in);
            //Creacion de socket tipo cliente
            System.out.println("(Cliente):Creacion de socket...");
            Socket socket = new Socket(InetAddress.getLocalHost(), 2000);

            //Abrir los flujos de lectura y escritura
            System.out.println("(Cliente):Abriendo flujos de entrada y salida...");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            //Intercambio de datos con el servidor
            System.out.println("(Cliente)Indique la ruta del archivo a leer");
            bw.write(sc.next());
            bw.newLine();
            bw.flush();
            System.out.println("(Cliente)Enviando datos al servidor");
            String linea=br.readLine();
            String salida="";
            while (linea!=null){
                salida+=linea;
                linea=br.readLine();
            }
            System.out.println("(Servidor)"+salida);


            //Cerramos los flujos de lectura y escritura
            System.out.println("(Cliente):Cerramos los flujos de lectura y escritura...");
            is.close();
            os.close();
            osw.close();
            isr.close();
            bw.close();
            br.close();

            //Cerramos la c onexione
            //socket.close();
        } catch (UnknownHostException e) {
            System.err.println("ERROR: Servidor desconocido");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR: ");
            e.printStackTrace();
        }
    }


}
