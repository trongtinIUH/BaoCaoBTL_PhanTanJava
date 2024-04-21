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
import dao.DangNhapServices;
import entity.NhanVien;
import entity.TaiKhoan;

public class DangNhap_dao_impl  extends UnicastRemoteObject implements DangNhapServices{

	
	
	
	public DangNhap_dao_impl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4745325277275024946L;

 @Override
	public ArrayList<TaiKhoan> getAllTaiKhoan() throws RemoteException {
		ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from TaiKhoan";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maTaiKhoan = rs.getString("maTaiKhoan");
				String matKhau = rs.getString("matKhau");
				boolean trangThai= rs.getBoolean("trangThai");
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				String roleName = rs.getString("roleName");
				TaiKhoan tk = new TaiKhoan(maTaiKhoan, matKhau, trangThai, nv, roleName);
				dsTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
 @Override
	//hàm tìm mã nv và mk để tiến hành đăng nhập
	public boolean Timkiem(String maNV, String mk) throws RemoteException {
        boolean found = false;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from TaiKhoan where maTaiKhoan = N'" + maNV + "' and matKhau = N'" + mk + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                found = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }
	
  @Override
	// hàm quên mk sau đó cập nhật lại mk mới 
	public boolean doiMatKhau(String soDienThoai, String matKhauMoi)  throws RemoteException{
	    boolean updated = false;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        // Kiểm tra xem số điện thoại có phù hợp với mã nhân viên không
	        String sqlCheck = "SELECT maNhanVien FROM NhanVien WHERE soDienThoai = ?";
	        PreparedStatement staCheck = con.prepareStatement(sqlCheck);
	        staCheck.setString(1, soDienThoai);
	        ResultSet rsCheck = staCheck.executeQuery();
	        if(rsCheck.next()) {
	            String maNV = rsCheck.getString("maNhanVien");
	            // Nếu phù hợp, cập nhật mật khẩu mới
	            String sqlUpdate = "UPDATE TaiKhoan SET matKhau = ? WHERE maTaiKhoan = ?";
	            PreparedStatement staUpdate = con.prepareStatement(sqlUpdate);
	            staUpdate.setString(1, matKhauMoi);
	            staUpdate.setString(2, maNV);
	            int rowsUpdated = staUpdate.executeUpdate();
	            if(rowsUpdated > 0) {
	                updated = true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return updated;
	}
  @Override
	// kiểm tra sdt có tồn tại trong hệ thống
	public boolean TimkiemSDT(String SDT) throws RemoteException{
		boolean found = false;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhanVien where soDienThoai = N'"+SDT+"'";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			if(rs.next()) {
				found = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}
  @Override
//tìm mk theo manv
	public TaiKhoan LayMatKhauTheoMaNhanVien(String maNhanVien)  throws RemoteException{
	    TaiKhoan taiKhoan = null;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "select TaiKhoan.* from TaiKhoan inner join NhanVien on TaiKhoan.maTaiKhoan = NhanVien.maNhanVien where NhanVien.maNhanVien = '" + maNhanVien + "'";
	        Statement stm = con.createStatement();
	        ResultSet rs = stm.executeQuery(sql);
	        if (rs.next()) {
	            String maTaiKhoan = rs.getString("maTaiKhoan");
	            String matKhau = rs.getString("matKhau");
	            boolean trangThai = rs.getBoolean("trangThai");
	            String roleName = rs.getString("roleName");
	            NhanVien nhanVien = new NhanVien(maNhanVien);
	            taiKhoan = new TaiKhoan(maTaiKhoan, matKhau, trangThai, nhanVien, roleName);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return taiKhoan;
	}
  @Override
//đổi mk theo ma nv
	public boolean doiMatKhauTheoMaNV(String maNhanVien, String matKhauMoi) throws RemoteException {
	    boolean updated = false;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        // Kiểm tra xem mã nhân viên có tồn tại không
	        String sqlCheck = "SELECT maNhanVien FROM NhanVien WHERE maNhanVien = ?";
	        PreparedStatement staCheck = con.prepareStatement(sqlCheck);
	        staCheck.setString(1, maNhanVien);
	        ResultSet rsCheck = staCheck.executeQuery();
	        if(rsCheck.next()) {
	            // Nếu tồn tại, cập nhật mật khẩu mới
	            String sqlUpdate = "UPDATE TaiKhoan SET matKhau = ? WHERE maTaiKhoan = ?";
	            PreparedStatement staUpdate = con.prepareStatement(sqlUpdate);
	            staUpdate.setString(1, matKhauMoi);
	            staUpdate.setString(2, maNhanVien);
	            int rowsUpdated = staUpdate.executeUpdate();
	            if(rowsUpdated > 0) {
	                updated = true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return updated;
	}
  @Override
	//thêm tk và  mk cho nv mới
	public boolean Them_taiKhoan_matKhau(TaiKhoan tk)  throws RemoteException{
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
	        psmt = con.prepareStatement("INSERT INTO TaiKhoan(maTaiKhoan, matKhau, trangThai, roleName, maNhanVien) VALUES(?,?,?,?,?)");
	        psmt.setString(1, tk.getMaTaiKhoan());
	        psmt.setString(2, tk.getMatKhau());
	        psmt.setBoolean(3, tk.isTrangThai());
	        psmt.setString(4, tk.getRoleName());
	        psmt.setString(5, tk.getNhanVien().getMaNhanVien()); // Giả sử rằng bạn có phương thức getNhanVien() trả về đối tượng NhanVien và phương thức getMaNhanVien() trả về mã nhân viên
	        n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

@Override
	public String getRole(String maTaiKhoan, String matkhau) throws RemoteException {
	    String role = null;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "select roleName from TaiKhoan where maTaiKhoan = ? and matkhau = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maTaiKhoan);
	        stmt.setString(2, matkhau);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            role = rs.getString("roleName");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return role;
	}

	




}
