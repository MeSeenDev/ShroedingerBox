package quests.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FillTest {

    @ParameterizedTest()
    @MethodSource("provideStringsForIsBlank")
    void fillWithZeros(int num, String correct) {
        String filled = Fill.fillWithZeros(num);
        Assertions.assertEquals(correct, filled);
    }
    @ParameterizedTest()
    @MethodSource("provideStringsForIsBlank")
    void fillWithZerosExt(int num, String correct) {
        String filled = FillFIleKt.fillWithZeros(num);
        Assertions.assertEquals(correct, filled);
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(0, "000000"),
                Arguments.of(1, "000001"),
                Arguments.of(15, "000015"),
                Arguments.of(0, "000000"),
                Arguments.of(987456, "987456")
        );
    }
}