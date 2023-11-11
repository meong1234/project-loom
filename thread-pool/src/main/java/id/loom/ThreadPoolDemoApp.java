
package id.loom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolDemoApp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        log.info("{} | " + "start running", Runtime.getRuntime().availableProcessors());

        log.info("testing 5_000 threads");
        try (ExecutorService es = Executors.newFixedThreadPool(200)) {
            for (int i = 0; i < 5_000; i++) {
                var x = i;
                es.submit(() -> processOrderRequest(x));
            }
        }

        log.info("{} ms | end running", System.currentTimeMillis() - start);
    }

    private static void processOrderRequest(int orderId) {
        log.info("{} | " + "create order {}", Thread.currentThread(), orderId);
        var userName = getActiveUserName(orderId);
        var order = createOrder(orderId, userName);
        log.info("{} | " + "order created {}", Thread.currentThread(), order);
    }

    @SneakyThrows
    private static String getActiveUserName(int orderId) {
        log.info("{} | " + "get user-name from DB", Thread.currentThread());
        Thread.sleep(Duration.ofSeconds(1L));
        return String.format("customer-%d", orderId);
    }

    @SneakyThrows
    private static String createOrder(int orderId, String userName) {
        log.info("{} | " + "create order, by calling order-service [{}, {}]", Thread.currentThread(), orderId, userName);
        Thread.sleep(Duration.ofSeconds(1L));
        return String.format("order-%d-%s", orderId, userName);
    }

    private static CompletableFuture<Void> processOrderRequestAsync(int orderId) {
        log.info("{} | " + "create order {}", Thread.currentThread(), orderId);
        return CompletableFuture.supplyAsync(() -> getActiveUserName(orderId))
                .thenApply((userName) -> createOrder(orderId, userName))
                .thenAccept((order) -> {
                    log.info("{} | " + "order created {}", Thread.currentThread(), order);
                });
    }

}
