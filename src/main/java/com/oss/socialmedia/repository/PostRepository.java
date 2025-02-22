package com.oss.socialmedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oss.socialmedia.model.MessageEntity;

public interface PostRepository extends MongoRepository<MessageEntity, String>{

}
