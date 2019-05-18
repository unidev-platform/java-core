package com.unidev.platform;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Service for maintaining fixed number of threads.
 */
@Slf4j
public class FixedThreadsManager {

    @Getter
    @Setter
    private long checkInterval = 100 * 10;

    @Getter
    @Setter
    private AtomicBoolean loop = new AtomicBoolean(true);

    @Getter
    @Setter
    private List<Future> status = new ArrayList<>();

    private ExecutorService executorService;

    /**
     * Schedule fixed number of threads.
     */
    public void startThreads(int threadCount, Runnable runnable) {
        executorService = Executors.newFixedThreadPool(threadCount);

        loop.set(true);

        while (loop.get()) {
            submitNewJobs(threadCount, runnable);
            try {
                Thread.sleep(checkInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculate jobs to schedule.
     */
    private void submitNewJobs(int threadCount, Runnable runnable) {
        List<Future> toRemove = new ArrayList<>();
        for (Future future : status) {
            if (future.isCancelled() || future.isDone()) {
                toRemove.add(future);
            }
        }
        if (!toRemove.isEmpty()) {
            log.info("Finished threads {}", toRemove.size());
        }
        status.removeAll(toRemove);

        int toStart = threadCount - status.size();

        if (toStart > 0) {
            log.info("Starting threads {}", toStart);
        }

        for (int thread = 0; thread < toStart; thread++) {
            Future<?> submit = executorService.submit(runnable);
            status.add(submit);
        }
    }

}