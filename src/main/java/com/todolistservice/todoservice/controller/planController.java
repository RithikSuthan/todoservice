package com.todolistservice.todoservice.controller;


import com.todolistservice.todoservice.model.newPlan;
import com.todolistservice.todoservice.service.newPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping("/getPlan")
        public ResponseEntity<?> getPlan()
        {
            return planService.getPlan();
        }
        @PatchMapping("/updateStatus")
        public ResponseEntity<?> updateStatus(@RequestParam(required = false) String taskNo)
        {
            return planService.updateStatus(taskNo);
        }

}
