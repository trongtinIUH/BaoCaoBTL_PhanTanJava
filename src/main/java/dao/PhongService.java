package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Phong;
import utils.DoanhThuLoaiPhong;

public interface PhongService extends Remote{
	public boolean addPhong(Phong ph) throws RemoteException;
	public boolean updatePhong(Phong ph, String maPhongMoi) throws RemoteException;
	public boolean deletePhong(String maPhong) throws RemoteException;
	public List<Phong> getallPhongs() throws RemoteException;
	public Phong getPhongTheoMaPhong(String maPhong) throws RemoteException;
	public List<Phong> getPhongTheoMaLoaiPhong(String maLoaiPhong) throws RemoteException;
	public List<Phong> getPhongTheoTrangThai(String trangThai) throws RemoteException;
	public List<Phong> getPhongTheoTenLoaiPhongVaTrangThai(String tenLoaiPhong, String trangThai) throws RemoteException;
	public List<Phong> getPhongTheoSucChua(String sucChua) throws RemoteException;
	public List<Phong> getPhongTheoTenLoaiPhong(String tenLoaiPhong) throws RemoteException;
	public List<Phong> getPhongTheoMaCTHD(String maHoaDon) throws RemoteException;
	public List<Phong> laydsPhongMoi() throws RemoteException;
	public List<Phong> getPhongTKTheoTrangThai(String trangThai, int soNguoi) throws RemoteException;
	public List<Phong> getPhongTKTheoTenLoaiPhong(String tenLoaiPhong, int soNguoi) throws RemoteException;
	public List<Phong> getPhongTKTheoTenLoaiPhongVaTrangThai(String tenLoaiPhong, String trangThai, int soNguoi) throws RemoteException;
	public List<Phong> getPhongTKTheoSoNguoiHat(int soNguoi) throws RemoteException;
	public double tinhTongTienPhongTheoMaHoaDon(String maHD) throws RemoteException;
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNgay(String ngay) throws RemoteException;
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoThang(String thang, int nam) throws RemoteException;
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNam(int nam) throws RemoteException;
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNhieuNam(int nam_bd, int nam_kt) throws RemoteException;
}
