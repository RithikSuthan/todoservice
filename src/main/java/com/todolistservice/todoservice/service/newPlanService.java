package com.todolistservice.todoservice.service;

import com.mongodb.client.result.DeleteResult;
import com.todolistservice.todoservice.model.newPlan;
import com.todolistservice.todoservice.model.newUser;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Service
public class newPlanService {
    @Autowired
    MongoTemplate mongoTemplate;
    public ResponseEntity<?>addPlan(newPlan plan)
    {
        if (plan.getPlan()!=null || plan.getPlan()!="")
        {
            plan.setTaskNo(generateTaskNo());
            plan.setStatus(false);
            mongoTemplate.save(plan);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"error\", \"message\":\"Plan message is empty\"}");
        }
           return ResponseEntity.ok().body("{\"status\":\"success\", \"message\":\"Data added Successfully\"}");
    }
    public ResponseEntity<?>getPlan()
    {
        List<newPlan> plans=mongoTemplate.findAll(newPlan.class);
        return ResponseEntity.ok(plans);
    }
    public ResponseEntity<?> updateStatus(String taskNo) {
        Query query = new Query(Criteria.where("taskNo").is(taskNo));

        newPlan existingPlan = mongoTemplate.findOne(query, newPlan.class);

        if (existingPlan != null) {
            boolean currentStatus = existingPlan.isStatus();

            newPlan updatedPlan = mongoTemplate.findAndModify(
                    query,
                    new Update().set("status", !currentStatus),
                    FindAndModifyOptions.options().returnNew(true),
                    newPlan.class
            );

            String message = "Status updated successfully. New status: " + updatedPlan.isStatus();
            return ResponseEntity.ok("{\"status\":\"success\", \"message\":\"" + message + "\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"status\":\"error\", \"message\":\"Plan not found with taskNo: " + taskNo + "\"}");
        }
    }

    public ResponseEntity<?>deleteTask(String taskNo) {
        Query query = new Query(Criteria.where("taskNo").is(taskNo));

        newPlan existingPlan = mongoTemplate.findOne(query, newPlan.class);
        if (existingPlan != null) {
            mongoTemplate.remove(query, newPlan.class);
            String message = "Deleted successfully.";
            return ResponseEntity.ok("{\"status\":\"success\", \"message\":\"" + message + "\"}");
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"status\":\"error\", \"message\":\"Plan not found\"}");
        }
    }
    public ResponseEntity<?> editTask(String taskNo,String newPlan) {
        Query query = new Query(Criteria.where("taskNo").is(taskNo));

        newPlan existingPlan = mongoTemplate.findOne(query, newPlan.class);

        if (existingPlan != null) {
            boolean currentStatus = existingPlan.isStatus();

            newPlan updatedPlan = mongoTemplate.findAndModify(
                    query,
                    new Update().set("plan",newPlan ),
                    FindAndModifyOptions.options().returnNew(true),
                    newPlan.class
            );

            String message = "Plan updated successfully. New status: " + updatedPlan.getPlan();
            return ResponseEntity.ok("{\"status\":\"success\", \"message\":\"" + message + "\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"status\":\"error\", \"message\":\"Plan not found with taskNo: " + taskNo + "\"}");
        }
    }
    private String generateTaskNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
        String formattedDate = dateFormat.format(new Date());
        return formattedDate;
    }
    public ResponseEntity<?>addUser(newUser user)
    {
        if (user.getEmailId()!=null && user.getEmailId()!="" && user.getPassword()!=null && user.getPassword()!="")
        {
            Query query = new Query(Criteria.where("emailId").is(user.emailId));
            newUser existingUser = mongoTemplate.findOne(query, newUser.class);
            if (existingUser==null)
            {
                mongoTemplate.save(user);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"status\":\"error\", \"message\":\"Already Existing User\"}");
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"error\", \"message\":\"Email or Password is empty\"}");
        }
        return ResponseEntity.ok().body("{\"status\":\"success\", \"message\":\"User added Successfully\"}");
    }
    public ResponseEntity<?>getUser(newUser user)
    {
        if (user.getEmailId()!=null && user.getEmailId()!="" && user.getPassword()!=null && user.getPassword()!="")
        {
            Query query = new Query(Criteria.where("emailId").is(user.emailId).and("password").is(user.password));
            newUser validUser = mongoTemplate.findOne(query, newUser.class);
            if(validUser==null)
            {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"error\", \"message\":\"Invalid Credentials\"}");
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"error\", \"message\":\"Email or Password is empty\"}");
        }
        return ResponseEntity.ok().body("{\"status\":\"success\", \"message\":\"User Exist\"}");
    }
}
