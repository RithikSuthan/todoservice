package com.todolistservice.todoservice.controller;


import com.todolistservice.todoservice.model.newPlan;
import com.todolistservice.todoservice.service.newPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class planController {

    @Autowired
    newPlanService planService;
    @PutMapping("/addPlan")
        public ResponseEntity<?> addPlan(@RequestBody(required = false) newPlan plan)
        {
            return planService.addPlan(plan);
        }
}
