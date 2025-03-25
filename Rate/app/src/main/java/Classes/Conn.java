package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conn {
    private static final String SERVER_IP = "10.0.2.2";
    private static final int SERVER_PORT = 65432;

    private static String command="";
    public static String getStr(String command)
    {
        String output="";
        String host = "localhost";
        int port = 12345;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to the server.");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            output=output.toString();
            out.println(out);
            socket.close();
            System.out.println("Message sent and connection closed.");

        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
