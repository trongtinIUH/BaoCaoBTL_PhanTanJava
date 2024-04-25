package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import app.App_Karaoke4T;
import dao.ChiTietDichVuServices;
import dao.ChiTietHoaDonServices;
import dao.DangNhapServices;
import dao.HoaDonDatPhongServices;
import dao.KhachHangServices;
import dao.KhuyenMaiServices;
import dao.LoaiPhongServices;
import dao.NhanVienService;
import dao.PhieuDatPhongService;
import dao.PhongService;
import dao.SanPhamService;
import dao.TempDatPhongServices;
import dao.TempPhongBiChuyenServices;
import dao.TempThanhToanServices;
import dao.ThongKeServices;
import dao.impl.ChiTietDichVu_dao_impl;
import dao.impl.ChiTietHoaDon_dao_impl;
import dao.impl.DangNhap_dao_impl;
import dao.impl.HoaDonDatPhongImpl;
import dao.impl.KhachHangImpl;
import dao.impl.KhuyenMaiImpl;
import dao.impl.LoaiPhongImpl;
import dao.impl.NhanVienImpl;
import dao.impl.PhieuDatPhongImpl;
import dao.impl.PhongImpl;
import dao.impl.SanPhamImpl;
import dao.impl.TempDatPhongImpl;
import dao.impl.TempPhongBiChuyenImpl;
import dao.impl.TempThanhToanImpl;
import dao.impl.ThongKeImpl;



public class Server {
	private static final String URL = "rmi://192.168.43.157:7878/";
	public static void main(String[] args) throws NamingException {
		try {
			ChiTietDichVuServices chiTietDichVuServices = new ChiTietDichVu_dao_impl();
			ChiTietHoaDonServices chiTietHoaDonServices = new ChiTietHoaDon_dao_impl();
			DangNhapServices dangNhapServices = new DangNhap_dao_impl();
			HoaDonDatPhongServices hoaDonDatPhongServices = new HoaDonDatPhongImpl();
			KhachHangServices khachHangServices = new KhachHangImpl();
			KhuyenMaiServices khuyenMaiServices = new KhuyenMaiImpl();
			LoaiPhongServices loaiPhongServices = new LoaiPhongImpl();
			NhanVienService nhanVienServices = new NhanVienImpl();
			PhieuDatPhongService phieuDatPhongServices = new PhieuDatPhongImpl();
			PhongService phongServices = new PhongImpl();
			SanPhamService sanPhamServices = new SanPhamImpl();
			TempDatPhongServices tempDatPhongServices = new TempDatPhongImpl();
			TempPhongBiChuyenServices tempPhongBiChuyenServices = new TempPhongBiChuyenImpl();
			TempThanhToanServices tempThanhToanServices = new TempThanhToanImpl();
			ThongKeServices thongKeServices = new ThongKeImpl();
			Context context = new InitialContext();
			LocateRegistry.createRegistry(7878);
			
			context.bind(URL + "chiTietDichVuServices", chiTietDichVuServices);
			context.bind(URL + "chiTietHoaDonServices", chiTietHoaDonServices);
			context.bind(URL + "dangNhapServices", dangNhapServices);
			context.bind(URL + "hoaDonDatPhongServices", hoaDonDatPhongServices);
			context.bind(URL + "khachHangServices", khachHangServices);
			context.bind(URL + "khuyenMaiServices", khuyenMaiServices);
			context.bind(URL + "loaiPhongServices", loaiPhongServices);
			context.bind(URL + "nhanVienServices", nhanVienServices);
			context.bind(URL + "phieuDatPhongServices", phieuDatPhongServices);
			context.bind(URL + "phongServices", phongServices);
			context.bind(URL + "sanPhamServices", sanPhamServices);
			context.bind(URL + "tempDatPhongServices", tempDatPhongServices);
			context.bind(URL + "tempPhongBiChuyenServices", tempPhongBiChuyenServices);
			context.bind(URL + "tempThanhToanServices", tempThanhToanServices);
			context.bind(URL + "thongKeServices", thongKeServices);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server is running...");

	}
}