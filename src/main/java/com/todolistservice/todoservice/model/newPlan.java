package com.todolistservice.todoservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "newPlan")
public class newPlan {
    private String taskNo;
    private String plan;
    private boolean status;
    private String user;
}
