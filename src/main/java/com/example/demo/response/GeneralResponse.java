package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralResponse implements Serializable {
    private int statusCode;
    private String messageId;
    private String message;
    private String statusDesc;
}
