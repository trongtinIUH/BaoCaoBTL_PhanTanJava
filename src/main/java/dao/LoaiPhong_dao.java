package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_dao {
	public ArrayList<LoaiPhong> getallLoaiPhongs(){
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoaiPhong";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2),rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}
	
	public LoaiPhong getLoaiPhongTheoMaLoaiPhong(String maLoaiPhong) {
		LoaiPhong loaiPh = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoaiPhong where maLoaiPhong = '" + maLoaiPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				loaiPh = new LoaiPhong(rs.getString(1), rs.getString(2),rs.getInt(3), rs.getDouble(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loaiPh;
	}
	
	public ArrayList<LoaiPhong> getLoaiPhongTheoSucChua(int sucChua) {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoaiPhong where sucChua = '" + sucChua + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2),rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}
	
	public ArrayList<LoaiPhong> getLoaiPhongTheoDonGia(Double donGia) {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoaiPhong where donGia = '" + donGia + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2),rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}
	
	public String getTenLoaiPhongTheoMaLoaiPhong(String maLoaiPhong) {
		String tenLoaiPhong = "";
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select tenLoaiPhong from LoaiPhong where maLoaiPhong = '" + maLoaiPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				tenLoaiPhong = rs.getString("tenLoaiPhong");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tenLoaiPhong;
	}
	
	public int getSucChuaTheoMaLoaiPhong(String maLoaiPhong) {
		int sucChua = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select sucChua from LoaiPhong where maLoaiPhong = '" + maLoaiPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				sucChua = rs.getInt("sucChua");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sucChua;
	}
	
	public double getDonGiaTheoMaLoaiPhong(String maLoaiPhong) {
		double donGia = 0;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select donGiaTheoGio from LoaiPhong where maLoaiPhong = '" + maLoaiPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				donGia = rs.getDouble("donGiaTheoGio");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return donGia;
	}
	
	public boolean addLoaiPhong(LoaiPhong loaiPh) {
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
			psmt = con.prepareStatement("insert into LoaiPhong values(?,?,?,?)");
			psmt.setString(1, loaiPh.getMaLoaiPhong());
			psmt.setString(2, loaiPh.getTenLoaiPhong());
			psmt.setInt(3, loaiPh.getSucChua());
			psmt.setDouble(4, loaiPh.getDonGiaTheoGio());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean updateLoaiPhong(LoaiPhong Loaiph) {
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
			psmt = con.prepareStatement("update LoaiPhong set tenLoaiPhong=?, sucChua=?, donGiaTheoGio=? where maLoaiPhong=?");
			psmt.setString(1, Loaiph.getTenLoaiPhong());
			psmt.setInt(2, Loaiph.getSucChua());
			psmt.setDouble(3, Loaiph.getDonGiaTheoGio());
			psmt.setString(4, Loaiph.getMaLoaiPhong());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean deleteLoaiPhong(String maLoaiPhong) {
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
			psmt = con.prepareStatement("delete LoaiPhong where maLoaiPhong=?");
			psmt.setString(1, maLoaiPhong);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
}
