import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Server
{
    public static void main(String[] args) throws IOException {
        System.out.println("Creating server socket on port " + Client.PORT);
        ServerSocket serverSocket = new ServerSocket(Client.PORT);

        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();

            TimeZone tz = TimeZone.getTimeZone("UTC");
            tz.setRawOffset(Integer.parseInt(str) * 3600000);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            String nowAsISO = df.format(new Date());
            System.out.print(nowAsISO);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println(nowAsISO);
            pw.close();
            socket.close();
        }
    }
}
