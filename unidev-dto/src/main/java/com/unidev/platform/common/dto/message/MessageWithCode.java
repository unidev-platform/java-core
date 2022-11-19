package com.unidev.platform.common.dto.message;

/**
 * Holder for message with code
 * @param <Code>
 * @param <Payload>
 */
public class MessageWithCode<Code, Payload> extends Message<Payload> {

    protected Code code;

    public Code getCode() {
        return code;
    }
    public void setCode(Code code) {
        this.code = code;
    }

}
