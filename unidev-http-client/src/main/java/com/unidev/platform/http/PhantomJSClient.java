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
package com.unidev.platform.http;

import com.unidev.platform.Processes;
import com.unidev.platform.model.ProcessToRun;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

/**
 * PhantomJS based client
 */
@NoArgsConstructor
@AllArgsConstructor
public class PhantomJSClient {

    @Getter
    @Setter
    private String phantomBinary = "phantomjs";

    @Getter
    @Setter
    private Processes processes;

    public String runScript(String script) throws Exception {
        File scriptFile = null;
        try {
            scriptFile = File.createTempFile("phantomjs", ".js");
            scriptFile.deleteOnExit();
            FileUtils.write(scriptFile, script, Charset.defaultCharset());
            ProcessToRun processToRun = ProcessToRun.builder().command(phantomBinary + " " + scriptFile.getAbsolutePath()).build();
            return processes.runProcess(processToRun);
        } finally {
            FileUtils.deleteQuietly(scriptFile);
        }
    }
}
