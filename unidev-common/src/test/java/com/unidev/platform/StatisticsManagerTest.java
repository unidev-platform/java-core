package com.unidev.platform;

import com.unidev.platform.model.StatItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticsManagerTest {

    private StatisticsManager statisticsManager;

    @BeforeEach
    void init() {
        statisticsManager = new StatisticsManager();
    }

    @Test
    public void statItem() {
        StatItem statItem = StatItem.builder().build();
        assertThat(statItem.getCount()).isEqualTo(0);
        assertThat(statItem.getTotal()).isEqualTo(0);
        assertThat(statItem.getLastValue()).isEqualTo(0);
        assertThat(statItem.average()).isEqualTo(0);

        statItem.add(10);
        statItem.add(20);

        assertThat(statItem.getCount()).isEqualTo(2);
        assertThat(statItem.getTotal()).isEqualTo(30);
        assertThat(statItem.getLastValue()).isEqualTo(20);
        assertThat(statItem.average()).isEqualTo(15);
    }

}
