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

import connectDB.ConnectDB;
import dao.impl.ChiTietHoaDon_dao_impl;
import entity.ChiTietHoaDon;
import entity.Phong;
import entity.HoaDonDatPhong;

public interface ChiTietHoaDonServices extends Remote {
	

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
