import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 1234; // Port to listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Input and output streams for communication
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String text;
                while ((text = reader.readLine()) != null) {
                    System.out.println("Received: " + text);
                    writer.println("Server: " + text); // Echo message back to client

                    if ("bye".equalsIgnoreCase(text)) {
                        System.out.println("Client disconnected");
                        break;
                    }
                }
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}