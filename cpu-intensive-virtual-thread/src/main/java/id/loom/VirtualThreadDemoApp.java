
package id.loom;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VirtualThreadDemoApp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        log.info("{} | " + "start running", Runtime.getRuntime().availableProcessors());

        log.info("testing 5_000 threads");
        try (ExecutorService es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5_000; i++) {
                es.submit(VirtualThreadDemoApp::cpuIntensive);
            }
        }

        log.info("{} ms | end running", System.currentTimeMillis() - start);
    }

    private static void cpuIntensive() {
        log.info("{} | " + "starting cpuIntensive", Thread.currentThread());
        var start = LocalDateTime.now();
        var count = 0;
        while (Duration.between(start, LocalDateTime.now()).toSeconds() <= 5L) {
            count = +count;
        }
        log.info("{} | " + "end cpuIntensive", Thread.currentThread());
    }
}
