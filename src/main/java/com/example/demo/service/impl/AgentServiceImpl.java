package com.example.demo.service.impl;

import com.example.demo.exception.AgentException;
import com.example.demo.model.AgentModel;
import com.example.demo.provider.KeyclockAuthProvider;
import com.example.demo.provider.request.KeyCloakAgentRequest;
import com.example.demo.repository.AgentRepository;
import com.example.demo.response.AgentResponse;
import com.example.demo.service.AgentService;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.Constants;
import org.aspectj.weaver.loadtime.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.Constants.HttpCodesMessages.*;

@Service
public class AgentServiceImpl implements AgentService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    KeyclockAuthProvider keyclockAuthProvider;
    @Override
    public AgentResponse findAllAgents() throws AgentException {
        AgentResponse agentResponse = new AgentResponse();
        try{
            agentResponse.setAgents(agentRepository.findAll());
            agentResponse.setStatusCode(HTTP_200_CODE);
            agentResponse.setMessage(HTTP_200_MESSAGE);
        } catch (Exception e){
            logger.error("SQL Exception while getting all agents");
            agentResponse.setStatusCode(HTTP_500_CODE);
            agentResponse.setMessage(HTTP_500_MESSAGE);
            throw new AgentException("Error getting agents :" + e.getMessage());
        }
        return agentResponse;
    }

    @Override
    public AgentModel findAgentById(Long id) {
        Optional<AgentModel> optional =  agentRepository.findById(id);
        return optional.get();
    }

    @Override
    @Transactional
    public AgentModel saveAgent(AgentModel agentModel) {
        try{
            agentRepository.save(agentModel);
            keyclockAuthProvider.addKeyCloakAgents(AgentConversion(agentModel));
        } catch (Exception e){
            logger.error("SQL Exception while inserting agents");
        }
        return agentModel;
    }

    private KeyCloakAgentRequest AgentConversion(AgentModel agentModel){
        KeyCloakAgentRequest keyCloakAgentRequest = new KeyCloakAgentRequest();
        keyCloakAgentRequest.setFirstName(agentModel.getName());
        keyCloakAgentRequest.setLastName("defaultName");
        keyCloakAgentRequest.setEmail("default@email.com");
        return keyCloakAgentRequest;
    }

    @Override
    public AgentModel updateAgent(AgentModel agentModel, Long id) {
        try {
            AgentModel agentModelExisting = agentRepository.findById(id).get();
            agentModelExisting.setName(agentModel.getName());
            agentModelExisting.setGender(agentModel.getGender());
            agentRepository.save(agentModelExisting);
        } catch (Exception e){
            logger.error("SQL Exception while updating agents");
        }
        return agentModel;
    }

    @Override
    public void deleteAgent(Long id) {
        try{
            agentRepository.deleteById(id);
        } catch(Exception e){
            logger.error("SQL Error while deleting");
        }
    }

    //TODO: Add custom @Queries with Parameters.
}
