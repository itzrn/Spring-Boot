package com.spring.learning.entity;

import com.spring.learning.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// for relational we use to write @Entity to connect with ORM but here as we create a document we will @Document
@Document(collection = "journal_entries") // this also tell a row will be like bellow class
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Data -> this contains above all lombok annotation
public class JournalEntry {
    @Id // making as primary key
    private ObjectId id; // this id will work as foreign key for user

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
//    private Sentiment sentiment;
}
// this is called a pojo class
