/**
 * Copyright (c) 2017 Denis O <denis.o@linux.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unidev.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Component for fetching random values
 */
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

    /**
     * Get random value from var-arg
     * @return
     */
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
     * Generate sequence of random values from provided dictionary string
     * @return
     */
    public  String randomValue(String dictionary, int minLength, int maxLength, boolean firstCharUpperCase) {
        int count = minLength + random.nextInt(maxLength - minLength);
        String result = "";
        for (int i = 0; i < count; i++) {
            int id = random.nextInt(dictionary.length());
            Character c = dictionary.charAt(id);
            if (i == 0 && firstCharUpperCase) {
                c = Character.toUpperCase(c);
            }
            result += c;
        }
        return result;
    }

    /**
     * Get random sleep value in seconds
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

    public void setRandom(Random random) {
        this.random = random;
    }

    public Randoms(@Autowired Random random) {
        this.random = random;
    }
}
