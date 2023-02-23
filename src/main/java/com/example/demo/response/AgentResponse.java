package com.example.demo.response;

import com.example.demo.model.AgentModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@JsonIgnoreProperties( ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@Data
public class AgentResponse {
    private List<AgentModel> agents;
}
