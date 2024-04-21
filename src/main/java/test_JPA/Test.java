package test_JPA;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonDatPhongServices;
import dao.KhachHangServices;
import dao.PhieuDatPhongService;
import dao.PhongService;
import dao.impl.HoaDonDatPhongImpl;
import dao.impl.KhachHangImpl;
import dao.impl.PhieuDatPhongImpl;
import dao.impl.PhongImpl;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.PhieuDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) throws RemoteException {
//		EntityManager em;
//		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		PhongService phongService = new PhongImpl();
		
		double result = phongService.tinhTongTienPhongTheoMaHoaDon("HD2208010001");
		System.out.println(result);
	}
}
