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
package com.unidev.platform;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Extenstion of StringUtils with code used in unidev.
 * @see StringUtils
 * @author denis
 */
@Slf4j
public class Strings extends StringUtils {

    /**
     * Remove first value between begin end, if not pattern not found return null
     * @param content Content which should be updated
     * @param begin Begin marker
     * @param end  End marker
     * @param includeBeginEnd Include in removal begin end
     * @return Updated content with removed value
     */
    public static String removeValueBetween(String content, String begin, String end, boolean includeBeginEnd) {
        String valueBetween = substringBetween(content, begin, end);
        if (isBlank(valueBetween)) {
            return null;
        }
        if (includeBeginEnd) {
            return replaceOnce(content, begin + valueBetween + end, "");
        }
        return replaceOnce(content, valueBetween, "");
    }

    /**
     * Generate string unique hash based on provided dictionary
     * @param str
     * @param minLength
     * @param maxLength
     * @param dictionary
     * @return
     */
    public static String genHash(String str, int minLength, int maxLength, String dictionary) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(str.hashCode());
        int length = minLength + random.nextInt(maxLength - minLength);
        for (int i = 0; i < length; i++) {
            int id = random.nextInt(dictionary.length());
            stringBuilder.append(dictionary.charAt(id));
        }
        return stringBuilder.toString();
    }

    /**
     * Generate string unique hash based on provided dictionary
     * @param str
     * @param minLength
     * @param maxLength
     * @return
     */
    public static String genHash(String str, int minLength, int maxLength) {
        return genHash(str, minLength, maxLength, "abcdefghiklmnopqrstxyz0123456789");
    }
}

