package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

public class HoaDonDatPhong_dao {
	public ArrayList<HoaDonDatPhong> getAllHoaDonDatPhong() {
		ArrayList<HoaDonDatPhong> dsHoaDonDatPhong = new ArrayList<HoaDonDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsHoaDonDatPhong.add(new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDonDatPhong;
	}

	public HoaDonDatPhong getHoaDonTheoMaHoaDon(String maHD) {
		HoaDonDatPhong hd = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong where maHoaDon = '" + maHD + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				hd = new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)), new NhanVien(rs.getString(3)),
						rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hd;
	}

	public String getMaHDTheoMaPhieuDP(String maPhieuDP) {
		String maHD = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select maHoaDon from PhieuDatPhong p join ChiTietHoaDon o on gioNhanPhong = ngayGioNhanPhong where p.maPhieu = '"
					+ maPhieuDP + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				maHD = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maHD;
	}

	public HoaDonDatPhong getHoaDonDatPhongTheoMaHD(String maHD) {
		HoaDonDatPhong HoaDonDatPhong = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong where maHoaDon like '%" + maHD + "%'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				HoaDonDatPhong = new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HoaDonDatPhong;
	}

	public HoaDonDatPhong getHoaDonDatPhongTheoMaPDP(String maPDP) {
		HoaDonDatPhong HoaDonDatPhong = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select o.maHoaDon, p.maKhachHang, p.maNhanVien, hd.ngayLapHoaDon, hd.trangThai, "
					+ "maKhuyenMai, tienKhachDua  from PhieuDatPhong p join ChiTietHoaDon o on p.maPhong = o.maPhong "
					+ "join HoaDonDatPhong hd on hd.maKhachHang = p.maKhachHang where maPhieu = '" + maPDP + "'"
					+ " AND FORMAT(p.ngayGioNhanPhong, 'yyyy-MM-dd HH:mm') = FORMAT(o.gioNhanPhong, 'yyyy-MM-dd HH:mm')";

			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				HoaDonDatPhong = new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HoaDonDatPhong;
	}

	public ArrayList<HoaDonDatPhong> getHoaDonDatPhongTheoTenKH(String tenKH) {
		ArrayList<HoaDonDatPhong> dsHoaDonDatPhong = new ArrayList<HoaDonDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select maHoaDon, hd.maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua "
					+ "from HoaDonDatPhong hd join KhachHang kh on kh.maKhachHang = hd.maKhachHang "
					+ "where kh.hoTen like N'%" + tenKH + "%'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsHoaDonDatPhong.add(new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDonDatPhong;
	}

	public ArrayList<HoaDonDatPhong> getHoaDonDatPhongTheoMaNV(String maNV) {
		ArrayList<HoaDonDatPhong> dsHoaDonDatPhong = new ArrayList<HoaDonDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong where maNhanVien = '" + maNV + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsHoaDonDatPhong.add(new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDonDatPhong;
	}

	public ArrayList<HoaDonDatPhong> getHoaDonTheoNgayLapHD(String ngayLapHD) {
		ArrayList<HoaDonDatPhong> dsHoaDonDatPhong = new ArrayList<HoaDonDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong where ngayLapHoaDon = '" + ngayLapHD + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsHoaDonDatPhong.add(new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDonDatPhong;
	}

	public ArrayList<HoaDonDatPhong> getHoaDonTheoThang(String thang, int nam) {
		ArrayList<HoaDonDatPhong> dsHoaDonDatPhong = new ArrayList<HoaDonDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong " + "where MONTH(ngayLapHoaDon) = '" + thang
					+ "' AND YEAR(ngayLapHoaDon) = '" + nam + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsHoaDonDatPhong.add(new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDonDatPhong;
	}

	public ArrayList<HoaDonDatPhong> getHoaDonTheoNam(int nam) {
		ArrayList<HoaDonDatPhong> dsHoaDonDatPhong = new ArrayList<HoaDonDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDatPhong " + "where YEAR(ngayLapHoaDon) = '" + nam + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsHoaDonDatPhong.add(new HoaDonDatPhong(rs.getString(1), new KhachHang(rs.getString(2)),
						new NhanVien(rs.getString(3)), rs.getDate(4), rs.getBoolean(5),
						new KhuyenMai(rs.getString(6) != null ? rs.getString(6) : "NULL"), rs.getDouble(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDonDatPhong;
	}

	public boolean updateHoaDon(String maHD, Date ngayLap, Boolean status, String maNV) {
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
			psmt = con.prepareStatement(
					"update HoaDonDatPhong set maNhanVien=?, ngayLapHoaDon=?, trangThai=? where maHoaDon=?");
			psmt.setString(1, maNV);
			psmt.setDate(2, (java.sql.Date) ngayLap);
			psmt.setBoolean(3, status);
			psmt.setString(4, maHD);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}

	public boolean updateHoaDon2(HoaDonDatPhong hd) {
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
			psmt = con.prepareStatement(
					"update HoaDonDatPhong set maKhachHang=?, maNhanVien=?, ngayLapHoaDon=?, trangThai=?, tienKhachDua=? where maHoaDon=?");
			psmt.setString(1, hd.getKhachHang().getMaKhachHang());
			psmt.setString(2, hd.getNhanVien().getMaNhanVien());
			psmt.setDate(3, (java.sql.Date) hd.getNgayLapHoaDon());
			psmt.setBoolean(4, hd.isTrangThai());
			psmt.setDouble(5, hd.getTienKhachDua());
			psmt.setString(6, hd.getMaHoaDon());
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

	public boolean updateHoaDon3(HoaDonDatPhong hd) {
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
			psmt = con.prepareStatement(
					"update HoaDonDatPhong set maKhachHang=?, maNhanVien=?, ngayLapHoaDon=?, trangThai=?, maKhuyenMai=?, tienKhachDua=? where maHoaDon=?");
			psmt.setString(1, hd.getKhachHang().getMaKhachHang());
			psmt.setString(2, hd.getNhanVien().getMaNhanVien());
			psmt.setDate(3, (java.sql.Date) hd.getNgayLapHoaDon());
			psmt.setBoolean(4, hd.isTrangThai());
			psmt.setString(5, hd.getKhuyenMai().getMaKhuyenMai());
			psmt.setDouble(6, hd.getTienKhachDua());
			psmt.setString(7, hd.getMaHoaDon());
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

	public boolean addHoaDonDatPhong(HoaDonDatPhong hddp) {
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
			psmt = con.prepareStatement("insert into HoaDonDatPhong values(?,?,?,?,?,?,?)");
			psmt.setString(1, hddp.getMaHoaDon());
			psmt.setString(2, hddp.getKhachHang().getMaKhachHang());
			psmt.setString(3, hddp.getNhanVien().getMaNhanVien());
			psmt.setDate(4, hddp.getNgayLapHoaDon());
			psmt.setBoolean(5, hddp.isTrangThai());
			psmt.setString(6, hddp.getKhuyenMai().getMaKhuyenMai());
			psmt.setDouble(7, hddp.getTienKhachDua());
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

	public boolean deleteHoaDon(String maHD) {
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
			psmt = con.prepareStatement("delete HoaDonDatPhong where maHoaDon=?");
			psmt.setString(1, maHD);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
}
