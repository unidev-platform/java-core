/**
 * Copyright (c) 2017 Denis O <denis.o@linux.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
