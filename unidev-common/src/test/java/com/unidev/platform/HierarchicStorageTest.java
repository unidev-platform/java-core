package com.unidev.platform;

import com.unidev.platform.storage.HierarchicStorage;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HierarchicStorageTest {

    @Test
    public void testHierarchy() {
        HierarchicStorage hierarchicStorage = new HierarchicStorage();
        hierarchicStorage.setRoot(new File("/tmp"));

        File qwePath = hierarchicStorage.genPath(3, "qwe");
        File qwePath2 = hierarchicStorage.genPath(3, "qwe");

        assertEquals(qwePath.getAbsolutePath(), qwePath2.getAbsolutePath());
    }

}
