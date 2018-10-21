/**
 * Copyright (c) 2017 Denis O <denis.o@linux.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unidev.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Native processes operations
 */

public class Processes {

    private static Logger LOG = LoggerFactory.getLogger(Processes.class);
    // /**
    //  * Call command and get result as string
    //  * @param app
    //  * @return
    //  */
    // public  String executeAndGet(String... app) {
    //     return  executeAndGet(null, app);
    // }
    //
    //
    // /**
    //  * Call command in location and fetch results
    //  * @param location
    //  * @param app
    //  * @return
    //  */
    // public  String executeAndGet(String location, String... app) {
    //     ProcessBuilder processBuilder = new ProcessBuilder(app);
    //     if (location != null) {
    //         processBuilder.directory(new File(location));
    //     }
    //     processBuilder.redirectErrorStream(true);
    //     StringBuilder result = new StringBuilder();
    //     try {
    //         Process process = processBuilder.start();
    //         InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
    //         BufferedReader bufferedReader =
    //                 new BufferedReader(inputStreamReader);
    //         String line;
    //         while((line = bufferedReader.readLine()) != null ) {
    //             LOG.debug(line);
    //             result.append(line+"\n");
    //         }
    //         bufferedReader.close();
    //         inputStreamReader.close();
    //         process.destroy();
    //     }catch(Throwable t) {
    //         LOG.error("Failed to execute {}", app, t);
    //     }
    //     return result.toString();
    // }
}
