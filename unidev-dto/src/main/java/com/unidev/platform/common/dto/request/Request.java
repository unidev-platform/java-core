package com.unidev.platform.common.dto.request;

/**
 * Abstract request object which holds abstract payload
 * @param <Payload>
 */
public class Request<Payload> {

    protected Payload payload;

    public Payload getPayload() {
        return payload;
    }
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}