package com.unidev.platform.storage;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.security.SecureRandom;
import java.util.Random;

public class HierarchicStorage {

    @Setter
    @Getter
    private File root;

    @Getter
    @Setter
    private HierarchicStorageGenerator generator = (root, currentFile, iteration, seed) -> {
        Random random = new Random(seed.hashCode());
        String value = "";
        for(int i = 0;i<iteration;i++) {
            value = random.nextInt(256) + "";
        }
        return value;
    };

    public File genPath(int depth, String seed) {
        File file = root;
        for (int iteration = 0; iteration < depth; iteration++) {
            file = new File(file, generator.nextLevel(root, file, iteration, seed));
        }

        return file;
    }

    public interface HierarchicStorageGenerator {

        /**
         * Generate next level for storage, return string to be used in path.
         */
        String nextLevel(File root, File currentFile, int iteration, String seed);

    }

}
