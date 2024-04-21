package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface NhanVienService extends Remote{
	public boolean addNhanVien(NhanVien nv) throws RemoteException;
	public boolean deleteNhanVien(String id) throws RemoteException;
	public boolean updateNhanVien(NhanVien nv) throws RemoteException;
	public List<NhanVien> findAllNhanVien() throws RemoteException;
	public NhanVien findByID(String maNV) throws RemoteException;
	public List<NhanVien> findByName(String name) throws RemoteException; //Tìm tương đối
	public NhanVien findNhanVienToLogin(String maNV) throws RemoteException;
}
