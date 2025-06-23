
package com.example.perfectamicable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Implementacao extends UnicastRemoteObject implements DistribuicaoService {

    public Implementacao() throws RemoteException {
        super();
    }

    // reutiliza os métodos de verificação da soluçao sequencial
    private static long sumDivisors(long n) {
        if (n <= 1) {
            return 0;
        }
        long sum = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i * i != n) {
                    sum += n / i;
                }
            }
        }
        return sum;
    }

    private static boolean isPerfect(long n) {
        return sumDivisors(n) == n;
    }

    private static boolean areAmicable(long a, long b) {
        if (a == b) {
            return false;
        }
        return sumDivisors(a) == b && sumDivisors(b) == a;
    }

    @Override
    public List<Long> findPerfectNumbers(long start, long end) throws RemoteException {
        List<Long> perfectNumbers = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            if (isPerfect(i)) {
                perfectNumbers.add(i);
            }
        }
        return perfectNumbers;
    }

    @Override
    public List<String> findAmicablePairs(long start, long end) throws RemoteException {
        List<String> amicablePairs = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            for (long j = i + 1; j <= end; j++) { // garante que j > i para evitar duplicação e auto-comparação
                if (areAmicable(i, j)) {
                    amicablePairs.add("(" + i + ", " + j + ")");
                }
            }
        }
        return amicablePairs;
    }
}


