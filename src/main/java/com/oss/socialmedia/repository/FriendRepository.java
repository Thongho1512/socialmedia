package com.oss.socialmedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oss.socialmedia.model.FriendEntity;

public interface FriendRepository extends MongoRepository<FriendEntity, String> {

}
