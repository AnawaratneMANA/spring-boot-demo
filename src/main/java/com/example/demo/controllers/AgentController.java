package com.example.demo.controllers;
import com.example.demo.exception.AgentException;
import com.example.demo.model.AgentModel;
import com.example.demo.response.AgentResponse;
import io.swagger.annotations.ApiOperation;
import com.example.demo.service.AgentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.Constants.APPLICATION_JSON_VALUE;
import static com.example.demo.config.Constants.AgentProperties.AGENT;
import static com.example.demo.config.Constants.HttpCodesMessages.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping( value = AGENT, produces = APPLICATION_JSON_VALUE)
public class AgentController {
    @Autowired
    AgentService agentService;

    @ApiOperation(value = "Get All Agents")
    @ApiResponses( value = {
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE),
    })
    @GetMapping("/")
    public ResponseEntity<AgentResponse> getAllAgents() throws AgentException {
         return status(HttpStatus.OK).body(agentService.findAllAgents());
    }

    @ApiOperation(value = "Save Agent")
    @ApiResponses( value = {
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE),
    })
    @PostMapping("/agent")
    public ResponseEntity<AgentModel> saveAgent(@RequestBody AgentModel agentModel){
        return status(HttpStatus.OK).body(agentService.saveAgent(agentModel));
    }

}
