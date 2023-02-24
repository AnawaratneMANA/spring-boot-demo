package com.example.demo.service.impl;

import com.example.demo.exception.AgentException;
import com.example.demo.model.AgentModel;
import com.example.demo.repository.AgentRepository;
import com.example.demo.response.AgentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {AgentServiceImpl.class})
public class AgentServiceImplTest {
    @MockBean
    AgentRepository agentRepository;

    @InjectMocks
    private AgentServiceImpl agentService;

    @BeforeAll
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAgentsTest() throws AgentException {
        when(agentRepository.findAll()).thenReturn(new ArrayList<AgentModel>());
        AgentResponse agentResponse = agentService.findAllAgents();
        AgentResponse a = new AgentResponse();
        a.setAgents(new ArrayList<>());
        Assertions.assertEquals(agentResponse, a);
    }
}
