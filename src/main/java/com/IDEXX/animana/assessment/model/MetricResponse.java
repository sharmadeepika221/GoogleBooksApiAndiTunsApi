package com.IDEXX.animana.assessment.model;


import java.util.HashMap;
import java.util.Map;


public class MetricResponse {

    final private Map<String, Long> counter = new HashMap<>();
    final private Map<String, Long> timer = new HashMap<>();

    public MetricResponse() {
    }

    public Map<String, Long> getCounter() {
        return counter;
    }

    public Map<String, Long> getTimer() {
        return timer;
    }
}
