package com.text.golo.repository;

import com.text.golo.entity.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends MongoRepository<Text, String> {


}
