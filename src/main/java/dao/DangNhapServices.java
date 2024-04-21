package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.TaiKhoan;

public interface DangNhapServices extends Remote {
	
	ArrayList<TaiKhoan> getAllTaiKhoan() throws RemoteException;
	boolean Timkiem(String maNV, String mk) throws RemoteException;
	boolean doiMatKhau(String soDienThoai, String matKhauMoi)  throws RemoteException;
	boolean TimkiemSDT(String SDT) throws RemoteException;
	TaiKhoan LayMatKhauTheoMaNhanVien(String maNhanVien)  throws RemoteException;
	boolean doiMatKhauTheoMaNV(String maNhanVien, String matKhauMoi) throws RemoteException;
	boolean Them_taiKhoan_matKhau(TaiKhoan tk)  throws RemoteException;
	String getRole(String maTaiKhoan, String matkhau) throws RemoteException;
	
}
