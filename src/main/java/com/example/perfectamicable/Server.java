
package com.example.perfectamicable;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            DistribuicaoService service = new Implementacao();
            Registry registry = LocateRegistry.createRegistry(1099); // porta padrão do RMI
            registry.rebind("PerfectAmicableService", service);
            System.out.println("Servidor RMI de Números Perfeitos e Amigáveis pronto.");
        } catch (Exception e) {
            System.err.println("Erro no servidor RMI: " + e.toString());
            e.printStackTrace();
        }
    }
}


