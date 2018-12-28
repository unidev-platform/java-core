package com.unidev.platform;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ExecutorServiceManagerTest {

    private Integer counter = 0;

    @Test
    public void testWaitingForTermination() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorServiceManager executorManager = new ExecutorServiceManager(executorService);

        Future<?> result = executorManager.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        });
        assertThat(result).isNotNull();
        assertThat(executorManager.allJobsCompleted()).isFalse();
        Thread.sleep(8000);
        assertThat(counter).isEqualTo(1);
        assertThat(executorManager.allJobsCompleted()).isTrue();
    }

}
