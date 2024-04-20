package dao;

import entity.TempDatPhong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TempDatPhongServices extends Remote {
	public  ArrayList<TempDatPhong> getAllTemp() throws RemoteException;
	public boolean addTemp(TempDatPhong tempDatPhong) throws RemoteException;
	public boolean deleteALLTempDatPhong() throws RemoteException;
	public boolean deleteTempDP(String maDP) throws RemoteException;
	public boolean updateTempDP(String maPhong, int soNguoi) throws RemoteException;
}
