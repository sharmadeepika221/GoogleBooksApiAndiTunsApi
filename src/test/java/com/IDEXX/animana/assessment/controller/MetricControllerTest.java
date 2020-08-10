package com.IDEXX.animana.assessment.controller;

import com.IDEXX.animana.assessment.service.MetricService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MetricControllerTest {
    @Autowired
    private MetricService metricService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void getCustomMetric_shouldReturnStatusOk() throws Exception{
        MockHttpServletResponse response = mvc.perform(
                get("/custom-metric")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}