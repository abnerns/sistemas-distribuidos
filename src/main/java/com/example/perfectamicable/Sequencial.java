package com.example.perfectamicable;

import java.util.ArrayList;
import java.util.List;

public class Sequencial {

    // método para calcular a soma dos divisores próprios de um número
    public static long sumDivisors(long n) {
        if (n <= 1) {
            return 0;
        }
        long sum = 1; // 1 é sempre um divisor próprio
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

    // método para verificar se um número é perfeito
    public static boolean isPerfect(long n) {
        return sumDivisors(n) == n;
    }

    // método para verificar se um par de números é amigável
    public static boolean areAmicable(long a, long b) {
        if (a == b) {
            return false; // um número não pode ser amigável consigo mesmo
        }
        return sumDivisors(a) == b && sumDivisors(b) == a;
    }

    public static void main(String[] args) {
        long startRange = 1;
        long endRange = 10000; // exemplo de intervalo

        System.out.println("Verificando números perfeitos e amigáveis no intervalo [" + startRange + ", " + endRange + "]");

        List<Long> perfectNumbers = new ArrayList<>();
        List<String> amicablePairs = new ArrayList<>();

        long startTime = System.nanoTime();

        for (long i = startRange; i <= endRange; i++) {
            // verifica números perfeitos
            if (isPerfect(i)) {
                perfectNumbers.add(i);
            }

            // verifica números amigáveis (apenas para pares, para evitar duplicação e auto-comparação)
            for (long j = i + 1; j <= endRange; j++) {
                if (areAmicable(i, j)) {
                    amicablePairs.add("(" + i + ", " + j + ")");
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // tempo em milissegundos

        System.out.println("\nNúmeros Perfeitos encontrados:");
        if (perfectNumbers.isEmpty()) {
            System.out.println("Nenhum número perfeito encontrado no intervalo.");
        } else {
            for (long num : perfectNumbers) {
                System.out.println(num);
            }
        }

        System.out.println("\nPares Amigáveis encontrados:");
        if (amicablePairs.isEmpty()) {
            System.out.println("Nenhum par amigável encontrado no intervalo.");
        } else {
            for (String pair : amicablePairs) {
                System.out.println(pair);
            }
        }

        System.out.println("\nTempo de execução sequencial: " + duration + " ms");
    }
}


