
package id.loom;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ThreadOverflowDemoApp {
    public static void main(String[] args) {
        log.info("{} | " + "start running", Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100_000_000; i++) {
            new Thread(ThreadOverflowDemoApp::processRequest).start();
        }
        log.info("end running");
    }

    private static void processRequest() {
        try {
            Thread.sleep(Duration.ofSeconds(10L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
