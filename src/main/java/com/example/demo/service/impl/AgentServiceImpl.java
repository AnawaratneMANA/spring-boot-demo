package com.example.demo.service.impl;

import com.example.demo.exception.AgentException;
import com.example.demo.model.AgentModel;
import com.example.demo.repository.AgentRepository;
import com.example.demo.response.AgentResponse;
import com.example.demo.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AgentRepository agentRepository;
    @Override
    public AgentResponse findAllAgents() throws AgentException {
        AgentResponse agentResponse = new AgentResponse();
        try{
            agentResponse.setAgents(agentRepository.findAll());
        } catch (Exception e){
            logger.info("SQL Exception while getting all agents");
            throw new AgentException("Error getting agents :" + e.getMessage());
        }
        return agentResponse;
    }

    @Override
    public AgentModel findAgentById() {
        return null;
    }

    @Override
    public AgentModel saveAgent(AgentModel agentModel) {
        try{
            agentRepository.save(agentModel);
        } catch (Exception e){
            logger.info("SQL Exception while inserting agents");
        }
        return agentModel;
    }

    @Override
    public AgentModel updateAgent(AgentModel agentModel) {
        return null;
    }

    @Override
    public void deleteAgent(Long id) {

    }
}
