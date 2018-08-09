import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Random;

public class MyService {
    public static void main(String [] args) throws IOException {

        ServerSocket myService = new ServerSocket(8000);
        try {
            Socket s = myService.accept();
            try {

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                Random rand = new Random();
                int n =rand.nextInt(50) + 1;

                out.println(n);

                for (int i = 0; i <= 50; i++) {
                    out = new PrintWriter(s.getOutputStream(), true);

                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String answer = input.readLine();
                    int result = Integer.parseInt(answer);

                    result++;

                    out.println(result);
                }
            } catch (Exception e) {
                System.out.println("Exiting.");
            }
            finally {
                s.close();
            }
        }
        finally {
            myService.close();
        }
    }
}