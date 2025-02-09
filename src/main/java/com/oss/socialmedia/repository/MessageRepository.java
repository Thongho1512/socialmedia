package com.oss.socialmedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oss.socialmedia.model.MessageEntity;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {

}
