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
import dao.ChiTietHoaDonServices;
import dao.DangNhapServices;
import dao.KhachHang_dao;
import dao.PhongService;
import dao.ThongKeServices;
import dao.impl.ChiTietDichVu_dao_impl;
import dao.impl.ChiTietHoaDon_dao_impl;
import dao.impl.DangNhap_dao_impl;
import dao.impl.PhongImpl;
import dao.impl.ThongKeImpl;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.KhachHang;
import entity.Phong;
import entity.SanPham;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

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
		
		
//ChiTietDichVuServices chiTietDichVuServices = new ChiTietDichVu_dao_impl();
//		//List<ChiTietDichVu>
//		boolean xoa = chiTietDichVuServices.deleteChiTietDV2("HD2310110002", "SP011", "203");
//		System.out.println(xoa);
		
		
//		ChiTietHoaDonServices chiTietHoaDonServices = new ChiTietHoaDon_dao_impl();
//		double tongTien = chiTietHoaDonServices.tinhSoGioHatTheoNam(Integer.valueOf(2020));
//		System.out.println(tongTien);
		DangNhapServices dangNhapServices = new DangNhap_dao_impl();
		String check = dangNhapServices.getRole( "2001001", "Son2001001");
		
		System.err.println(check);
		
	}
}
