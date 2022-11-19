package com.unidev.platform.common.dto.request;

/**
 * Versioned request object with abstract payload and abstract version
 * @param <Version>
 * @param <Payload>
 */
public class RequestWithVersion<Version, Payload> extends Request<Payload> {

    protected Version version;

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}