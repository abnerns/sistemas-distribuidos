package com.example.perfectamicable;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DistribuicaoService extends Remote {
    List<Long> findPerfectNumbers(long start, long end) throws RemoteException;
    List<String> findAmicablePairs(long start, long end) throws RemoteException;
}


