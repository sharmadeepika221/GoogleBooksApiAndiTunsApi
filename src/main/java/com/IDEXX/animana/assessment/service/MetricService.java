package com.cognizant.garage.business.service;

import com.cognizant.garage.business.domain.response.MetricResponse;

public interface MetricService {
    void incrementNumberOfAllRequests();

    void incrementNumberOfRequestsThatResult200Status();

    void incrementNumberOfRequestsThatResult4XXStatus();

    void incrementNumberOfRequestsThatResult5XXStatus();

    void setRequestTimeInMillis(long millis);

    MetricResponse getMetricResult();
}
