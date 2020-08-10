package com.cognizant.garage.web.filter;

import com.cognizant.garage.business.service.MetricService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MetricFilter extends OncePerRequestFilter {
    final private MetricService metricService;

    public MetricFilter(MetricService metricService) {
        this.metricService = metricService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        filterChain.doFilter(request, response);

        metricService.incrementNumberOfAllRequests();
        int status = response.getStatus();
        if (status == 200) {
            metricService.incrementNumberOfRequestsThatResult200Status();
        }
        else if(status >= 400 && status < 500) {
            metricService.incrementNumberOfRequestsThatResult4XXStatus();
        }
        else if(status >= 500) {
            metricService.incrementNumberOfRequestsThatResult5XXStatus();
        }
        long endTime = System.currentTimeMillis();
        metricService.setRequestTimeInMillis(endTime - startTime);
    }
}
