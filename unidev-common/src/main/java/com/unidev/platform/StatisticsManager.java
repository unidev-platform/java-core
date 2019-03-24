package com.unidev.platform;

import com.unidev.platform.model.StatItem;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatisticsManager {

    @Getter
    private Map<String, StatItem> statistics = new ConcurrentHashMap<>();

    public void add(String key) {
        add(key, 1L);
    }

    public void add(String key, long value) {
        createIfMissing(key).add(value);
    }

    public StatItem fetchStat(String key) {
        return createIfMissing(key);
    }

    private StatItem createIfMissing(String key) {
        statistics.putIfAbsent(key, StatItem.builder().build());
        return statistics.get(key);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Statistics: \n");
        statistics.entrySet().forEach(item -> stringBuilder.append(item.getKey() + " : Total: " + item.getValue().getTotal() + " Average: " + item.getValue().average() + " Count: " + item.getValue().getCount() + "\n"));

        return stringBuilder.toString();
    }

}
