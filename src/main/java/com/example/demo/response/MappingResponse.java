package com.example.demo.response;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class MappingResponse {
    boolean flag;
    Object object;
}
