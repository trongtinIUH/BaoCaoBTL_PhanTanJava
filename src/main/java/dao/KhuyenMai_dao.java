package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhuyenMai;

public class KhuyenMai_dao {
	public ArrayList<KhuyenMai> getallKhuyenMais() {
		ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhuyenMai";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsKhuyenMai.add(
						new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsKhuyenMai;
	}

	public KhuyenMai getKhuyenMaiTheoMaKhuyenMai(String maKhuyenMai) {
		KhuyenMai km = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhuyenMai where maKhuyenMai = '" + maKhuyenMai + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return km;
	}

	public ArrayList<KhuyenMai> getKhuyenMaiTheoTenKhuyenMai(String tenKhuyenMai) {
		ArrayList<KhuyenMai> dskm = new ArrayList<KhuyenMai>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM KhuyenMai WHERE tenKhuyenMai LIKE N'%" + tenKhuyenMai + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dskm.add(new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskm;
	}

	public KhuyenMai getKhuyenMaiTheoNgayBatDauKM(String ngayBatDau) {
		KhuyenMai km = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhuyenMai where ngayBatDau = '" + ngayBatDau + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return km;
	}

	public float getPhanTramKhuyenMaiTheoMaKM(String maKM) {
		float discount = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select phanTramKhuyenMai from KhuyenMai where maKhuyenMai = '" + maKM + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				discount = rs.getFloat(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return discount;
	}

	public boolean addKhuyenMai(KhuyenMai km) {
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
			psmt = con.prepareStatement("insert into KhuyenMai values(?,?,?,?,?)");
			psmt.setString(1, km.getMaKhuyenMai());
			psmt.setString(2, km.getTenKhuyenMai());
			psmt.setDate(3, km.getNgayBatDau());
			psmt.setDate(4, km.getNgayKetThuc());
			psmt.setFloat(5, km.getPhanTramKhuyenMai());
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

	public boolean updateKhuyenMai(KhuyenMai km) {
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
					"update KhuyenMai set tenKhuyenMai=?, ngayBatDau=?, NgayKetThuc=?, phanTramKhuyenMai=? where maKhuyenMai=?");
			psmt.setString(1, km.getTenKhuyenMai());
			psmt.setDate(2, km.getNgayBatDau());
			psmt.setDate(3, km.getNgayKetThuc());
			psmt.setFloat(4, km.getPhanTramKhuyenMai());
			psmt.setString(5, km.getMaKhuyenMai());
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

	public boolean deleteKhuyenMai(String maKM) {
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
			psmt = con.prepareStatement("delete KhuyenMai where maKhuyenMai=?");
			psmt.setString(1, maKM);
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
}
