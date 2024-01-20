package com.projects.taxicorporation.server;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    private final Map<String, String> data;

    public TestData(Map<String, String> data) {
        this.data = new HashMap<>(data);
    }

    public Map<String, String> getData() {
        return new HashMap<>(data);
    }
}
