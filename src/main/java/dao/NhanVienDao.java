package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;

public interface NhanVienDao extends Remote{
	public boolean addNhanVien(NhanVien nv) throws RemoteException;
	public boolean deleteNhanVien(String id) throws RemoteException;
	public boolean updateNhanVien(NhanVien nv) throws RemoteException;
	public ArrayList<NhanVien> findAllNhanVien() throws RemoteException;
	public NhanVien findByID(String maNV) throws RemoteException;
	public ArrayList<NhanVien> findByName(String name) throws RemoteException; //Tìm tương đối
	public NhanVien findNhanVienToLogin(String maNV) throws RemoteException;
}
