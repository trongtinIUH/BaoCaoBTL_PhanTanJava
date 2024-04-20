package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import entity.ChiTietDichVu;


public interface ChiTietDichVuServices extends Remote {

	ArrayList<ChiTietDichVu> getAllChiTietDichVu() throws RemoteException;

	ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaHD(String maHD) throws RemoteException;

	ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaHDVaMaPhong(String maHD, String maPhong) throws RemoteException;

	ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaPhong(String maPhong) throws RemoteException;

	double tinhTongTienDVTheoMaHoaDon(String maHD) throws RemoteException;

	boolean addChiTietDV(ChiTietDichVu ctdv) throws RemoteException;

	boolean UpdateChiTietDV(ChiTietDichVu cthd) throws RemoteException;

	boolean deleteChiTietDV(String maSanPham) throws RemoteException;

	boolean deleteChiTietDV2(String maHD, String maSanPham, String maPhong) throws RemoteException;
}
