package com.tony.JobZygo.repo;

import com.tony.JobZygo.entity.User;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByFirstName(@NonNull String firstName);
}
