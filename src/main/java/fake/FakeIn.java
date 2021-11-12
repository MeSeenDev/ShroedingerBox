package fake;

import java.io.ByteArrayInputStream;

public class FakeIn {
    public static void emulate(String string) {
        System.setIn(new ByteArrayInputStream(string.getBytes()));
    }
}
