package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.TaiKhoan;

public interface DangNhapServices extends Remote {
	
public	List<TaiKhoan> getAllTaiKhoan() throws RemoteException;
public	boolean Timkiem(String maNV, String mk) throws RemoteException;
public	boolean doiMatKhau(String soDienThoai, String matKhauMoi)  throws RemoteException;
public	boolean TimkiemSDT(String SDT) throws RemoteException;
public	TaiKhoan LayMatKhauTheoMaNhanVien(String maNhanVien)  throws RemoteException;
public	boolean doiMatKhauTheoMaNV(String maNhanVien, String matKhauMoi) throws RemoteException;
public	boolean Them_taiKhoan_matKhau(TaiKhoan tk)  throws RemoteException;
public	String getRole(String maTaiKhoan, String matkhau) throws RemoteException;
	
}
