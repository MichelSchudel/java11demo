package nl.craftsmen.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringDemo {


    @Test
    public void testStringStrip() {
        var stringExample = " \u0020\u0019Hello, world!\u0019";
        printQuoted(stringExample.trim());
        //java 11 methods
        printQuoted(stringExample.strip());
        printQuoted(stringExample.stripLeading());
        printQuoted(stringExample.stripTrailing());
    }

    @Test
    public void testBlank() {
        String onlySpaces = " ";
        String emptyString  = "";
        System.out.println(onlySpaces.isEmpty());
        //java 11 methods
        System.out.println(onlySpaces.isBlank());
        System.out.println(emptyString.isBlank());
    }

    @Test
    public void testRepeat() {
        String laughter = "ha";
        System.out.println(new String(new char[10]).replace("\0", laughter));
        //java 11 methods
        String moreLaughter = laughter.repeat(10);
        System.out.println(moreLaughter);
        assertThat(moreLaughter).isEqualTo("hahahahahahahahahaha");
    }
    @Test
    public void testLines() {
        String someText = "First line\r\nSecond line\rThird line\nfourth line";
        Arrays.stream(someText.split("\r")).forEach(System.out::println);
        //java 11 methods
        someText.lines().forEach(System.out::println);
    }

    private void printQuoted(Object s) {
        System.out.println("s = [" + s + "]");
    }

}
