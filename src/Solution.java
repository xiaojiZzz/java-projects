import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.thenAcceptAsync(result -> System.out.println("Event 1 processed: " + result));
        future.thenAcceptAsync(result -> System.out.println("Event 2 processed: " + result));
        future.thenAcceptAsync(result -> System.out.println("Event 3 processed: " + result));
        Thread.sleep(2000);
        future.complete("Hello, CompletableFuture!");
        Thread.sleep(5000);
    }
}