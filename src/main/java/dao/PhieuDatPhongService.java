package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import entity.PhieuDatPhong;

public interface PhieuDatPhongService extends Remote{
	public boolean addPhieuDatPhong(PhieuDatPhong pdp) throws RemoteException;
	public boolean xoaPhieuDatPhongTheoMa(String maPhong);
	public List<PhieuDatPhong> getAllsPhieuDatPhong() throws RemoteException;
	public List<PhieuDatPhong> getMaPhongDatTruoc() throws RemoteException;
	public PhieuDatPhong getPDPDatTruocTheoMaPhong(String maPhong) throws RemoteException;
	public PhieuDatPhong getPhieuDatPhongTheoMaPDP(String maPhieu) throws RemoteException;
	public PhieuDatPhong getPhieuDatPhongPhongCho(String maPhong) throws RemoteException;
	public List<PhieuDatPhong> getPhieuDatPhongTheoMaKH(String maKhachHang) throws RemoteException;
	public List<PhieuDatPhong> getDanhSachPhieuDatPhongTheoMaPhong(String maPhong) throws RemoteException;
	public List<PhieuDatPhong> getPhieuDatPhongInfo() throws RemoteException;
	public PhieuDatPhong timThongTinPhieuDatPhongTheoMaPhong(String maPhong) throws RemoteException;
	public List<PhieuDatPhong> getAllsPhieuDatPhong_ChuaThanhToan() throws RemoteException;
	public List<PhieuDatPhong> getAllsPhieuDatPhong_DangSuDung() throws RemoteException;
	public PhieuDatPhong getPhieuDatPhongTheoMaPDP_DangSuDung(String maPhieu) throws RemoteException;
	public PhieuDatPhong getPhieuDatPhongTheoMaPhong_TrangThaiCho(String maPhong) throws RemoteException;
	public List<PhieuDatPhong> getAllsPhieuDatPhong_PhongCho() throws RemoteException;
	public List<PhieuDatPhong> getAllsPhieuDatPhong_DaThanhToan() throws RemoteException;
	public List<PhieuDatPhong> getPDPTheoNgayNhan(LocalDate ngayGioNhanPhong) throws RemoteException;
	public List<PhieuDatPhong> getPDPTheoThangNhan(YearMonth thangNhan) throws RemoteException;	
	public List<PhieuDatPhong> getPDPTheoNamNhan(int namNhan) throws RemoteException;
}
