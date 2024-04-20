package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import dao.impl.ChiTietHoaDon_dao_impl;
import entity.ChiTietHoaDon;
import entity.Phong;
import entity.HoaDonDatPhong;

public class ChiTietHoaDon_dao extends UnicastRemoteObject implements ChiTietHoaDon_dao_impl {
	
	
	public ChiTietHoaDon_dao() throws RemoteException {
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8531935970952225301L;
	

	@Override
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException {
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
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString("maHoaDon")), new Phong(rs.getString("maPhong")),
						rs.getTimestamp("gioNhanPhong"), rs.getTimestamp("gioTraPhong"), rs.getDouble("soGioHat")));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
 @Override
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) throws RemoteException {
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
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString("maHoaDon")), new Phong(rs.getString("maPhong")),
						rs.getTimestamp("gioNhanPhong"), rs.getTimestamp("gioTraPhong"), rs.getDouble("soGioHat")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
 @Override
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaPhong(String maPhong) throws RemoteException {
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
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString("maHoaDon")), new Phong(rs.getString("maPhong")),
						rs.getTimestamp("gioNhanPhong"), rs.getTimestamp("gioTraPhong"), rs.getDouble("soGioHat")));
			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
 @Override
	public double tinhSoGioHatTheoNgay(String date)  throws RemoteException {
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
				soGioHat = rs.getDouble("TongSoGioHat");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}
 @Override
	public double tinhSoGioHatTheoThang(String thang, int nam) throws RemoteException {
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
				soGioHat = rs.getDouble("TongSoGioHat");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}
	     @Override
	public double tinhSoGioHatTheoNam(int nam)  throws RemoteException{
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
				soGioHat = rs.getDouble("TongSoGioHat");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}
	     @Override
	public double tinhSoGioHatTheoNhieuNam(int nambt, int namkt) throws RemoteException {
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
					+ "WHERE YEAR(ngayLapHoaDon) BETWEEN "+nambt+" AND "+namkt;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soGioHat = rs.getDouble("TongSoGioHat");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soGioHat;
	}
             @Override
	public boolean addChiTietHD(ChiTietHoaDon cthd) throws RemoteException {
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
			stmt = con.prepareStatement("INSERT INTO ChiTietHoaDon (maHoaDon, maPhong, gioNhanPhong, gioTraPhong, soGioHat) VALUES (?, ?, ?, ?, ?)");
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
             @Override
	public boolean UpdateChiTietHD(ChiTietHoaDon cthd) 	    throws RemoteException {
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
             @Override
	public boolean UpdateChiTietHD_ChuyenPhong(ChiTietHoaDon cthd) 	    throws RemoteException{
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
             @Override
	public boolean deleteChiTietHD(String maPhong)  throws RemoteException{
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
             @Override
	public ArrayList<ChiTietHoaDon> getCTHDPhongDangSD() throws RemoteException {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM ChiTietHoaDon JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong where Phong.trangThai = N'Dang_su_dung'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				dsChiTietHoaDon.add(new ChiTietHoaDon(new HoaDonDatPhong(rs.getString("maHoaDon")), new Phong(rs.getString("maPhong")),
						rs.getTimestamp("gioNhanPhong"), rs.getTimestamp("gioTraPhong"), rs.getDouble("soGioHat")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
}
