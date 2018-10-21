package com.unidev.platform.model;

import lombok.Builder;
import lombok.Data;

/**
 * Process to run details
 *
 * Note:
 * timeout = -1, infinite timeout
 */
@Data
@Builder
public class ProcessToRun {

    private String location;

    private String command;

    private String output;

    @Builder.Default private int timeout = 5 * 60 * 1000;

}
