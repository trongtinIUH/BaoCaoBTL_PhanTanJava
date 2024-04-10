package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Enum_TrangThai;
import entity.LoaiPhong;
import entity.Phong;
import utils.DoanhThuLoaiPhong;

public class Phong_dao {
	public ArrayList<Phong> getallPhongs() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
		
		//------------
		
	}

	public Phong getPhongTheoMaPhong(String maPhong) {
		Phong ph = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong where maPhong = '" + maPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				ph = new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ph;
	}

	public ArrayList<Phong> getPhongTheoMaLoaiPhong(String maLoaiPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong where maLoaiPhong = '" + maLoaiPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}

	public ArrayList<Phong> getPhongTheoTrangThai(String trangThai) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong where trangThai = N'" + trangThai + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}
	
	public ArrayList<Phong> getPhongTheoLoaiPhongVaTrangThai(String loaiPhong, String trangThai) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong join LoaiPhong on Phong.maLoaiPhong = LoaiPhong.maLoaiPhong where tenLoaiPhong = N'"
					+ loaiPhong + "' and trangThai = N'" + trangThai + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}

	public double tinhTongTienPhongTheoMaHoaDon(String maHD) {
		double tongTien = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT SUM(lp.donGiaTheoGio * cthd.soGioHat) AS tongTien "
					+ "FROM ChiTietHoaDon cthd JOIN Phong p ON p.maPhong = cthd.maPhong "
					+ "JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong " + "where cthd.maHoaDon = '" + maHD + "'"
					+ "GROUP BY cthd.maHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				tongTien = rs.getDouble("tongTien");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tongTien;
	}

	public ArrayList<Phong> getPhongTheoSucChua(String sucChua) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Phong.maPhong, Phong.maLoaiPhong, Phong.trangThai FROM Phong JOIN LoaiPhong ON Phong.maLoaiPhong = LoaiPhong.maLoaiPhong WHERE LoaiPhong.sucChua = '"
					+ sucChua + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;

	}
	
	public ArrayList<Phong> getPhongTheoLoaiPhong(String loaiPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong join LoaiPhong on Phong.maLoaiPhong = LoaiPhong.maLoaiPhong where tenLoaiPhong = N'"+ loaiPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}
	
	public ArrayList<Phong> getPhongTheoMaCTHD(String maHoaDon) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong INNER JOIN ChiTietHoaDon ON Phong.maPhong = ChiTietHoaDon.maPhong where maHoaDon='" + maHoaDon + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNgay(String ngay) {
		DoanhThuLoaiPhong dtlp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "DECLARE @ngayNhap DATE = '" + ngay + "' " + "SELECT " + "  @ngayNhap AS ngayLapHoaDon, "
					+ "  0 AS TongTienPhongThuong, " + "  0 AS TongTienPhongVIP " + "WHERE NOT EXISTS ("
					+ "  SELECT * FROM HoaDonDatPhong " + "  WHERE ngayLapHoaDon = @ngayNhap " + ") " + "UNION ALL "
					+ "SELECT " + "  ngayLapHoaDon, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP "
					+ "FROM HoaDonDatPhong HDDP " + "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon "
					+ "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong "
					+ "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong "
					+ "WHERE HDDP.ngayLapHoaDon = @ngayNhap " + "GROUP BY HDDP.ngayLapHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dtlp = new DoanhThuLoaiPhong(rs.getDate("ngayLapHoaDon"), rs.getDouble("TongTienPhongThuong"), rs.getDouble("TongTienPhongVIP"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtlp;
	}

	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoThang(String thang, int nam) {
		DoanhThuLoaiPhong dtlp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "DECLARE @thang INT = " + thang + " " + "DECLARE @nam INT = " + nam + " " + "SELECT "
					+ "  FORMAT(DATEFROMPARTS(@nam, @thang, 1), 'yyyy-MM') AS ThangNam, "
					+ "  0 AS TongTienPhongThuong, " + "  0 AS TongTienPhongVIP " + "WHERE NOT EXISTS (" + "  SELECT * "
					+ "  FROM HoaDonDatPhong " + "  WHERE MONTH(ngayLapHoaDon) = @thang "
					+ "    AND YEAR(ngayLapHoaDon) = @nam" + ")" + "UNION ALL " + "SELECT "
					+ "  FORMAT(ngayLapHoaDon,'yyyy-MM') AS ThangNam, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP "
					+ "FROM HoaDonDatPhong HDDP " + "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon "
					+ "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong "
					+ "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong "
					+ "WHERE MONTH(ngayLapHoaDon) = @thang " + "  AND YEAR(ngayLapHoaDon) = @nam "
					+ "GROUP BY FORMAT(ngayLapHoaDon,'yyyy-MM')";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dtlp = new DoanhThuLoaiPhong(rs.getDouble("TongTienPhongThuong"), rs.getDouble("TongTienPhongVIP"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtlp;
	}
	
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNam(int nam) {
		DoanhThuLoaiPhong dtlp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "DECLARE @nam INT = "+nam+" "
					+ "SELECT "
					+ "FORMAT(DATEFROMPARTS(@nam, 1, 1), 'yyyy') AS Nam,  "
					+ "0 AS TongTienPhongThuong, "
					+ "0 AS TongTienPhongVIP "
					+ "WHERE NOT EXISTS ( "
					+ "SELECT * "
					+ "FROM HoaDonDatPhong "
					+ "WHERE YEAR(ngayLapHoaDon) = @nam"
					+ ")"
					+ "UNION ALL "
					+ "SELECT  "
					+ "FORMAT(ngayLapHoaDon,'yyyy') AS Nam, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP "
					+ "FROM HoaDonDatPhong HDDP "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon "
					+ "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong "
					+ "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong "
					+ "WHERE YEAR(ngayLapHoaDon) = @nam "
					+ "GROUP BY FORMAT(ngayLapHoaDon,'yyyy')";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dtlp = new DoanhThuLoaiPhong(rs.getDouble("TongTienPhongThuong"), rs.getDouble("TongTienPhongVIP"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtlp;
	}
	
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNhieuNam(int nambt, int namkt) {
		DoanhThuLoaiPhong dtlp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "DECLARE @startYear INT = "+nambt+", @endYear INT = "+namkt+" "
					+ "SELECT  "
					+ "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, \r\n"
					+ "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP \r\n"
					+ "FROM HoaDonDatPhong HDDP "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon "
					+ "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong "
					+ "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong "
					+ "WHERE YEAR(ngayLapHoaDon) BETWEEN @startYear AND @endYear";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dtlp = new DoanhThuLoaiPhong(rs.getDouble("TongTienPhongThuong"), rs.getDouble("TongTienPhongVIP"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtlp;
	}

	public boolean addPhong(Phong ph) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
			psmt = con.prepareStatement("insert into Phong values(?,?,?)");
			psmt.setString(1, ph.getMaPhong());
			psmt.setString(2, ph.getLoaiPhong().getMaLoaiPhong());
			psmt.setString(3, ph.getTrangThai().toString());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}

	public boolean updatePhong(Phong ph, String maPhongMoi) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
			psmt = con.prepareStatement("update Phong set trangThai=? where maPhong=?");
			psmt.setString(1, ph.getTrangThai().toString());
			psmt.setString(2, ph.getMaPhong());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean deletePhong(String maPhong) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
			psmt = con.prepareStatement("delete Phong where maPhong=?");
			psmt.setString(1, maPhong);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}

	// tín code
	public ArrayList<Phong> laydsPhongMoi() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Phong.maPhong, LoaiPhong.tenLoaiPhong, LoaiPhong.sucChua, LoaiPhong.donGiaTheoGio, Phong.trangThai "
					+ "FROM Phong " + "JOIN LoaiPhong ON Phong.maLoaiPhong = LoaiPhong.maLoaiPhong "
					+ "WHERE Phong.trangThai = N'Trống'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong phong = new Phong();
				phong.setMaPhong(rs.getString("maPhong"));
				phong.getLoaiPhong().setTenLoaiPhong(rs.getString("tenLoaiPhong"));
				phong.getLoaiPhong().setSucChua(rs.getInt("sucChua"));
				phong.getLoaiPhong().setDonGiaTheoGio(rs.getDouble("donGiaTheoGio"));
				phong.setTrangThai(Enum_TrangThai.valueOf(rs.getString("trangThai")));
				dsPhong.add(phong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	public ArrayList<Phong> getPhongTKTheoTrangThai(String trangThai, int soNguoi) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong join LoaiPhong on Phong.maLoaiPhong = LoaiPhong.maLoaiPhong where trangThai = N'"
					+ trangThai + "' and sucChua >= " + soNguoi;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}

	public ArrayList<Phong> getPhongTKTheoLoaiPhong(String loaiPhong, int soNguoi) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong join LoaiPhong on Phong.maLoaiPhong = LoaiPhong.maLoaiPhong where tenLoaiPhong = N'"
					+ loaiPhong + "' and sucChua >= " + soNguoi;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}

	public ArrayList<Phong> getPhongTKTheoLoaiPhongVaTrangThai(String loaiPhong, String trangThai, int soNguoi) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong join LoaiPhong on Phong.maLoaiPhong = LoaiPhong.maLoaiPhong where tenLoaiPhong = N'"
					+ loaiPhong + "' and sucChua >= " + soNguoi + " and trangThai = N'" + trangThai + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}

	public ArrayList<Phong> getPhongTKTheoSoNguoiHat(int soNguoi) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Phong join LoaiPhong on Phong.maLoaiPhong = LoaiPhong.maLoaiPhong where sucChua >= "
					+ soNguoi;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsPhong.add(new Phong(rs.getString("maPhong"), new LoaiPhong(rs.getString("maLoaiPhong")),
						Enum_TrangThai.valueOf(rs.getString("trangThai"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (dsPhong.isEmpty())
			return null;
		return dsPhong;
	}
}
