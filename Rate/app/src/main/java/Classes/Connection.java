package Classes;

import com.example.rate.MainActivity;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection implements Runnable {
    private static final String SERVER_IP = "10.0.2.2";
    private static final int SERVER_PORT = 65432;
    private String command="";
    File dir;
    @Override
    public void run() {
        String response = "";
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String a;

            System.out.println(MainActivity.msj=inputStream.readLine());



            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = "Unknown host exception.";
        } catch (IOException e) {
            e.printStackTrace();
            response = "IOException occurred.";
        }
    }

    public Connection(String command)
    {
        this.command=command;
        this.dir=dir;
    }

}
