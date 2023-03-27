package com.smile.miniged.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.smile.miniged.model.Doc;

@Service
@EnableMongoRepositories
@Repository
public interface DocumentDAO extends MongoRepository<Doc , String>{

    @Query("{id:'?0'") 
    Doc findDocById(String id);
}
