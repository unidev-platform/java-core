package com.unidev.platform;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadManagerTest {

    private final AtomicInteger startCount = new AtomicInteger(0);
    private final AtomicInteger stopCount = new AtomicInteger(0);

    @Test
    public void testScheduling() throws InterruptedException {
        FixedThreadsManager fixedThreadsManager = new FixedThreadsManager();
        new Thread(
                () -> fixedThreadsManager.startThreads(10, () -> {
                    startCount.incrementAndGet();
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    stopCount.incrementAndGet();
                })

        ).start();

        while (true) {
            System.out.println(startCount.get() + " " + stopCount.get());
            Thread.sleep(100);
            if (startCount.get() > 20 && stopCount.get() > 20) {
                fixedThreadsManager.getLoop().set(false);
                break;
            }
        }

    }

}
