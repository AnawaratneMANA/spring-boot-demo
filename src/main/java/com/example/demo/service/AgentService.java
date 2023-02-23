package com.example.demo.service;

import com.example.demo.exception.AgentException;
import com.example.demo.model.AgentModel;
import com.example.demo.response.AgentResponse;

import java.util.List;

public interface AgentService {
    public AgentResponse findAllAgents() throws AgentException; //GET_ALL
    public AgentModel findAgentById(); //SPECIFIC
    public AgentModel saveAgent(AgentModel agentModel); //INSERT
    public AgentModel updateAgent(AgentModel agentModel); //UPDATE
    void deleteAgent(Long id);
}
