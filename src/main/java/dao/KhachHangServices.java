package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.KhachHang;

public interface KhachHangServices extends Remote{
	public ArrayList<KhachHang> getAllKhachHangs() throws RemoteException;

	public KhachHang getKhachHangTheoMaKH(String maKhachHang) throws RemoteException;

	public ArrayList<KhachHang> getKhachHangTheoTenKH(String tenKhachHang) throws RemoteException;

	public KhachHang getKhachHangTheoSDT(String sdt) throws RemoteException;

	public boolean addKhachHang(KhachHang kh) throws RemoteException;

	public boolean updateKhachHang(KhachHang kh) throws RemoteException;

	public boolean deleteKhachHang(String maKH) throws RemoteException;
//	public ArrayList<KhachHang> getallKhachHangs() {
//		ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		try {
//			String sql = "select maKhachHang, hoTen, soDienThoai, gioiTinh from KhachHang";
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				dsKhachHang.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return dsKhachHang;
//	}
//
//	public KhachHang getKhachHangTheoMaKH(String maKhachHang) {
//		KhachHang kh = null;
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		try {
//			String sql = "select maKhachHang, hoTen, soDienThoai, gioiTinh from KhachHang where maKhachHang = '" + maKhachHang + "'";
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return kh;
//	}
//
//	public ArrayList<KhachHang> getKhachHangTheoTenKH(String tenKhachHang) {
//		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		try {
//			String sql = "SELECT maKhachHang, hoTen, soDienThoai, gioiTinh FROM KhachHang WHERE hoTen LIKE N'%" + tenKhachHang + "%'";
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				dskh.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return dskh;
//	}
//
//	public KhachHang getKhachHangTheoSDT(String sdt) {
//		KhachHang kh = null;
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		try {
//			String sql = "select maKhachHang, hoTen, soDienThoai, gioiTinh from KhachHang where soDienThoai = '" + sdt + "'";
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return kh;
//	}
//
//	public boolean addKhachHang(KhachHang kh) {
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement psmt = null;
//		int n = 0;
//		try {
//			psmt = con.prepareStatement("insert into KhachHang values(?,?,?,?)");
//			psmt.setString(1, kh.getMaKhachHang());
//			psmt.setString(2, kh.getHoTen());
//			psmt.setString(3, kh.getSoDienThoai());
//			psmt.setBoolean(4, kh.isGioiTinh());
//			n = psmt.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			try {
//				psmt.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return n > 0;
//	}
//
//	public boolean updateKhachHang(KhachHang kh) {
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement psmt = null;
//		int n = 0;
//		try {
//			psmt = con.prepareStatement("update KhachHang set hoTen=?, soDienThoai=?, gioiTinh=? where maKhachHang=?");
//			psmt.setString(1, kh.getHoTen());
//			psmt.setString(2, kh.getSoDienThoai());
//			psmt.setBoolean(3, kh.isGioiTinh());
//			psmt.setString(4, kh.getMaKhachHang());
//			n = psmt.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			try {
//				psmt.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return n > 0;
//	}
//
//	public boolean deleteKhachHang(String maKH) {
//		try {
//			ConnectDB.getInstance();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement psmt = null;
//		int n = 0;
//		try {
//			psmt = con.prepareStatement("delete KhachHang where maKhachHang=?");
//			psmt.setString(1, maKH);
//			n = psmt.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			try {
//				psmt.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return n > 0;
//	}
}
