package dao;
import entity.TempThanhToan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TempThanhToanServices extends Remote {
	public ArrayList<TempThanhToan> getAllTemp() throws RemoteException;

	public boolean addTemp(TempThanhToan tmp) throws RemoteException;

	public boolean deleteALLTempThanhToan() throws RemoteException;
}
