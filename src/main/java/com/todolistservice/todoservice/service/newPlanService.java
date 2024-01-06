package com.todolistservice.todoservice.service;

import com.todolistservice.todoservice.model.newPlan;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class newPlanService {
    @Autowired
    MongoTemplate mongoTemplate;
    public ResponseEntity<?>addPlan(newPlan plan)
    {
        if (plan.getPlan()!=null || plan.getPlan()!="")
        {
            plan.setStatus(false);
            mongoTemplate.save(plan);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"error\", \"message\":\"Plan message is empty\"}");
        }
           return ResponseEntity.ok().body("{\"status\":\"success\", \"message\":\"Data added Successfully\"}");
    }
}
