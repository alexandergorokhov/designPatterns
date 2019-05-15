package mahi;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FactorialPrime {

    public Set<Integer> getPrimeFactorsOfAnumber(int number) {
        Set<Integer> primeFactors = new TreeSet<>();
        while (number % 2 == 0) {
            primeFactors.add(2);
            number /= 2;

        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {

            while (number % i == 0) {
                primeFactors.add(i);

                number /= i;
            }
        }
        if (number > 2) {
            primeFactors.add(number);
        }
        return primeFactors;
    }

    public Boolean isPrime(int number) {
        if (number > 2 && number % 2 == 0) {
            return false;
        }
        int top = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < top; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;

    }
}
