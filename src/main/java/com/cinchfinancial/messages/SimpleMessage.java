package com.cinchfinancial.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by jbhambhani on 2/16/16.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY)
/*
@JsonSubTypes({
        @JsonSubTypes.Type(value = MxMessage.class)
})
*/

public class SimpleMessage {

    private String message;

    public SimpleMessage () {

    }
    public SimpleMessage (String message) {
        this.message = message;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String messageText) {
        this.message = messageText;
    }



}
