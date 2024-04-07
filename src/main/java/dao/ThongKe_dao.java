package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import utils.ModelThongKe;
import utils.ModelThongKeDTNhieuNam;
import utils.ModelThongKeKH;

public class ThongKe_dao {
	public ArrayList<ModelThongKe> thongKeTheoNam(String yearStart, String yearEnd){
		ArrayList<ModelThongKe> lists = new ArrayList<ModelThongKe>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "DECLARE @namBatDau int = "+yearStart+" "
					+ "DECLARE @namKetThuc int = "+yearEnd+" "
					+ "SELECT "
					+ "    YEAR(ngayLapHoaDon) AS nam,  "
					+ "    SUM(tongTienSauKhuyenMai) AS tongDoanhThu, "
					+ "    SUM(tienPhong) AS tongTienPhong, "
					+ "    SUM(tienDichVu) AS tongTienDichVu "
					+ "FROM "
					+ "("
					+ "    SELECT  "
					+ "    hd.ngayLapHoaDon,  "
					+ "    (ISNULL(tienPhong, 0) + ISNULL(tienDichVu, 0)) * (1 - COALESCE(km.phanTramKhuyenMai, 0) / 100) AS tongTienSauKhuyenMai,  "
					+ "    tienPhong,  "
					+ "    tienDichVu  "
					+ "FROM "
					+ "    HoaDonDatPhong hd  "
					+ "LEFT JOIN  "
					+ "    ("
					+ "        SELECT  "
					+ "            cthd.maHoaDon,  "
					+ "            SUM(lp.donGiaTheoGio * cthd.soGioHat) AS tienPhong  "
					+ "        FROM  "
					+ "            ChiTietHoaDon cthd "
					+ "        LEFT JOIN  "
					+ "            Phong p ON p.maPhong = cthd.maPhong "
					+ "        LEFT JOIN "
					+ "            LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong "
					+ "        GROUP BY "
					+ "            cthd.maHoaDon "
					+ "    ) cthd ON cthd.maHoaDon = hd.maHoaDon "
					+ "LEFT JOIN "
					+ "    ("
					+ "        SELECT  "
					+ "            ctdv.maHoaDon,  "
					+ "            SUM(ctdv.giaBan * ctdv.soLuong) AS tienDichVu  "
					+ "        FROM  "
					+ "            ChiTietDichVu ctdv "
					+ "        GROUP BY  "
					+ "            ctdv.maHoaDon "
					+ "    ) ctdv ON ctdv.maHoaDon = hd.maHoaDon  "
					+ "LEFT JOIN "
					+ "    KhuyenMai km ON km.maKhuyenMai = hd.maKhuyenMai "
					+ ") AS KQ "
					+ "WHERE  "
					+ "    YEAR(ngayLapHoaDon) BETWEEN @namBatDau AND @namKetThuc  "
					+ "GROUP BY  "
					+ "    YEAR(ngayLapHoaDon)";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				lists.add(new ModelThongKe(rs.getString(1) + "", rs.getDouble(2), rs.getDouble(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<ModelThongKeDTNhieuNam> thongKeTheoNhieuNam(int yearStart, int yearEnd){
		ArrayList<ModelThongKeDTNhieuNam> lists = new ArrayList<ModelThongKeDTNhieuNam>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "DECLARE @namBatDau int = "+yearStart+""
					+ "DECLARE @namKetThuc int = "+yearEnd+" "
					+ "SELECT "
					+ "    YEAR(ngayLapHoaDon) AS nam, "
					+ "    COUNT(DISTINCT maHoaDon) AS tongSoHoaDon,"
					+ "    SUM(tongTienSauKhuyenMai) AS tongDoanhThu, "
					+ "    SUM(tienPhongThuong) AS tongDoanhThuPhongThuong,"
					+ "    SUM(tienPhongVIP) AS tongDoanhThuPhongVIP,"
					+ "    SUM(tienPhong) AS tongTienPhong,"
					+ "    SUM(tienDichVu) AS tongTienDichVu,"
					+ "    SUM(soGioHat) AS tongSoGioHat "
					+ "FROM "
					+ "("
					+ "    SELECT "
					+ "        hd.ngayLapHoaDon, "
					+ "        hd.maHoaDon, "
					+ "        (ISNULL(tienPhong, 0) + ISNULL(tienDichVu, 0)) * (1 - COALESCE(km.phanTramKhuyenMai, 0) / 100) AS tongTienSauKhuyenMai, "
					+ "        tienPhongThuong, "
					+ "        tienPhongVIP, "
					+ "        tienPhong, "
					+ "        tienDichVu, "
					+ "        soGioHat "
					+ "    FROM  "
					+ "        HoaDonDatPhong hd  "
					+ "    LEFT JOIN "
					+ "        ("
					+ "            SELECT  "
					+ "                cthd.maHoaDon,  "
					+ "                SUM(lp.donGiaTheoGio * cthd.soGioHat) AS tienPhong, "
					+ "                SUM(CASE WHEN lp.maLoaiPhong LIKE 'PT%' THEN lp.donGiaTheoGio * cthd.soGioHat ELSE 0 END) AS tienPhongThuong, "
					+ "                SUM(CASE WHEN lp.maLoaiPhong LIKE 'PV%' THEN lp.donGiaTheoGio * cthd.soGioHat ELSE 0 END) AS tienPhongVIP, "
					+ "                SUM(cthd.soGioHat) AS soGioHat "
					+ "            FROM  "
					+ "                ChiTietHoaDon cthd "
					+ "            LEFT JOIN  "
					+ "                Phong p ON p.maPhong = cthd.maPhong  "
					+ "            LEFT JOIN  "
					+ "                LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong  "
					+ "            GROUP BY  "
					+ "                cthd.maHoaDon "
					+ "        ) cthd ON cthd.maHoaDon = hd.maHoaDon "
					+ "    LEFT JOIN  "
					+ "        ( "
					+ "            SELECT  "
					+ "                ctdv.maHoaDon,  "
					+ "                SUM(ctdv.giaBan * ctdv.soLuong) AS tienDichVu  "
					+ "            FROM "
					+ "                ChiTietDichVu ctdv "
					+ "            GROUP BY "
					+ "                ctdv.maHoaDon "
					+ "        ) ctdv ON ctdv.maHoaDon = hd.maHoaDon "
					+ "    LEFT JOIN  "
					+ "        KhuyenMai km ON km.maKhuyenMai = hd.maKhuyenMai "
					+ ") AS KQ  "
					+ "WHERE  "
					+ "    YEAR(ngayLapHoaDon) BETWEEN @namBatDau AND @namKetThuc  "
					+ "GROUP BY "
					+ "    YEAR(ngayLapHoaDon)";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				lists.add(new ModelThongKeDTNhieuNam(rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<ModelThongKe> updateCboYear(){
		ArrayList<ModelThongKe> yearLists = new ArrayList<ModelThongKe>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select YEAR(ngayLapHoaDon) as nam "
					+ "from HoaDonDatPhong "
					+ "group by YEAR(ngayLapHoaDon)";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				yearLists.add(new ModelThongKe(rs.getString(1) + ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return yearLists;
	}
	
	public ArrayList<ModelThongKe> updateCboMonth(){
		ArrayList<ModelThongKe> lists = new ArrayList<ModelThongKe>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select MONTH(ngayLapHoaDon) as thang "
					+ "from HoaDonDatPhong "
					+ "group by Month(ngayLapHoaDon)";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ModelThongKe modelTK = new ModelThongKe();
				modelTK.setMonth(rs.getString(1));
				lists.add(modelTK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhat() {
		ArrayList<ModelThongKeKH> lists = new ArrayList<ModelThongKeKH>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT TOP 10 "
					+ "kh.maKhachHang, "
					+ "kh.hoTen, "
					+ "kh.soDienThoai, "
					+ "kh.gioiTinh, "
					+ "SUM(cthd.soGioHat) AS TongSoGioHat "
					+ "FROM "
					+ "KhachHang kh "
					+ "JOIN "
					+ "HoaDonDatPhong hddp ON kh.maKhachHang = hddp.maKhachHang "
					+ "JOIN "
					+ "ChiTietHoaDon cthd ON hddp.maHoaDon = cthd.maHoaDon "
					+ "GROUP BY "
					+ "kh.maKhachHang, "
					+ "kh.hoTen, "
					+ "kh.soDienThoai, "
					+ "kh.gioiTinh "
					+ "ORDER BY "
					+ "TongSoGioHat DESC";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ModelThongKeKH modelTK = new ModelThongKeKH(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5));
				lists.add(modelTK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhatTheoNam(String year) {
		ArrayList<ModelThongKeKH> lists = new ArrayList<ModelThongKeKH>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT TOP 10 "
					+ "kh.maKhachHang, "
					+ "kh.hoTen, "
					+ "kh.soDienThoai, "
					+ "kh.gioiTinh, "
					+ "SUM(cthd.soGioHat) as TongSoGioHat "
					+ "FROM "
					+ "KhachHang kh "
					+ "JOIN "
					+ "HoaDonDatPhong hddp ON kh.maKhachHang = hddp.maKhachHang "
					+ "JOIN "
					+ "ChiTietHoaDon cthd ON hddp.maHoaDon = cthd.maHoaDon "
					+ "where YEAR(hddp.ngayLapHoaDon) = "+year+" "
					+ "GROUP BY  "
					+ "YEAR(hddp.ngayLapHoaDon), "
					+ "kh.maKhachHang, "
					+ "kh.hoTen, "
					+ "kh.soDienThoai, "
					+ "kh.gioiTinh "
					+ "ORDER BY "
					+ "TongSoGioHat DESC";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ModelThongKeKH modelTK = new ModelThongKeKH(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5));
				lists.add(modelTK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhatTheoThang(String year, String month) {
		ArrayList<ModelThongKeKH> lists = new ArrayList<ModelThongKeKH>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT TOP 10 "
					+ "kh.maKhachHang, "
					+ "kh.hoTen, "
					+ "kh.soDienThoai, "
					+ "kh.gioiTinh, "
					+ "SUM(cthd.soGioHat) as TongSoGioHat "
					+ "FROM "
					+ "KhachHang kh "
					+ "JOIN "
					+ "HoaDonDatPhong hddp ON kh.maKhachHang = hddp.maKhachHang "
					+ "JOIN "
					+ "ChiTietHoaDon cthd ON hddp.maHoaDon = cthd.maHoaDon "
					+ "where YEAR(hddp.ngayLapHoaDon) = "+year+" and MONTH(hddp.ngayLapHoaDon) = "+month+""
					+ "GROUP BY  "
					+ "YEAR(hddp.ngayLapHoaDon), "
					+ "MONTH(hddp.ngayLapHoaDon), "
					+ "kh.maKhachHang, "
					+ "kh.hoTen, "
					+ "kh.soDienThoai, "
					+ "kh.gioiTinh "
					+ "ORDER BY "
					+ "TongSoGioHat DESC";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ModelThongKeKH modelTK = new ModelThongKeKH(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5));
				lists.add(modelTK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lists;
	}
}
