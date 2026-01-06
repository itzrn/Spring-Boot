package com.spring.learning.repository;

import com.spring.learning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

public class UserRepositoryImp {

    @Autowired
    private MongoTemplate mongoTemplate; // this is provided by spring data mongo

    public List<User> getUserByUserName(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("aryan"));

//        by default it follows and operator
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

//        for or operator
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true)
//        ));


//        doesn't matter the field is array or list it treat as a string and match it
//        query.addCriteria(Criteria.where("roles").in("USER", "ADMIN"));



        return mongoTemplate.find(query, User.class);
    }
}
