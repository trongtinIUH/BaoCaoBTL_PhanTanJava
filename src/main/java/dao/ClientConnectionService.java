package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ClientConnectionService extends Remote {
    void clientConnected() throws RemoteException;
    void setLoadData(Map<String, Boolean> loadData) throws RemoteException;
    void setMapIP_MSNV(Map<String, String> mapIP_MSNV) throws RemoteException;
    Map<String, Boolean> getLoadData() throws RemoteException;
    Map<String, String> getMapIP_MSNV() throws RemoteException;
    void deleteFromMapLoadData(String key) throws RemoteException;
    void deleteFromMapIP_MSNV(String key) throws RemoteException;
}