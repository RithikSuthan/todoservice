package com.todolistservice.todoservice.controller;


import com.todolistservice.todoservice.model.newPlan;
import com.todolistservice.todoservice.model.newUser;
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
        @DeleteMapping("/deleteTask")
        public ResponseEntity<?> deleteTask(@RequestParam(required = false) String taskNo)
        {
            return planService.deleteTask(taskNo);
        }

        @PatchMapping("/editTask")
        public ResponseEntity<?> editTask(@RequestParam(required = false) String taskNo,@RequestParam(required = false) String newPlan)
        {
            return planService.editTask(taskNo,newPlan);
        }
        @PutMapping("/registerUser")
        public ResponseEntity<?> addUser(@RequestBody(required = false) newUser user)
        {
            return planService.addUser(user);
        }

}
