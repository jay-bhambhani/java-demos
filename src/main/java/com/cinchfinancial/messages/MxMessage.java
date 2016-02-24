package com.cinchfinancial.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jbhambhani on 2/16/16.
 */

public class MxMessage extends SimpleMessage {

    private int cinchUserId;
    private int messageId;

    public MxMessage() {

    }

    @JsonCreator
    public MxMessage (@JsonProperty("cinchUser") int cinchUser,
                      @JsonProperty("messageId") int messageNoId,
                      @JsonProperty("message") String messageText) {
        setMessage(messageText);
        setCinchUser(cinchUser);
        setMessageId(messageNoId);

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


    @Override
    public String toString() {
        String messageText = getMessage();
        return "CinchUser: " + this.cinchUserId + ", Message - " + this.messageId +  ": " + messageText;
    }

}

