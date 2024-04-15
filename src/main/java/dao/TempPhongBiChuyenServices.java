package dao;
import entity.TempPhongBiChuyen;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TempPhongBiChuyenServices extends Remote {
	public ArrayList<TempPhongBiChuyen> getAllTemp() throws RemoteException;
	public boolean addTemp(TempPhongBiChuyen tmp) throws RemoteException;
	public boolean deleteALLTempPhongBiChuyen() throws RemoteException;
	public boolean deleteTempPhongBiChuyen(String maPhong) throws RemoteException;
	public boolean updateTempPhongBiChuyen(String maPhongCu, String maPhongMoi) throws RemoteException;
}
