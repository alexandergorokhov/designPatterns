import mahi.FactorialPrime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.*;

public class MahiTest {
    private static FactorialPrime factorialPrime;

    @BeforeClass
    public static void setUp() {
        factorialPrime = new FactorialPrime();
    }

    @Test
    public void factorialPrimeNumber10Test() {
        Set<Integer> result = factorialPrime.getPrimeFactorsOfAnumber(10);
        Set<Integer> expected = new TreeSet<>() {{
            add(2);
            add(5);
        }};
        Assert.assertEquals(result, expected);
    }

    @Test
    public void factorialPrimeNumber60Test() {
        Set<Integer> result = factorialPrime.getPrimeFactorsOfAnumber(60);
        Set<Integer> expected = new TreeSet<>() {{
            add(2);
            add(3);
            add(5);
        }};
        Assert.assertEquals(result, expected);
    }

    @Test
    public void isPrimeShouldReturnTrueTest() {
        Boolean result = factorialPrime.isPrime(17);
        Boolean expected = true;
        Assert.assertTrue(expected.equals(result));
    }

    @Test
    public void isPrimeShouldReturnFalseTest() {
        Boolean result = factorialPrime.isPrime(10);
        Boolean expected = false;
        Assert.assertEquals(result, expected);
    }
}
