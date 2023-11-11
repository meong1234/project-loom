
package id.loom;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VirtualThreadDemoApp {
    public static void main(String[] args) {
        log.info("{} | " + "start running", Runtime.getRuntime().availableProcessors());

        startingVirtualThread();

        log.info("end running");
    }

    public static void startingVirtualThread() {
        //start without name
        var vThread = Thread.startVirtualThread(VirtualThreadDemoApp::processRequest);
        log.info("{} | " + "check thread", vThread);

        //start with name
        var namedVThread = Thread.ofVirtual().name("test-process").start(VirtualThreadDemoApp::processRequest);
        log.info("{} | " + "check thread", namedVThread);

        //create with threadFactory
        var vThreadFactory = Thread.ofVirtual().name("test-process").factory();
        var vThreadByFactory = vThreadFactory.newThread(VirtualThreadDemoApp::processRequest);
        log.info("{} | " + "check thread", vThreadByFactory);
        vThreadByFactory.start();

        //create using executor Service
        try (ExecutorService es = Executors.newVirtualThreadPerTaskExecutor()) {
            es.submit(VirtualThreadDemoApp::processRequest);
            es.submit(VirtualThreadDemoApp::processRequest);
        }
    }

    private static void processRequest() {
        try {
            log.info("{} | " + "processRequest", Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
