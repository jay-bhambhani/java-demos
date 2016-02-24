package com.cinchfinancial.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jbhambhani on 2/19/16.
 */
public class TransUnionMessage extends SimpleMessage {

    private int cinchUserId;
    private int messageId;
    private String transUnionUser;

    public TransUnionMessage() {

    }

    @JsonCreator
    public TransUnionMessage(@JsonProperty("cinchUser") int cinchUser,
                             @JsonProperty("messageId") int messageNoId,
                             @JsonProperty("message") String messageText,
                             @JsonProperty("tUUser") String transUnionUser) {
        setMessage(messageText);
        setCinchUser(cinchUser);
        setMessageId(messageNoId);
        this.transUnionUser = transUnionUser;

    }

    public int getCinchUser() {
        return cinchUserId;
    }

    public void setCinchUser(int cinchUser) {
        this.cinchUserId = cinchUser;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageNoId) {
        this.messageId = messageNoId;
    }

    public String getTransUnionUser() {
        return transUnionUser;
    }

    public void setTransUnionUser(String transUnionUser) {
        this.transUnionUser = transUnionUser;
    }

    @Override
    public String toString() {
        String messageText = getMessage();
        return "CinchUser: " + this.cinchUserId + ", Message - " + this.messageId + ": " + messageText + "TransUnionUser: " + transUnionUser;
    }
}
