package com.tony.JobZygo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "JobPost")
@Data
public class JobPost {
    ObjectId id;
    private String profile;
    private int exp;
    private String jobType;
    private String companyName;
    private String desc;
    private int salary;
    private String location;
    List<String> techs = new ArrayList<>();
}

