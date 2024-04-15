package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_dao extends UnicastRemoteObject implements NhanVienDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3956292996006239396L;
	public NhanVien_dao() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<NhanVien> findAllNhanVien() throws RemoteException{
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsNhanVien.add(new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTen"), rs.getString("soDienThoai"), 
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("chucVu"), rs.getString("anhDaiDien")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	public boolean addNhanVien(NhanVien nv) throws RemoteException{
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
			psmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?)");
			psmt.setString(1, nv.getMaNhanVien());
			psmt.setString(2, nv.getHoTen());
			psmt.setString(3, nv.getSoDienThoai());
			psmt.setBoolean(4, nv.isGioiTinh());
			psmt.setDate(5, nv.getNgaySinh());
			psmt.setString(6, nv.getChucVu());
			psmt.setString(7, nv.getAnhDaiDien());
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

	public boolean deleteNhanVien(String maNV) throws RemoteException{
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
			psmt = con.prepareStatement("delete NhanVien where maNhanVien=?");
			psmt.setString(1, maNV);
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

	public boolean updateNhanVien(NhanVien nv) throws RemoteException{
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
			psmt = con.prepareStatement(
					"update NhanVien set hoTen=?, soDienThoai=?, gioiTinh=?, ngaySinh=?, chucVu=?, anhDaiDien=? where maNhanVien=?");
			psmt.setString(1, nv.getHoTen());
			psmt.setString(2, nv.getSoDienThoai());
			psmt.setBoolean(3, nv.isGioiTinh());
			psmt.setDate(4, nv.getNgaySinh());
			psmt.setString(5, nv.getChucVu());
			psmt.setString(6, nv.getAnhDaiDien());
			psmt.setString(7, nv.getMaNhanVien());
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

	public NhanVien findByID(String maNV) throws RemoteException{
		NhanVien nv = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien where maNhanVien = '" + maNV + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTen"), rs.getString("soDienThoai"), 
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("chucVu"), rs.getString("anhDaiDien"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}
	
	public ArrayList<NhanVien> findByName(String tenNV)  throws RemoteException{
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM NhanVien WHERE hoTen LIKE N'%" + tenNV + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsNhanVien.add(new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTen"), rs.getString("soDienThoai"), 
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("chucVu"), rs.getString("anhDaiDien")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	// lấy tên nhân viên 
	public NhanVien findNhanVienToLogin(String maNhanVien) throws RemoteException{
		NhanVien nhanVien = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhanVien where maNhanVien = N'"+maNhanVien+"'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			if(rs.next()) {
				String hoTen = rs.getString("hoTen");
				String chucVu = rs.getString("chucVu");
				nhanVien = new NhanVien(maNhanVien, hoTen, chucVu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nhanVien;
	}
}
