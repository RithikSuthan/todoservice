package com.todolistservice.todoservice.service;

import com.todolistservice.todoservice.model.newPlan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class newPlanService {
    public ResponseEntity<?>addPlan(newPlan plan)
    {
        return ResponseEntity.ok("Data added Successfully");
    }
}
