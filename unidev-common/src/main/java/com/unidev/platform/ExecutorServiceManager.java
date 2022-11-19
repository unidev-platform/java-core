package com.unidev.platform;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Wrapper around executor service for handling result jobs
 */
public class ExecutorServiceManager {

    private final ExecutorService executorService;

    private final Collection<Future<?>> submittedJobs = new ConcurrentLinkedQueue<>();

    public ExecutorServiceManager(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<?> submit(Runnable runnable) {
        Future<?> future = executorService.submit(runnable);
        submittedJobs.add(future);
        return future;
    }

    public <T> Future<T> submit(Callable<T> callable) {
        Future<T> future = executorService.submit(callable);
        submittedJobs.add(future);
        return future;
    }

    public <T> Future<T> submit(Runnable task, T result) {
        Future<T> future = executorService.submit(task, result);
        submittedJobs.add(future);
        return future;
    }

    public Collection<Future<?>> getSubmittedJobs() {
        return submittedJobs;
    }

    /**
     * Check if all submitted jobs are completed
     */
    public boolean allJobsCompleted() {
        for(Future<?> future : submittedJobs) {
            if (!future.isDone()) {
                return false;
            }
        }
        return true;
    }

}
