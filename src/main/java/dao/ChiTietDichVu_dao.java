package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDichVu;
import entity.HoaDonDatPhong;
import entity.Phong;
import entity.SanPham;

public class ChiTietDichVu_dao {
	public ArrayList<ChiTietDichVu> getAllChiTietDichVu() {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(2)), new SanPham(rs.getString(3)), rs.getInt(4),  rs.getDouble(5)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}
	
	public ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaHD(String maHD) {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu where maHoaDon='"+ maHD +"'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(3)), new SanPham(rs.getString(2)), rs.getInt(4),  rs.getDouble(5)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}
	
	public ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaHDVaMaPhong(String maHD, String maPhong) {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu where maHoaDon = '" + maHD +"' and maPhong = '" + maPhong + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(3)), new SanPham(rs.getString(2)), rs.getInt(4),  rs.getDouble(5)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}
	
	public ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaPhong(String maPhong) {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu where maPhong='"+ maPhong +"'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString(1)), new Phong(rs.getString(3)), new SanPham(rs.getString(2)), rs.getInt(4),  rs.getDouble(5)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}
	
	public double tinhTongTienDVTheoMaHoaDon(String maHD) {
		double tongTienDV = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT SUM(giaBan * soLuong) AS tongTienDV "
					+ "FROM ChiTietDichVu ctdv "
					+ "where ctdv.maHoaDon = '" + maHD + "'"
					+ "GROUP BY ctdv.maHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				tongTienDV = rs.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tongTienDV;
	}
	
	public boolean addChiTietDV(ChiTietDichVu ctdv) {
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
			stmt = con.prepareStatement("insert into ChiTietDichVu values(?,?,?,?,?)");
			stmt.setString(1, ctdv.getHoaDon().getMaHoaDon());
			stmt.setString(2, ctdv.getSanPham().getMaSanPham());
			stmt.setString(3, ctdv.getPhong().getMaPhong());
			stmt.setInt(4, ctdv.getSoLuong());
			stmt.setDouble(5, ctdv.getGia());
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
	
	public boolean UpdateChiTietDV(ChiTietDichVu cthd) {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update ChiTietDichVu set soLuong=?, giaBan=? where maHoaDon=? and maSanPham=? and maPhong=?");
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setDouble(2, cthd.getGia());
			stmt.setString(3, cthd.getHoaDon().getMaHoaDon());
			stmt.setString(4, cthd.getSanPham().getMaSanPham());
			stmt.setString(5, cthd.getPhong().getMaPhong());
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
	
	public boolean deleteChiTietDV(String maSanPham) {
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
			stmt = con.prepareStatement("delete from ChiTietDichVu where maSanPham = ?");
			stmt.setString(1, maSanPham);
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
	
	public boolean deleteChiTietDV2(String maHD, String maSanPham, String maPhong) {
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
			stmt = con.prepareStatement("delete from ChiTietDichVu where maHoaDon=? and maSanPham=? and maPhong=?");
			stmt.setString(1, maHD);
			stmt.setString(2, maSanPham);
			stmt.setString(3, maPhong);
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
}
