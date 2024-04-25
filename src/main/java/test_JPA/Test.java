package test_JPA;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dao.ChiTietHoaDonServices;
import dao.HoaDonDatPhongServices;
import dao.PhongService;
import dao.SanPhamService;
import dao.TempDatPhongServices;
import dao.impl.ChiTietHoaDon_dao_impl;
import dao.impl.HoaDonDatPhongImpl;
import dao.impl.PhongImpl;
import dao.impl.SanPhamImpl;
import dao.impl.TempDatPhongImpl;
import entity.Phong;
import entity.SanPham;
import entity.TempDatPhong;

public class Test {
	public static void main(String[] args) throws RemoteException {
//		EntityManager em;
//		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		PhongService  phongService = new PhongImpl();
		phongService.getallPhongs().forEach(System.out::println);
	}
}
