package nl.craftsmen.demo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Some nice API changes.
 */
public class ApiDemo {


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
        System.out.println(laughter.repeat(10));
    }
    @Test
    public void testLines() {
        String someText = "First line\r\nSecond line\rThird line\nfourth line";
        Arrays.stream(someText.split("\r")).forEach(System.out::println);
        //java 11 methods
        someText.lines().forEach(System.out::println);
    }

    @Test
    public void testPathOf() throws IOException, URISyntaxException {
        URI uri = getClass().getResource("/file.txt").toURI();

        //java 10
        Files.readAllLines(Paths.get(uri)).forEach(System.out::println);

        //java 11
        Files.readAllLines(Path.of(uri)).forEach(System.out::println);

        //even simpler:
        System.out.println(Files.readString(Path.of(uri)));

        //there is also a write option
        Files.writeString(Path.of("newfile.txt"), "content", StandardOpenOption.CREATE);

    }

    @Test
    public void testCompareStringBuffer() {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbWithSameContent = new StringBuffer();
        sb.append("hi!");
        sbWithSameContent.append("hi!");
        System.out.println(sb.compareTo(sbWithSameContent));
    }

    @Test
    public void bufferedOutputStream() {
        String s = "Hello, world!";
        //java 10
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(s.getBytes(), 0, s.length());
        System.out.println(new String(byteArrayOutputStream.toByteArray()));
        //java 11
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        byteArrayOutputStream2.writeBytes(s.getBytes());
        System.out.println(new String(byteArrayOutputStream2.toByteArray()));
    }


    @Test
    public void optionalDemo() {
        Optional<String> optional = getStringValue();
        //java 10
        if (!optional.isPresent()) {
            System.out.println("empty!");
        }
        //java 11
        if (optional.isEmpty()) {
            System.out.println("empty!");
        }
    }

    private Optional<String> getStringValue() {
        return Optional.empty();
    }

    private void printQuoted(Object s) {
        System.out.println("s = [" + s + "]");
    }

}
