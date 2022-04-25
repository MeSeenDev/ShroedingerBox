package quests.annotations;

public class weightTestA {

    @Weight(10)
    void method1() {
        System.out.println("method1");
    }

    @Weight(3)
    void method2() {
        System.out.println("method2");
    }

    @Weight(21)
    void method3() {
        System.out.println("method3");
    }
}
