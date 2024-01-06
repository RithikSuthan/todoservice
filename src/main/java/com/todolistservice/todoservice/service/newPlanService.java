package com.todolistservice.todoservice.service;

import com.todolistservice.todoservice.model.newPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class newPlanService {
    @Autowired
    MongoTemplate mongoTemplate;
    public ResponseEntity<?>addPlan(newPlan plan)
    {
        if (plan.getPlan()!=null)
        {
            plan.setStatus(false);
            mongoTemplate.save(plan);
        }
        else
        {
            return ResponseEntity.ok("Plan is empty");
        }
        return ResponseEntity.ok("Data added Successfully");
    }
}
