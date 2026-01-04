package com.spring.learning.repository;

import com.spring.learning.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}

/*
Spring data mongodb provides you an interface called as MongoRepository

MongoRepository is an interface provided by data mongodb which provides you the standard crud operations
 */
