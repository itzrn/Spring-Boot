package com.spring.learning.cache;

import com.spring.learning.entity.ConfigJournalAppEntity;
import com.spring.learning.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache { // this will get create once through-out the context means appCache will also get filled once, means this is working as in memory cache
    public enum keys{
        weather_api;
    }


    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity : all){
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }


    }
}

// now this class we can autowire it anywhere and can use the data with its bean

// to access enum
// AppCache.keys.weather_api.toString();
