package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.Phong;
import entity.HoaDonDatPhong;

public class ChiTietHoaDon_dao {
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(2)),
						rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(5)));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon where maHoaDon='" + maHD + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(2)),
						rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaPhong(String maPhong) {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon where maPhong='" + maPhong + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(2)),
						rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(5)));
			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public double tinhSoGioHatTheoNgay(String date) {
		double soGioHat = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT SUM(CTHD.soGioHat) AS TongSoGioHat " + "FROM HoaDonDatPhong HDP "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDP.maHoaDon = CTHD.maHoaDon " + "where ngayLapHoaDon = '"
					+ date + "' " + "GROUP BY ngayLapHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soGioHat = rs.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}

	public double tinhSoGioHatTheoThang(String thang, int nam) {
		double soGioHat = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT " + "FORMAT(ngayLapHoaDon, 'yyyy-MM') AS Thang, "
					+ "SUM(CTHD.soGioHat) AS TongSoGioHat " + "FROM HoaDonDatPhong HDP "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDP.maHoaDon = CTHD.maHoaDon "
					+ "WHERE FORMAT(ngayLapHoaDon, 'yyyy-MM') = '" + nam + "-" + thang + "' "
					+ "GROUP BY FORMAT(ngayLapHoaDon, 'yyyy-MM')";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soGioHat = rs.getDouble(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}
	
	public double tinhSoGioHatTheoNam(int nam) {
		double soGioHat = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT FORMAT(ngayLapHoaDon, 'yyyy') AS Nam, "
					+ "    SUM(CTHD.soGioHat) AS TongSoGioHat  "
					+ "FROM HoaDonDatPhong HDP  "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDP.maHoaDon = CTHD.maHoaDon "
					+ "WHERE FORMAT(ngayLapHoaDon, 'yyyy') = '"+nam+"' "
					+ "GROUP BY FORMAT(ngayLapHoaDon, 'yyyy')";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soGioHat = rs.getDouble(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}
	
	public double tinhSoGioHatTheoNhieuNam(int nambt, int namkt) {
		double soGioHat = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT SUM(CTHD.soGioHat) AS TongSoGioHat "
					+ "FROM HoaDonDatPhong HDP "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDP.maHoaDon = CTHD.maHoaDon "
					+ "WHERE YEAR(ngayLapHoaDon) BETWEEN "+nambt+" AND "+namkt+"";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soGioHat = rs.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}

	public boolean addChiTietHD(ChiTietHoaDon cthd) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?)");
			stmt.setString(1, cthd.getHoaDon().getMaHoaDon());
			stmt.setString(2, cthd.getPhong().getMaPhong());
			stmt.setTimestamp(3, cthd.getGioNhanPhong());
			stmt.setTimestamp(4, cthd.getGioTraPhong());
			stmt.setDouble(5, cthd.getSoGioHat());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	public boolean UpdateChiTietHD(ChiTietHoaDon cthd) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ChiTietHoaDon set gioNhanPhong=?, gioTraPhong=? soGioHat=? where maPhong=? and maHoaDon=?");
			stmt.setTimestamp(1, cthd.getGioNhanPhong());
			stmt.setTimestamp(2, cthd.getGioTraPhong());
			stmt.setDouble(3, cthd.getSoGioHat());
			stmt.setString(4, cthd.getPhong().getMaPhong());
			stmt.setString(5, cthd.getHoaDon().getMaHoaDon());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	public boolean UpdateChiTietHD_ChuyenPhong(ChiTietHoaDon cthd) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ChiTietHoaDon set gioTraPhong=?, soGioHat=? where maPhong=? and maHoaDon=?");
			stmt.setTimestamp(1, cthd.getGioTraPhong());
			stmt.setDouble(2, cthd.getSoGioHat());
			stmt.setString(3, cthd.getPhong().getMaPhong());
			stmt.setString(4, cthd.getHoaDon().getMaHoaDon());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	public boolean deleteChiTietHD(String maPhong) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from ChiTietHoaDon where maPhong = ?");
			stmt.setString(1, maPhong);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	public ArrayList<ChiTietHoaDon> getCTHDPhongDangSD() {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM ChiTietHoaDon JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong where Phong.trangThai = N'Đang_sử_dụng'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(2)),
						rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
}
