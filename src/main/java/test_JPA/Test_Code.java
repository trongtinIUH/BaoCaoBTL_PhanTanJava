package test_JPA;

import java.rmi.RemoteException;

import dao.ChiTietDichVuServices;
import dao.HoaDonDatPhongServices;
import dao.KhuyenMaiServices;
import dao.impl.ChiTietDichVu_dao_impl;
import dao.impl.HoaDonDatPhongImpl;
import dao.impl.KhuyenMaiImpl;


public class Test_Code {
public static void main(String[] args) throws RemoteException {
//	ChiTietDichVuServices ctdv = new ChiTietDichVu_dao_impl();
//	System.out.println(ctdv.getAllChiTietDichVu());
	//System.out.println(ctdv.getChiTietDichVuTheoMaHD("HD2007150001"));
//	
//	KhuyenMaiServices km = new KhuyenMaiImpl();
//	System.out.println(km.getKhuyenMaiTheoMaKhuyenMai("KM010121"));
	HoaDonDatPhongServices hd= new HoaDonDatPhongImpl();
	System.out.println(hd.getHoaDonDatPhongTheoMaHD("HD2007150001"));
}
}
