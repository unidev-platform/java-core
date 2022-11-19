package com.unidev.platform;

import com.unidev.platform.common.exception.UnidevRuntimeException;
import com.unidev.platform.model.ProcessToRun;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Native processes operations
 */
@Slf4j
public class Processes {

    /**
     * Run process and collect output
     */
    public String runProcess(ProcessToRun processToRun) {
        CommandLine commandLine = CommandLine.parse(processToRun.getCommand());

        DefaultExecutor executor = new DefaultExecutor();
        if (Strings.isNotBlank(processToRun.getLocation())) {
            executor.setWorkingDirectory(new File(processToRun.getLocation()));
        }
        ExecuteWatchdog watchdog = new ExecuteWatchdog(processToRun.getTimeout());
        executor.setWatchdog(watchdog);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        log.info("Running {}", processToRun);
        try {
            executor.execute(commandLine);
        } catch (IOException e) {
            log.warn("Execution of {} failed {}", processToRun, outputStream, e);
            throw new UnidevRuntimeException(e);
        }

        return outputStream.toString();
    }

    public String runBash(String command) {
        return runProcess(ProcessToRun.builder().command("/bin/bash -c \"" + command + "\"").build());
    }

     /**
      * Call command and get result as string
      * @param app
      * @return
      */
     @Deprecated
     public  String executeAndGet(String... app) {
         return executeAndGet(null, app);
     }
     /**
      * Call command in location and fetch results
      * @param location
      * @param app
      * @return
      */
     @Deprecated
     public  String executeAndGet(String location, String... app) {
         String command = "";
         for(String item : app) {
             command += item + " ";
         }
         return runProcess(ProcessToRun.builder().location(location).command(command).build());
     }
}
