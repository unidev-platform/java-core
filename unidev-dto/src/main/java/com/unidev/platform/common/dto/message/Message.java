package com.unidev.platform.common.dto.message;

/**
 * Generic message holder
 */
public class Message<Payload> {

    protected Payload message;

    public Payload getMessage() {
        return message;
    }

    public void setMessage(Payload message) {
        this.message = message;
    }
}