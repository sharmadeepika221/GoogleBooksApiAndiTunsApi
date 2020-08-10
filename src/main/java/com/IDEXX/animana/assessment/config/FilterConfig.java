package com.IDEXX.animana.assessment.config;

import com.IDEXX.animana.assessment.filter.MetricFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class FilterConfig  extends WebSecurityConfigurerAdapter {

    private MetricFilter metricFilter;

    public FilterConfig(MetricFilter metricFilter) {
        this.metricFilter = metricFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().addFilterBefore(metricFilter, UsernamePasswordAuthenticationFilter.class);


    }
}

