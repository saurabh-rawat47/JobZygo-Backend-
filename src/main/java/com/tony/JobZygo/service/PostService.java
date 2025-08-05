package com.tony.JobZygo.service;

import com.tony.JobZygo.entity.JobPost;
import com.tony.JobZygo.repo.PostRepo;
import com.tony.JobZygo.repo.SearchRepoIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    SearchRepoIml searchRepoIml;

    public List<JobPost> getAllPost() {
        return postRepo.findAll();
    }

    public JobPost createJP(JobPost jobPost) {
        postRepo.save(jobPost);
        return jobPost;
    }


    public List<JobPost> findPostByText(String text) {
      return  searchRepoIml.findByText(text);
    }


}
