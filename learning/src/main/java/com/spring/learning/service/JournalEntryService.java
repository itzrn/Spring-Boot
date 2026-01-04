package com.spring.learning.service;

import com.spring.learning.entity.JournalEntry;
import com.spring.learning.entity.User;
import com.spring.learning.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class JournalEntryService {


    @Autowired // dependency injection
    private JournalEntryRepository journalEntryRepository; // this is an interface which gets implemented at run time by spring and get inject here

    @Autowired
    private UserService userService;

    // @Transactional should be used here, but this is not the atlas server so giving error
//    @Transactional // this allows to completely run the function properly if something happen in between then it will undo the things happened before the point of error
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findUserByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);

            log.info("JOURNAL ENTRY CREATED SUCCESSFULLY FOR USER: {}",userName);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user = userService.findUserByUserName(userName);

            // first deleting from user's journalEntries List
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));


            if (removed) {
                userService.saveEntry(user);
                // deleting from journal_entries collection
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }


        return removed;
    }

}

/*
Controller ---> service ---> repository
 */
