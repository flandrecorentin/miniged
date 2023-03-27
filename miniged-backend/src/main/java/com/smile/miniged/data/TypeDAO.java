package com.smile.miniged.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.smile.miniged.model.Type;
@Service
@EnableMongoRepositories
@Repository
public interface TypeDAO extends MongoRepository<Type , String>{

    @Query("{name:'?0'") 
    Type findTypeByName(String name);

}