package quests.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import quests.reflection.factory.myFactory;
import quests.reflection.factory.optionalFactory;
import quests.reflection.pojo.Ass;
import quests.reflection.pojo.Foo;

class FactoryATest {

    @Test
    void generateAssInstance() throws Exception {
        Assertions.assertEquals(new FactoryAss().generateInstance().getClass(), Ass.class);
    }

    @Test
    void generateFooInstance() throws Exception {
        Assertions.assertEquals(new FactoryFoo().generateInstance().getClass(), Foo.class);
    }
    @Test
    void generateAssOptInstance() throws Exception {
        Assertions.assertEquals(new FactoryAss().generateOptInstance().getClass(), Ass.class);
    }

    @Test
    void generateFooOptInstance() throws Exception {
        Assertions.assertEquals(new FactoryFoo().generateOptInstance().getClass(), Foo.class);
    }

    public class FactoryAss extends myFactory<Ass> implements optionalFactory<Ass> {
    }

    public class FactoryFoo extends myFactory<Foo> implements optionalFactory<Foo> {
    }
}