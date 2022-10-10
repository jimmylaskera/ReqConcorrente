import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NumberThread extends Thread {
    public NumberThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://numbersapi.p.rapidapi.com/random/trivia?min=10&max=20&fragment=true&json=true"))
            .header("X-RapidAPI-Key", "310c14e473mshea0c5ec5916e0a9p13e8a5jsnf6cab75c55b7")
            .header("X-RapidAPI-Host", "numbersapi.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(this.getName() + ": " + response.body()); 
        } catch (IOException | InterruptedException e) {
            System.out.println(this.getName() + ": " + e.toString());
        }
    }
}
