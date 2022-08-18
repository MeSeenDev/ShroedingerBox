package string;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PhoneFormatterTest {

    @ParameterizedTest
    @MethodSource("phones")
    void prefixFormat(String phone,String expected) {

        String actual = PhoneFormatter.prefixFormat(phone);

        assertEquals(expected,actual);
    }


    private static Stream<Arguments> phones() {
        return Stream.of(
                arguments("9","9"),
                arguments("89519599857","+79519599857"),
                arguments("+79607282391","+79607282391"),
                arguments("","")
        );
    }
}