/*
  Copyright (c) 2018 Denis O <denis.o@linux.com>

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
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

    private ExecutorService executorService;

    private Collection<Future<?>> submittedJobs = new ConcurrentLinkedQueue<>();

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
