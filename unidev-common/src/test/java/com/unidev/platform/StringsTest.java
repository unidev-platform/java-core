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


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

/**
 * Tests for strings services
 */
public class StringsTest {

    @Test
    public void testHashGeneration() {
        String hash1 = Strings.genHash("potato", 5, 10);
        String hash2 = Strings.genHash("potato", 5, 10);
        assertThat(hash1, is(hash2));

        String customHash1 = Strings.genHash("potato", 5, 10, "tomato");
        String customHash2 = Strings.genHash("potato", 5, 10, "tomato");
        assertThat(customHash1, is(customHash2));
    }

    @Test
    public void testRemoveValueBetween() {
        String text = "ABC-1234-XYZ";
        String updatedText = Strings.removeValueBetween(text, "ABC", "XYZ", false);
        assertThat(updatedText, is("ABCXYZ"));

        String message = "Message1<b>remove me</b>.Message2<b>remove me</b>.";
        String updatedMessage = Strings.removeValueBetween(message, "<b>", "</b>", true);
        assertThat(updatedMessage, is("Message1.Message2<b>remove me</b>."));

        String notFoundMessage = Strings.removeValueBetween(message, "<p>", "</p>", true);
        assertThat(notFoundMessage, is(nullValue()));

    }

}
