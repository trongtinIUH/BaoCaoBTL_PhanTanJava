package dao.impl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.ChiTietHoaDon;

public interface ChiTietHoaDon_dao_impl extends Remote {
	ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException;
	ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) throws RemoteException;
	ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaPhong(String maPhong) throws RemoteException;
	double tinhSoGioHatTheoNgay(String date)  throws RemoteException;
	double tinhSoGioHatTheoThang(String thang, int nam) throws RemoteException ;
	double tinhSoGioHatTheoNam(int nam)  throws RemoteException;
	double tinhSoGioHatTheoNhieuNam(int nambt, int namkt) throws RemoteException;
	boolean addChiTietHD(ChiTietHoaDon cthd) throws RemoteException;
	boolean UpdateChiTietHD(ChiTietHoaDon cthd) throws RemoteException;
	boolean UpdateChiTietHD_ChuyenPhong(ChiTietHoaDon cthd) 	    throws RemoteException;
	boolean deleteChiTietHD(String maPhong)  throws RemoteException;
	ArrayList<ChiTietHoaDon> getCTHDPhongDangSD() throws RemoteException;
}
