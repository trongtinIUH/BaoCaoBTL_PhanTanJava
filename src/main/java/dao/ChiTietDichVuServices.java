package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.ChiTietDichVu;


public interface ChiTietDichVuServices extends Remote {

 public	List<ChiTietDichVu> getAllChiTietDichVu() throws RemoteException;

 public	List<ChiTietDichVu> getChiTietDichVuTheoMaHD(String maHD) throws RemoteException;

 public	List<ChiTietDichVu> getChiTietDichVuTheoMaHDVaMaPhong(String maHD, String maPhong) throws RemoteException;

 public	List<ChiTietDichVu> getChiTietDichVuTheoMaPhong(String maPhong) throws RemoteException;

 public	double tinhTongTienDVTheoMaHoaDon(String maHD) throws RemoteException;

 public	boolean addChiTietDV(ChiTietDichVu ctdv) throws RemoteException;

 public	boolean UpdateChiTietDV(ChiTietDichVu cthd) throws RemoteException;
 public ChiTietDichVu findChiTietDichVu(String maHoaDon, String maPhong, String maSanPham) throws RemoteException;

 public boolean deleteChiTietDV(String maSanPham) throws RemoteException;

 public boolean deleteChiTietDV2(String maHD, String maSanPham, String maPhong) throws RemoteException;
}
