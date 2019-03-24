package com.unidev.platform;

import com.unidev.platform.model.StatItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticsManagerTest {

    private StatisticsManager statisticsManager;

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


    @Test
    public void statisticManager() {
        statisticsManager = new StatisticsManager();

        statisticsManager.add("test1");
        statisticsManager.add("test1");
        statisticsManager.add("test2", 666);

        StatItem test1 = statisticsManager.fetchStat("test1");
        StatItem test2 = statisticsManager.fetchStat("test2");

        assertThat(test1).isNotNull();
        assertThat(test1.getCount()).isEqualTo(2);
        assertThat(test1.getTotal()).isEqualTo(2);

        assertThat(test2).isNotNull();
        assertThat(test2.getLastValue()).isEqualTo(666);
        assertThat(test2.getCount()).isEqualTo(1);
    }

}
