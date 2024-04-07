package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPham_dao {
	public ArrayList<SanPham> getallSanPhams(){
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from SanPham";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsSanPham.add(new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSanPham;
	}
	
	public SanPham getSanPhamTheoMaSP(String maSP) {
		SanPham dt = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from SanPham where maSanPham = '" + maSP + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dt = new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dt;
	}
	
	public String getLoaiSanPhamTheoMaSP(String maSP) {
		String loaiSP = "";
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select loaiSanPham from SanPham where maSanPham = '" + maSP + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				loaiSP = rs.getString("loaiSanPham");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loaiSP;
	}
	
	public SanPham getSanPhamTheoTen(String tenSP) {
		SanPham dt = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from SanPham where tenSanPham = N'" + tenSP + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dt = new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dt;
	}
	

	public ArrayList<SanPham> getSanPhamTheoTenSanPham(String tenSP) {
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from SanPham where tenSanPham = N'" + tenSP + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsSanPham.add(new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSanPham;
	}
	
	public ArrayList<SanPham> getSanPhamTheoLoaiSanPham(String loaiSP) {
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from SanPham where loaiSanPham = N'" + loaiSP + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsSanPham.add(new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSanPham;
	}
	
	public ArrayList<SanPham> getAllSanPhamTheoTenSP(String tenSP) {
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from SanPham where tenSanPham like N'%"+tenSP+"%'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				dsSP.add(new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsSP;
	}
	
	public ArrayList<SanPham> getAllSanPhamTheoMaSP(String maSP) {
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from SanPham where maSanPham = '"+maSP+"'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				dsSP.add(new SanPham(rs.getString(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsSP;
	}
	
	public boolean addSanPham(SanPham sp) {
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
			psmt = con.prepareStatement("insert into SanPham values(?,?,?,?,?,?,?,?)");
			psmt.setString(1, sp.getMaSanPham());
			psmt.setString(2, sp.getTenSanPham());
			psmt.setDate(3, sp.getNgaySanXuat());
			psmt.setString(4, sp.getloaiSanPham());
			psmt.setDouble(5, sp.getDonGia());
			psmt.setString(6, sp.getDonViTinh());
			psmt.setInt(7, sp.getSoLuongTon());
			psmt.setString(8, sp.getHinhAnh());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean updateSanPham(SanPham sp) {
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
			psmt = con.prepareStatement("update SanPham set tenSanPham=?, ngaySanXuat=?, loaiSanPham=?, donGiaNhap=?, donViTinh=?, soLuongTon=?, hinhAnh=? where maSanPham=?");
			psmt.setString(1, sp.getTenSanPham());
			psmt.setDate(2, sp.getNgaySanXuat());
			psmt.setString(3, sp.getloaiSanPham());
			psmt.setDouble(4, sp.getDonGia());
			psmt.setString(5, sp.getDonViTinh());
			psmt.setInt(6, sp.getSoLuongTon());
			psmt.setString(7, sp.getHinhAnh());
			psmt.setString(8, sp.getMaSanPham());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		}finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean updateSLTon(int slTon, String ma) {
		try {
			ConnectDB.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Connection con = ConnectDB.getConnection();
		
		 String sql ="update SanPham set soLuongTon = ? where maSanPham = ?";
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, slTon);
			pst.setString(2, ma);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteSanPham(String maSP) {
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
			psmt = con.prepareStatement("delete SanPham where maSanPham=?");
			psmt.setString(1, maSP);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	// hiển thị dịch vụ bên phòng
	
	 public ArrayList<SanPham> getallSp(){
	        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
	        Connection con = null;
	        try {
	            ConnectDB.getInstance();
	            con = ConnectDB.getConnection();
	            String sql = "select maSanPham, tenSanPham, soLuongTon, donGiaNhap from SanPham";
	            Statement stm = con.createStatement();
	            ResultSet rs = stm.executeQuery(sql);
	            while(rs.next()) {
	                dsSanPham.add(new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return dsSanPham;
	    }
}
