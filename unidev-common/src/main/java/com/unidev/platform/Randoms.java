package com.unidev.platform;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * Component for fetching random values
 */
@Slf4j
public class Randoms {

    private Random random = null;

    public Randoms(Random random) {
        this.random = random;
    }

    public Randoms() {
        this.random = new Random();
    }

    /**
     * Fetch random value from list
     * @return Random list value or null if empty or null
     */
    public  <T> T randomValue(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            log.warn("Can't get random value from list {}", list);
            return null;
        }
        int size = list.size();
        int id = random.nextInt(size);
        return list.get(id);
    }

    /**
     * Fetch random value from collection
     * @return Random collection value or null if empty or null
     */
    public <T> T randomValue(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            log.warn("Can't get random value from collection {}", collection);
            return null;
        }
        int size = collection.size();
        int id = random.nextInt(size);
        Iterator<T> iterator = collection.iterator();
        T value = null;
        for(int i = 0;i<=id;i++) {
            value = iterator.next();
        }
        return value;
    }

    /**
     * Get random value from var-arg
     * @return
     */
    public  <T> T randomValue(T... array) {
        if (array == null || array.length == 0) {
            log.warn("Can't get random value from array {}", array);
            return null;
        }
        int size = array.length;
        int id = random.nextInt(size);
        return array[id];
    }

    /**
     * Generate sequence of random values from provided dictionary string
     * @return
     */
    public  String randomValue(String dictionary, int minLength, int maxLength, boolean firstCharUpperCase) {
        int count = minLength + random.nextInt(maxLength - minLength);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int id = random.nextInt(dictionary.length());
            Character c = dictionary.charAt(id);
            if (i == 0 && firstCharUpperCase) {
                c = Character.toUpperCase(c);
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * Get random sleep value in seconds
     * @return Selected sleep value
     */
    public  long randomSleepValue(long min, long max) {
        if (min == max) {
            return min;
        }
        long sleep = 1000 + (1000 * (min + random.nextInt((int) (max -  min))));
        log.debug("Selected sleep value {}", sleep);
        return sleep;
    }

    /**
     * Get random value between min and max
     * @return
     */
    public  long randomValue(long min, long max) {
        if (min == max) {
            return min;
        }
        return min + random.nextInt((int)(max - min));
    }

    /**
     * Get random values from collection
     * @return
     */
    public <T> List<T> randomValues(List<T> list, int count) {
        List<T> result = new ArrayList<>();
        for(int i = 0;i<count;i++) {
            int id = random.nextInt(list.size());
            T item = list.get(id);
            result.add(item);
        }
        return result;
    }


    public Random getRandom() {
        return random;
    }

}
