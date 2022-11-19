package com.unidev.platform;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

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
