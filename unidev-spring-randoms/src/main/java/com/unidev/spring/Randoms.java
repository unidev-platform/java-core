package com.unidev.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Component for fetching random values
 * @author denis
 */
@Component
public class Randoms {

    private static Logger LOG = LoggerFactory.getLogger(Randoms.class);
    private Random random = null;

    /**
     * Fetch random value from list
     * @return Random list value or null if empty or null
     */
    public  <T> T randomValue(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            LOG.warn("Can't get random value from list {}", list);
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
            LOG.warn("Can't get random value from collection {}", collection);
            return null;
        }
        int size = collection.size();
        int id = random.nextInt(size);
        Iterator<T> iterator = collection.iterator();
        T value = null;
        for(int i = 0;i<id;i++) {
            value = iterator.next();
        }
        return value;
    }

    public  <T> T randomValue(T... array) {
        if (array == null || array.length == 0) {
            LOG.warn("Can't get random value from array {}", array);
            return null;
        }
        int size = array.length;
        int id = random.nextInt(size);
        return array[id];
    }

    /**
     * Generate random value from dictionary
     * @param dictionary
     * @param minlength
     * @param maxlength
     * @param firstCharUpcase
     * @return
     */
    public  String randomValue(String dictionary, int minlength, int maxlength, boolean firstCharUpcase) {
        int count = minlength + random.nextInt(maxlength - minlength);
        String result = "";
        for (int i = 0; i < count; i++) {
            int id = random.nextInt(dictionary.length());
            Character c = dictionary.charAt(id);
            if (i == 0)
                if (firstCharUpcase)
                    c = Character.toUpperCase(c);
            result += c;
        }
        return result;
    }

    /**
     * Get random sleep value in seconds
     * @param min Min value
     * @param max Max value
     * @return Selected sleep value
     */
    public  int randomSleepValue(int min, int max) {
        if (min == max) {
            return min;
        }
        int sleep = 1000 + (1000 * (min + random.nextInt(max -  min)));
        LOG.debug("Selected sleep value {}", sleep);
        return sleep;
    }

    /**
     * Get random value between min and max
     * @param min
     * @param max
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
     * @param list
     * @param count
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

    @Autowired
    public void setRandom(Random random) {
        this.random = random;
    }
}
