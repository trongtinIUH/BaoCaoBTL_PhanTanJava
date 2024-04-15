package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import utils.ModelThongKe;
import utils.ModelThongKeDTNhieuNam;
import utils.ModelThongKeKH;

public interface ThongKeServices extends Remote {
	public ArrayList<ModelThongKe> thongKeTheoNam(String yearStart, String yearEnd) throws RemoteException;
	public ArrayList<ModelThongKeDTNhieuNam> thongKeTheoNhieuNam(int yearStart, int yearEnd) throws RemoteException;
	public ArrayList<ModelThongKe> updateCboYear() throws RemoteException;
	public ArrayList<ModelThongKe> updateCboMonth() throws RemoteException;
	public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhat() throws RemoteException;
	public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhatTheoNam(String year) throws RemoteException;
	public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhatTheoThang(String year, String month) throws RemoteException;
	
}
