package test_JPA;

import java.rmi.RemoteException;

import dao.ChiTietHoaDonServices;
import dao.impl.ChiTietHoaDon_dao_impl;

public class Test {
	public static void main(String[] args) throws RemoteException {
//		EntityManager em;
//		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		ChiTietHoaDonServices chiTietHoaDonServices = new ChiTietHoaDon_dao_impl();
		
		double result = chiTietHoaDonServices.tinhSoGioHatTheoNgay("2023-10-10");
		System.out.println(result);
	}
}
