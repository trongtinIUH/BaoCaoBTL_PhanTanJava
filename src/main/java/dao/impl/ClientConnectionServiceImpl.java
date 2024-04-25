package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import app.DataManager;
import dao.ClientConnectionService;

public class ClientConnectionServiceImpl extends UnicastRemoteObject implements ClientConnectionService {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5961152039357364950L;

	public ClientConnectionServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void clientConnected() throws RemoteException {
    	System.out.println(DataManager.getMapIP_MSNV());
    	System.out.println("=============================");
    	System.out.println(DataManager.getRole());
    	System.out.println("=============================");
    	System.out.println(DataManager.getLoadData());
        System.out.println("A client has connected.");
        System.out.println("===================================");
    }
    
    @Override
    public void deleteFromMapLoadData(String key) throws RemoteException {
        DataManager.deleteFromMapLoadData(key);
    }

    @Override
    public void deleteFromMapIP_MSNV(String key) throws RemoteException {
        DataManager.deleteFromMapIP_MSNV(key);
    }
    
    @Override
    public void setLoadData(Map<String, Boolean> loadData) throws RemoteException {
        DataManager.setLoadData(new HashMap<>(loadData));
    }

    @Override
    public void setMapIP_MSNV(Map<String, String> mapIP_MSNV) throws RemoteException {
        DataManager.setMapIP_MSNV(new HashMap<>(mapIP_MSNV));
    }
    
    @Override
    public Map<String, Boolean> getLoadData() throws RemoteException {
        return new HashMap<>(DataManager.getLoadData());
    }

    @Override
    public Map<String, String> getMapIP_MSNV() throws RemoteException {
        return new HashMap<>(DataManager.getMapIP_MSNV());
    }
}