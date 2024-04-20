package test_JPA;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dao.SanPham_dao;
import dao.ThongKeServices;
import dao.impl.ThongKeImpl;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import utils.ModelThongKe;

public class Test {
	public static void main(String[] args) throws RemoteException {
		EntityManager em;
		em = Persistence.createEntityManagerFactory("BaiTapLonPTUD").createEntityManager();
//		Persistence.createEntityManagerFactory("BaiTapLonPTUD");
//		SanPham_dao sanpham_dao = new SanPham_dao();
//		ArrayList<SanPham> list = sanpham_dao.getallSanPhams();
//		for (SanPham sanPham : list) {
//			System.out.println(sanPham.getTenSanPham());
//		}
		
		ThongKeServices thongke = new ThongKeImpl();
		String hello = thongke.hello();
		System.out.println(hello);
		ArrayList<ModelThongKe> list = thongke.thongKeTheoNam("2020", "2023");
		for (ModelThongKe modelThongKe : list) {
            System.out.println(modelThongKe.getYear());
            System.out.println(modelThongKe.getDoanhThuDichVu());
            System.out.println(modelThongKe.getTongDoanhThu());
        }
	}
}
