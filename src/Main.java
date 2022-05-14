import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        new Main();
    }
    public Main(){
        Thread cuadrados = new Thread(() -> {
             String res = IntStream.rangeClosed(1, 100)
                    .map(n -> n * n)
                    .mapToObj(Objects::toString)
                    .collect(Collectors.joining(", "));
            System.out.println("Cuadrados: " + res);
        }, "Cuadrados");

        Thread pares = new Thread(()->{
            String res = IntStream.rangeClosed(1, 100)
                    .filter(n -> n%2==0)
                    .mapToObj(Objects::toString)
                    .collect(Collectors.joining(", "));
            System.out.println("Pares: "+res);
        }, "Pares");

        Thread primos = new Thread(()->{
            String res = IntStream.rangeClosed(1, 100)
                    .filter(Main::isPrime)
                    .mapToObj(Objects::toString)
                    .collect(Collectors.joining(", "));
            System.out.println("Primos: "+res);
        }, "Primos");

        cuadrados.start();
        pares.start();
        primos.start();
    }

    public static boolean isPrime(int n){
        if (n == 0 || n == 1 || n == 4){
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n%i==0) return false;
        }
        return true;
    }
}
