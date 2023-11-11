
package id.loom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VirtualThreadDemoApp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        log.info("{} | " + "start running", Runtime.getRuntime().availableProcessors());

        log.info("testing 5_000 threads");
        try (ExecutorService es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 20; i++) {
                var x = i;
                es.submit(() -> processOrderRequest(x));
            }
        }

        log.info("{} ms | end running", System.currentTimeMillis() - start);
    }

    private static void processOrderRequest(int orderId) {
        try {
            log.info("{} | " + "create order {}", Thread.currentThread(), orderId);
            var userName = getActiveUserName(orderId);
            var order = createOrder(orderId, userName);
            log.info("{} | " + "order created {}", Thread.currentThread(), order);
        } catch (Exception e) {
            log.error("error {}, [{}]", Thread.currentThread(), orderId, e);
        }
    }

    @SneakyThrows
    private static String getActiveUserName(int orderId) {
        log.info("{} | " + "get user-name from DB [{}]", Thread.currentThread(), orderId);
        Thread.sleep(Duration.ofSeconds(5));
        return String.format("customer-%d", orderId);
    }

    @SneakyThrows
    private static String createOrder(int orderId, String userName) {
        log.info("{} | " + "create order, by calling order-service [{}, {}]", Thread.currentThread(), orderId, userName);
        Thread.sleep(Duration.ofSeconds(5));
        throw new RuntimeException("test exception");
    }
}
