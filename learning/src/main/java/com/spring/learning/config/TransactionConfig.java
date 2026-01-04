package com.spring.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory){ // here automatically the implementation of MongoDatabaseFactory is passed which is SimpleMongoClientDatabaseFactory
        return new MongoTransactionManager(dbFactory); // this requires MongoDatabaseFactory as parameter
    }
    // MongoDatabaseFactory helps with the connection to database
}

/*

using @EnableTransactionManagement spring boot will make a transactional context to all those functions which have annotated with transactional

transactional context mean it will create a container for those function and those function will get treated as a one operation
if in between anything happen everything will roll back

if suppose two user called this function at the same time then spring boot make two separate transactional context and do
operation independently


All these things will get done by whom?
these all things will done by PlatformTransactionalManager which is an interface
which get implement by MongoTransactionalManger

so there is the need to make the bean that PlatformTransactionalManager implementation is MongoTransactionalManger
 */
