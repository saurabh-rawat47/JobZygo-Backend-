package com.tony.JobZygo.repo;

import com.tony.JobZygo.entity.JobPost;

import java.util.List;

public interface SearchRepo {
    List<JobPost> findByText(String text);
}
