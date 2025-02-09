package com.oss.socialmedia.model;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.oss.socialmedia.common.FriendStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "friends")
public class FriendEntity {
    private String id;
    @Field("user_id1")
    private String userId1;
    @Field("user_id2")
    private String userId2;
    private FriendStatus status;
    @Field("created_at")
    private Instant createdAt;
    @Field("updated_at")
    private Instant updatedAt;
}
