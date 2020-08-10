package com.IDEXX.animana.assessment.controller;

import com.IDEXX.animana.assessment.service.MetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom-metric")
public class MetricServiceController {
    final private MetricService metricService;

    public MetricServiceController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping
    public ResponseEntity<?> getCustomMetric() {
        return ResponseEntity.ok(metricService.getMetricResult());
    }
}
