import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    public static final int PORT = 4261;

    public static void main(String[] args) throws IOException
    {
        final String host = "localhost";
        System.out.println("Creating socket to '" + host + "' on port " + PORT);

        while (true) {
            Socket socket = new Socket(host, PORT);
            BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
            String userInput = userInputBR.readLine();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(userInput);
            System.out.println("client sends:" + userInput);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("client receives: " + br.readLine());
            socket.close();
        }
    }
}
