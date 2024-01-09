package com.todolistservice.todoservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "newUser")
public class newUser {
     public String emailId;
     public String password;
}
