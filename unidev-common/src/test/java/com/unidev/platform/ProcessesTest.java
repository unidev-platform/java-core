package com.unidev.platform;

import com.unidev.platform.model.ProcessToRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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
