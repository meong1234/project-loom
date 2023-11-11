
package id.loom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
                es.submit(VirtualThreadDemoApp::synchronizedFunction);
            }
        }

        log.info("{} ms | end running", System.currentTimeMillis() - start);
    }

    @SneakyThrows
    private synchronized static void synchronizedFunction() {
        log.info("{} | " + "starting synchronizedFunction", Thread.currentThread());
        Thread.sleep(5_000);
        log.info("{} | " + "end synchronizedFunction", Thread.currentThread());
    }
}
