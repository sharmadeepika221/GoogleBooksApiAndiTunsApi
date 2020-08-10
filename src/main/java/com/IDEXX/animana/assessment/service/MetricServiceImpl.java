package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.response.MetricResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class MetricServiceImpl implements MetricService {
    final private static Map<String, Long> COUNTER = new ConcurrentHashMap<>();
    final private static Map<String, Long> TIMER = new ConcurrentHashMap<>();
    final private static String TOTAL_REQUESTS = "totalRequests";
    final private static String TOTAL_REQUESTS_200 = "totalRequests200";
    final private static String TOTAL_REQUESTS_4XX = "totalRequests4xx";
    final private static String TOTAL_REQUESTS_5XX = "totalRequests5xx";

    final private static String MAX_TIME = "max";
    final private static String MIN_TIME = "min";
    final private static String AVG_TIME = "avg";
    final private static String TOTAL_TIME = "totalTime";

    public MetricServiceImpl() {
        log.info("Init the metric");
        COUNTER.put(TOTAL_REQUESTS, 0L);
        COUNTER.put(TOTAL_REQUESTS_200, 0L);
        COUNTER.put(TOTAL_REQUESTS_4XX, 0L);
        COUNTER.put(TOTAL_REQUESTS_5XX, 0L);

        TIMER.put(MAX_TIME, 0L);
        TIMER.put(MIN_TIME, 0L);
        TIMER.put(AVG_TIME, 0L);
        TIMER.put(TOTAL_TIME, 0L);
    }

    @Async
    @Override
    public void incrementNumberOfAllRequests() {
        COUNTER.replace(TOTAL_REQUESTS, COUNTER.get(TOTAL_REQUESTS) + 1);
    }

    @Async
    @Override
    public void incrementNumberOfRequestsThatResult200Status() {
        COUNTER.replace(TOTAL_REQUESTS_200, COUNTER.get(TOTAL_REQUESTS_200) + 1);
    }

    @Async
    @Override
    public void incrementNumberOfRequestsThatResult4XXStatus() {
        COUNTER.replace(TOTAL_REQUESTS_4XX, COUNTER.get(TOTAL_REQUESTS_4XX) + 1);
    }

    @Async
    @Override
    public void incrementNumberOfRequestsThatResult5XXStatus() {
        COUNTER.replace(TOTAL_REQUESTS_5XX, COUNTER.get(TOTAL_REQUESTS_5XX) + 1);
    }

    @Override
    public MetricResponse getMetricResult() {
        log.info("Entered Metric result");
        MetricResponse metricResponse = new MetricResponse();
        metricResponse.getCounter().putAll(COUNTER);
        metricResponse.getTimer().putAll(TIMER);
        return metricResponse;
    }

    @Async
    @Override
    public void setRequestTimeInMillis(long millis) {
        TIMER.replace(TOTAL_TIME, TIMER.get(TOTAL_TIME) + millis);
        TIMER.replace(MAX_TIME, millis > TIMER.get(MAX_TIME) ? millis : TIMER.get(MAX_TIME));
        TIMER.replace(MIN_TIME, TIMER.get(MIN_TIME) < millis && TIMER.get(MIN_TIME) != 0 ? TIMER.get(MIN_TIME) : millis);
        TIMER.replace(AVG_TIME, TIMER.get(TOTAL_TIME) / COUNTER.get(TOTAL_REQUESTS));
    }
}
