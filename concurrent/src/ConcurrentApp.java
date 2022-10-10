import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConcurrentApp {
    public static void main(String[] args) throws Exception {
        int requisitions = 0;
        
        System.out.printf("How many requisitions? ");
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) requisitions = in.nextInt();
        in.close();

        List<NumberThread> trivia = new ArrayList<>();
        int number = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < requisitions; i++) {
            number = i+1;
            trivia.add(new NumberThread(String.valueOf(number)));
            trivia.get(i).start();
            Thread.sleep(100);
        }

        Thread.sleep(500);
        for (int i = 0; i < requisitions; i++) {
            if (trivia.get(i).isAlive()) trivia.get(i).interrupt();
        }
        
        long time = System.currentTimeMillis() - start - 100*requisitions - 500;
        System.out.println("\nElapsed time: " + time + " ms.");
    }
}
