package com.oss.socialmedia.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comments")
public class CommentEntity {
    
    @Id
    private String id;
    @Field("post_id")
    private String postId;
    @Field("user_id")
    private String userId;
    
    private String content;
    @Field("created_at")
    @CreatedDate
    private Instant createdAt;
    @Field("updated_at")
    @LastModifiedDate
    private Instant updatedAt;
}