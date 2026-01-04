package com.spring.learning.repository;

import com.spring.learning.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Object> {
    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
