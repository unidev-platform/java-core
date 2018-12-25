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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.unidev.platform.model.ProcessToRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProcessesTest {

    private Processes processes;

    @BeforeEach
    void init() {
        processes = new Processes();
    }

    @Test
    void testCommandExecution() {
        String output = processes.runProcess(ProcessToRun.builder().command("ls").build());
        assertThat(output).isNotBlank();
    }

    @Test
    void testBashCommandRun() {
        String output = processes.runProcess(ProcessToRun.builder().command("whoami").build());
        assertThat(output).isNotBlank();
    }

}
