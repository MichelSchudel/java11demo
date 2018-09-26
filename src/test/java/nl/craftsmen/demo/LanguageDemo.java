package nl.craftsmen.demo;

import com.sun.istack.Nullable;
import org.junit.Test;

/**
 * Demo showing local variable syntax for lamba parameters.
 */

public class LanguageDemo {

    @FunctionalInterface
    interface ITest {
        double tupleFunction(int x, int y);
    }

    @Test
    public void test() {
        //java 10
        ITest test = ( int x, int y) -> x + y;
        System.out.println(test.tupleFunction(1,2));

        //java 11
        ITest test2 = ( @Nullable var x, @Nullable var y) -> x + y;
        System.out.println(test2.tupleFunction(1,2));
    }
}
