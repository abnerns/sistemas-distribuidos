
package com.example.perfectamicable;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.util.concurrent.TimeUnit;

public class Cliente {

    public static void main(String[] args) {
        String host = (args.length < 1) ? "localhost" : args[0];
        long startRange = 1;
        long endRange = 10000; // exemplo de intervalo
        int numClients = 4; // número de clientes/tarefas para distribuir o trabalho

        System.out.println("Verificando números perfeitos e amigáveis de forma distribuída no intervalo [" + startRange + ", " + endRange + "] com " + numClients + " clientes.");

        List<Long> perfectNumbers = Collections.synchronizedList(new ArrayList<>());
        List<String> amicablePairs = Collections.synchronizedList(new ArrayList<>());

        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(numClients);
        List<Future<?>> futures = new ArrayList<>();

        long rangeSize = (endRange - startRange + 1);
        long chunkSize = rangeSize / numClients;

        for (int i = 0; i < numClients; i++) {
            long threadStart = startRange + (i * chunkSize);
            long threadEnd = (i == numClients - 1) ? endRange : (threadStart + chunkSize - 1);

            Callable<Void> task = () -> {
                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    DistribuicaoService service = (DistribuicaoService) registry.lookup("PerfectAmicableService");

                    List<Long> perfects = service.findPerfectNumbers(threadStart, threadEnd);
                    synchronized (perfectNumbers) {
                        perfectNumbers.addAll(perfects);
                    }

                    List<String> amicables = service.findAmicablePairs(threadStart, threadEnd);
                    synchronized (amicablePairs) {
                        amicablePairs.addAll(amicables);
                    }
                } catch (Exception e) {
                    System.err.println("Erro no cliente RMI: " + e.toString());
                    e.printStackTrace();
                }
                return null;
            };
            futures.add(executor.submit(task));
        }

        executor.shutdown();
        try {
            for (Future<?> future : futures) {
                future.get(); // espera todas as tarefas serem concluídas
            }
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            System.err.println("Execução distribuída interrompida: " + e.getMessage());
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // tempo em milissegundos

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

        System.out.println("\nTempo de execução distribuída: " + duration + " ms");
    }
}


