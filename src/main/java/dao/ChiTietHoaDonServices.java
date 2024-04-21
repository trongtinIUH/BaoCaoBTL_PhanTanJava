package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.impl.ChiTietHoaDon_dao_impl;
import entity.ChiTietHoaDon;
import entity.Phong;
import entity.HoaDonDatPhong;

public interface ChiTietHoaDonServices extends Remote {
	

public	List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException;
public	List<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) throws RemoteException;
public	List<ChiTietHoaDon> getChiTietHoaDonTheoMaPhong(String maPhong) throws RemoteException;
public	double tinhSoGioHatTheoNgay(String date)  throws RemoteException;
public	double tinhSoGioHatTheoThang(String thang, int nam) throws RemoteException ;
public	double tinhSoGioHatTheoNam(int nam)  throws RemoteException;
public	double tinhSoGioHatTheoNhieuNam(int nambt, int namkt) throws RemoteException;
public	boolean addChiTietHD(ChiTietHoaDon cthd) throws RemoteException;
public	boolean UpdateChiTietHD(ChiTietHoaDon cthd) throws RemoteException;
public	boolean UpdateChiTietHD_ChuyenPhong(ChiTietHoaDon cthd) 	    throws RemoteException;
public	boolean deleteChiTietHD(String maPhong)  throws RemoteException;
public	List<ChiTietHoaDon> getCTHDPhongDangSD() throws RemoteException;
	
}
