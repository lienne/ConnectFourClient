import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String [] args) throws IOException {

        Socket s = new Socket("localhost", 8000);

        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        int result = Integer.parseInt(answer);

        for(int i = 0; i <= 50; i++) {
            result++;

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println(result);

            System.out.println("Result on Client side:" + result); // printing 50
        }
    }
}
