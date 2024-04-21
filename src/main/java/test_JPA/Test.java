package test_JPA;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dao.ChiTietDichVuServices;
import dao.KhachHangServices;
import dao.PhongService;
import dao.ThongKeServices;
import dao.impl.ChiTietDichVu_dao_impl;
import dao.impl.HoaDonDatPhongImpl;
import dao.impl.PhongImpl;
import dao.impl.ThongKeImpl;
import entity.ChiTietDichVu;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.Phong;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import utils.ModelThongKe;

public class Test {
	public static void main(String[] args) throws RemoteException {
		EntityManager em;
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
//		Persistence.createEntityManagerFactory("BaiTapLonPTUD");
//		SanPham_dao sanpham_dao = new SanPham_dao();
//		ArrayList<SanPham> list = sanpham_dao.getallSanPhams();
//		for (SanPham sanPham : list) {
//			System.out.println(sanPham.getTenSanPham());
//		}
		
//		ThongKeServices thongke = new ThongKeImpl();
//		String hello = thongke.hello();
//		System.out.println(hello);
//		ArrayList<ModelThongKe> list = thongke.thongKeTheoNam("2020", "2023");
//		for (ModelThongKe modelThongKe : list) {
//            System.out.println(modelThongKe.getYear());
//            System.out.println(modelThongKe.getDoanhThuDichVu());
//            System.out.println(modelThongKe.getTongDoanhThu());
//        }
//		PhongService phongServices = new PhongImpl();
//		List<Phong> list = phongServices.getallPhongs();
//		list.forEach(System.out::println);
////		ChiTietDichVuServices chiTietDichVuServices = new ChiTietDichVu_dao_impl();
//		ArrayList<ChiTietDichVu> listCTDV = chiTietDichVuServices.getAllChiTietDichVu();
//		listCTDV.forEach(System.out::println);
		
		HoaDonDatPhongImpl hoaDonDatPhongImpl = new HoaDonDatPhongImpl();
		HoaDonDatPhong hoaDonDatPhong = hoaDonDatPhongImpl.getHoaDonTheoMaHoaDon("HD2108010001");
	}
}
