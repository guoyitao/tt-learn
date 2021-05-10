package com.gyt.shiyan2.san;

import java.util.HashSet;

public class Test3 {
    HashSet hashSet = new HashSet();

    public void factor(int number) {
        if(isPrime(number))
        {
            hashSet.add(number);
        }
        for (int i = 2; i <= number - 1; i++) {
            if (number % i == 0) {
                hashSet.add(i);
                int num = number / i;
                if (isPrime(num)) {
                    hashSet.add(num);
                } else {
                    factor(number / i);
                }
                break;
            }
        }
    }

    public HashSet getHashSet() {
        return hashSet;
    }

    public boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
