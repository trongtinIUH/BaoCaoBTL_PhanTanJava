package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import dao.ChiTietDichVuServices;
import entity.ChiTietDichVu;
import entity.HoaDonDatPhong;
import entity.Phong;
import entity.SanPham;

public class ChiTietDichVu_dao_impl extends UnicastRemoteObject implements ChiTietDichVuServices {

	public ChiTietDichVu_dao_impl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248955254471555575L;

	@Override
	public ArrayList<ChiTietDichVu> getAllChiTietDichVu() throws RemoteException {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString("maHoaDon")),
						new Phong(rs.getString("maPhong")), new SanPham(rs.getString("maSanPham")),
						rs.getInt("soLuong"), rs.getDouble("gia")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}

	@Override
	public ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaHD(String maHD) throws RemoteException {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu where maHoaDon='" + maHD + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString("maHoaDon")),
						new Phong(rs.getString("maPhong")), new SanPham(rs.getString("maSanPham")),
						rs.getInt("soLuong"), rs.getDouble("gia")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}

	@Override
	public ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaHDVaMaPhong(String maHD, String maPhong)
			throws RemoteException {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu where maHoaDon = '" + maHD + "' and maPhong = '" + maPhong + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString("maHoaDon")),
						new Phong(rs.getString("maPhong")), new SanPham(rs.getString("maSanPham")),
						rs.getInt("soLuong"), rs.getDouble("gia")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}

	@Override
	public ArrayList<ChiTietDichVu> getChiTietDichVuTheoMaPhong(String maPhong) throws RemoteException {
		ArrayList<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietDichVu where maPhong='" + maPhong + "'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietDichVu.add(new ChiTietDichVu(new HoaDonDatPhong(rs.getString("maHoaDon")),
						new Phong(rs.getString("maPhong")), new SanPham(rs.getString("maSanPham")),
						rs.getInt("soLuong"), rs.getDouble("gia")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietDichVu;
	}

	@Override
	public double tinhTongTienDVTheoMaHoaDon(String maHD) throws RemoteException {
		double tongTienDV = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT SUM(gia * soLuong) AS tongTienDV " + "FROM ChiTietDichVu ctdv "
					+ "where ctdv.maHoaDon = '" + maHD + "'" + "GROUP BY ctdv.maHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				tongTienDV = rs.getDouble("tongTienDV");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongTienDV;
	}

	@Override
	public boolean addChiTietDV(ChiTietDichVu ctdv) throws RemoteException {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"insert into ChiTietDichVu(maHoaDon, maSanPham, maPhong, soLuong, gia) values(?,?,?,?,?)");
			stmt.setString(1, ctdv.getHoaDon().getMaHoaDon());
			stmt.setString(2, ctdv.getSanPham().getMaSanPham());
			stmt.setString(3, ctdv.getPhong().getMaPhong());
			stmt.setInt(4, ctdv.getSoLuong());
			stmt.setDouble(5, ctdv.getGia());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	@Override
	public boolean UpdateChiTietDV(ChiTietDichVu cthd) throws RemoteException {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ChiTietDichVu set soLuong=?, giaBan=? where maHoaDon=? and maSanPham=? and maPhong=?");
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setDouble(2, cthd.getGia());
			stmt.setString(3, cthd.getHoaDon().getMaHoaDon());
			stmt.setString(4, cthd.getSanPham().getMaSanPham());
			stmt.setString(5, cthd.getPhong().getMaPhong());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	@Override
	public boolean deleteChiTietDV(String maSanPham) throws RemoteException {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
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
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	@Override
	public boolean deleteChiTietDV2(String maHD, String maSanPham, String maPhong) throws RemoteException {
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
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
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

}
