package com.example.demo.service;

import com.example.demo.model.AgentModel;

import java.util.List;

public interface AgentService {
    public List<AgentModel> findAllAgents(); //GET_ALL
    public AgentModel findAgentById(); //SPECIFIC
    public AgentModel saveAgent(AgentModel agentModel); //INSERT
    public AgentModel updateAgent(AgentModel agentModel); //UPDATE
    void deleteAgent(Long id);
}
