package com.unidev.platform;

public class Env {

    public String env(String name, String defaultValue) {
        return System.getenv().getOrDefault(name, defaultValue);
    }

    public String env(String name) {
        String value = System.getenv(name);

        if (value == null) {
            throw new RuntimeException("No Env variable " + name);
        }

        return value;
    }
}
