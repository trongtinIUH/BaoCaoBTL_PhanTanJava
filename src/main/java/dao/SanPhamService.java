package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.SanPham;

public interface SanPhamService extends Remote{
	public boolean addSanPham(SanPham sp) throws RemoteException;
	public boolean updateSanPham(SanPham sp) throws RemoteException;
	public boolean updateSLTon(int slTon, String ma) throws RemoteException;
	public boolean deleteSanPham(String maSP);
	public List<SanPham> getAllSanPhams() throws RemoteException;
	public SanPham getSanPhamTheoMaSP(String maSP) throws RemoteException;
	public String getLoaiSanPhamTheoMaSP(String maSP) throws RemoteException;
	public List<SanPham> getSanPhamTheoTenSanPham(String tenSP) throws RemoteException;
	public SanPham getSanPhamTheoTenSanPham2(String tenSP) throws RemoteException;
	public List<SanPham> getSanPhamTheoLoaiSanPham(String loaiSP) throws RemoteException;
	public List<SanPham> getallSp() throws RemoteException;
}
