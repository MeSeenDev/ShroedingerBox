package quests.lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class NonNullListTest {


    @Test
    void add() {
        List<String> list = NonNullList.getInstance();
        list.add(null);
        Assertions.assertEquals(1 , list.size());

    }

    @Test
    void contains() {
        List<String> list = NonNullList.<String>createFrom(Arrays.asList("as"));
        Assertions.assertEquals(false, list.equals(Arrays.asList("as")));
    }

    @Test
    void addAll() {
    }

    @Test
    void testAddAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void set() {
    }

    @Test
    void testAdd() {
    }
}