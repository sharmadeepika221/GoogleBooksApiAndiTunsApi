package com.cognizant.garage.business.service;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MetricServiceImplTest {

    private MetricServiceImpl metricService;

    @Before
    public void setUp() {
        metricService = new MetricServiceImpl();
    }

    @Test
    public void incrementNumberOfAllRequests() {

        Map<String, Long> counter = new HashMap<>();
        counter.put("totalRequests", 1L);
        counter.put("totalRequests200", 0L);
        counter.put("totalRequests4xx", 0L);
        counter.put("totalRequests5xx", 0L);

        metricService.incrementNumberOfAllRequests();

        assertThat(metricService.getMetricResult().getCounter()).isEqualTo(counter);
    }

    @Test
    public void incrementNumberOfRequestsThatResult200Status() {
        Map<String, Long> counter = new HashMap<>();
        counter.put("totalRequests", 1L);
        counter.put("totalRequests200", 1L);
        counter.put("totalRequests4xx", 0L);
        counter.put("totalRequests5xx", 0L);

        metricService.incrementNumberOfAllRequests();
        metricService.incrementNumberOfRequestsThatResult200Status();

        assertThat(metricService.getMetricResult().getCounter()).isEqualTo(counter);
    }

    @Test
    public void incrementNumberOfRequestsThatResult4XXStatus() {
        Map<String, Long> counter = new HashMap<>();
        counter.put("totalRequests", 1L);
        counter.put("totalRequests200", 0L);
        counter.put("totalRequests4xx", 1L);
        counter.put("totalRequests5xx", 0L);

        metricService.incrementNumberOfAllRequests();
        metricService.incrementNumberOfRequestsThatResult4XXStatus();

        assertThat(metricService.getMetricResult().getCounter()).isEqualTo(counter);
    }

    @Test
    public void incrementNumberOfRequestsThatResult5XXStatus() {
        Map<String, Long> counter = new HashMap<>();
        counter.put("totalRequests", 1L);
        counter.put("totalRequests200", 0L);
        counter.put("totalRequests4xx", 0L);
        counter.put("totalRequests5xx", 1L);

        metricService.incrementNumberOfAllRequests();
        metricService.incrementNumberOfRequestsThatResult5XXStatus();

        assertThat(metricService.getMetricResult().getCounter()).isEqualTo(counter);
    }

}