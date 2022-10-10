import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class SequencialApp {
    public static void main(String[] args) throws Exception {
        int requisitions = 0;
        
        System.out.printf("How many requisitions? ");
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) requisitions = in.nextInt();
        in.close();

        long start = System.currentTimeMillis();
        for (int i = 1; i <= requisitions; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://numbersapi.p.rapidapi.com/random/trivia?min=10&max=20&fragment=true&json=true"))
                .header("X-RapidAPI-Key", "310c14e473mshea0c5ec5916e0a9p13e8a5jsnf6cab75c55b7")
                .header("X-RapidAPI-Host", "numbersapi.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response;
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(i + ": " + response.body()); 
            } catch (IOException | InterruptedException e) {
                System.out.println(i + ": " + e.toString());
            }
            Thread.sleep(100);
        }
        long time = System.currentTimeMillis() - start - 100*requisitions;
        System.out.println("\nElapsed time: " + time + " ms.");
    }
}
