package com.cognizant.garage.business.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class MetricResponse {

    final private Map<String, Long> counter = new HashMap<>();
    final private Map<String, Long> timer = new HashMap<>();

}
