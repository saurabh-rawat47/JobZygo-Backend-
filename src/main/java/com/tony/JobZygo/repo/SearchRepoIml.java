package com.tony.JobZygo.repo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tony.JobZygo.entity.JobPost;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepoIml implements SearchRepo {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter mongoConverter;


    @Override
    public List<JobPost> findByText(String text) {
        final List<JobPost> jobPosts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("tony");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "jobsearch")
                                .append("text", new Document("query", text)
                                        .append("path", Arrays.asList("desc", "techs", "profile")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));
        result.forEach(doc -> jobPosts.add(mongoConverter.read(JobPost.class, doc)));
        return jobPosts;
    }
}
