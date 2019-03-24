package com.unidev.platform.model;

import lombok.Builder;
import lombok.Data;

/**
 * Item to store statistics
 */
@Data
@Builder
public class StatItem {

    private long total;
    private long count;
    private long lastValue;

    public void add(long value) {
        lastValue = value;
        total += value;
        count++;
    }

    public float average() {
        if (count == 0) {
            return 0;
        }
        return total / (float) count;
    }

}
