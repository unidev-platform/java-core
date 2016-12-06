package com.unidev.platform.common.dto.response;

/**
 * Versioned response object
 * @param <Version>
 * @param <Payload>
 */
public class ResponseWithVersion<Version, Status, Payload> extends Response<Status, Payload> {

    protected Version version;

    public Version getVersion() {
        return version;
    }
    public void setVersion(Version version) {
        this.version = version;
    }

}