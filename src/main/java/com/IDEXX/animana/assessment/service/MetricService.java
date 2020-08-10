package com.IDEXX.animana.assessment.service;


import com.IDEXX.animana.assessment.model.MetricResponse;

public interface MetricService {
    void incrementNumberOfAllRequests();

    void incrementNumberOfRequestsThatResult200Status();

    void incrementNumberOfRequestsThatResult4XXStatus();

    void incrementNumberOfRequestsThatResult5XXStatus();

    void setRequestTimeInMillis(long millis);

    MetricResponse getMetricResult();
}
