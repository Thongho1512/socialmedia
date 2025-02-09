package com.oss.socialmedia.model;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "messages")
public class MessageEntity {

    @Id
    private String Id;
    @Field("sender_id")
    private String senderId;
    @Field("receiver_id")
    private String receiverId;
    @Field("message_text")
    private String messageText;
    @Field("message_type")
    private String messageType;
    @Field("is_read")
    private Boolean isRead;
    @Field("created_at")
    private Instant createdAt;
    @Field("updated_at")
    private Instant updatedAt;

    // Getters and Setters
}
