package com.example.perfectamicable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Paralela {

    // reutiliza os métodos de verificação da soluçao sequencial
    public static long sumDivisors(long n) {
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

    public static boolean isPerfect(long n) {
        return sumDivisors(n) == n;
    }

    public static boolean areAmicable(long a, long b) {
        if (a == b) {
            return false;
        }
        return sumDivisors(a) == b && sumDivisors(b) == a;
    }

    // classe Runnable para a tarefa de cada thread
    static class RangeChecker implements Runnable {
        private final long start;
        private final long end;
        private final List<Long> perfectNumbers;
        private final List<String> amicablePairs;

        public RangeChecker(long start, long end, List<Long> perfectNumbers, List<String> amicablePairs) {
            this.start = start;
            this.end = end;
            this.perfectNumbers = perfectNumbers;
            this.amicablePairs = amicablePairs;
        }

        @Override
        public void run() {
            for (long i = start; i <= end; i++) {
                // verifica se os números são perfeitos
                if (isPerfect(i)) {
                    synchronized (perfectNumbers) {
                        perfectNumbers.add(i);
                    }
                }

                // verifica números amigáveis, apenas para pares, pois é mais complexa em paralelo
                // para simplificar e evitar duplicação de trabalho, cada thread verifica apenas pares onde ambos os números estão no mesmo intervalo
                // uma abordagem mais robusta para amigáveis em paralelo exigiria um algoritmo diferente
                for (long j = i + 1; j <= end; j++) {
                    if (areAmicable(i, j)) {
                        synchronized (amicablePairs) {
                            amicablePairs.add("(" + i + ", " + j + ")");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        long startRange = 1;
        long endRange = 10000; // exemplo de intervalo
        int numThreads = Runtime.getRuntime().availableProcessors(); // nº de threads igual ao número de cores da CPU

        System.out.println("Verificando números perfeitos e amigáveis em paralelo no intervalo [" + startRange + ", " + endRange + "] com " + numThreads + " threads.");

        List<Long> perfectNumbers = Collections.synchronizedList(new ArrayList<>());
        List<String> amicablePairs = Collections.synchronizedList(new ArrayList<>());

        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long rangeSize = (endRange - startRange + 1);
        long chunkSize = rangeSize / numThreads;

        for (int i = 0; i < numThreads; i++) {
            long threadStart = startRange + (i * chunkSize);
            long threadEnd = (i == numThreads - 1) ? endRange : (threadStart + chunkSize - 1);
            executor.execute(new RangeChecker(threadStart, threadEnd, perfectNumbers, amicablePairs));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Execução paralela interrompida: " + e.getMessage());
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // tempo milissegundos

        System.out.println("\nNúmeros Perfeitos encontrados:");
        if (perfectNumbers.isEmpty()) {
            System.out.println("Nenhum número perfeito encontrado no intervalo.");
        } else {
            Collections.sort(perfectNumbers);
            for (long num : perfectNumbers) {
                System.out.println(num);
            }
        }

        System.out.println("\nPares Amigáveis encontrados:");
        if (amicablePairs.isEmpty()) {
            System.out.println("Nenhum par amigável encontrado no intervalo.");
        } else {
            Collections.sort(amicablePairs);
            for (String pair : amicablePairs) {
                System.out.println(pair);
            }
        }

        System.out.println("\nTempo de execução paralela: " + duration + " ms");
    }
}


