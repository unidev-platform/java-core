package com.unidev.platform.common.dto.response;

/**
 * Common response object
 * @param <Payload>
 */
public class Response<Status, Payload> {

    protected Status status;
    protected Payload payload;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}