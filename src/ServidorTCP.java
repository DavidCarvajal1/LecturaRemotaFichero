import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTCP {
    public static void main(String[] args) {
        try {

            //Creamos el socket del servidor
            System.out.println("(servidor):Creacion de socket...");
            ServerSocket socketServidor = new ServerSocket(2000);

            //Espera ya cepta conexiones
            System.out.println("(servidor)Esperando peticion...");
            Socket socketCliente=socketServidor.accept();

            //Flujo de entrada y salida
            System.out.println("(servidor)Abriendo flujos de entrada y salida...");
            InputStream is=socketCliente.getInputStream();
            OutputStream os=socketCliente.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            //Intercambiamos datos con el cliente
            System.out.println("(Servidor)Leyendo datos del cliente");
            String ruta= br.readLine();
            System.out.println("(Servidor)Enviando datos al cliente");
            bw.write(leerFichero(ruta));
            bw.flush();

            //Cerramos los flujos de lectura y escritura
            System.out.println("(Servidor):Cerramos los flujos de lectura y escritura...");
            is.close();
            os.close();
            osw.close();
            isr.close();
            bw.close();
            br.close();

            //Cerramos la conexion
            socketServidor.close();
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("ERROR: Error al crear el socket en el puerto 50000");
            e.printStackTrace();
        }
    }

    /**
     * Leera un fichero, devolviendo lo leido como string
     * @param ruta
     * @return
     */
    private static String leerFichero(String ruta){
        String salida="";
        String linea;
        try(BufferedReader br=new BufferedReader(new FileReader(ruta))){
            linea=br.readLine();
            while (linea!=null){
                salida+=linea+"\n";
                linea=br.readLine();
            }
        }catch (FileNotFoundException ex){
            System.out.println("El fichero no se encontro");
        }catch (IOException ex){
            System.out.println("Error de entrada o salida");
        }
        return  salida;
    }


}
