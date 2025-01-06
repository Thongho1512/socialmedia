package com.oss.socialmedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oss.socialmedia.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
