package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;

import connectDB.ConnectDB;

import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class PhieuDatPhong_dao {
	public ArrayList<PhieuDatPhong> getAllsPhieuDatPhong() {
		ArrayList<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(3));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();
				dspdp.add(
						new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dspdp;
	}

	public ArrayList<PhieuDatPhong> getMaPhongDatTruoc() {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where DATEADD(MINUTE, 21, ngayGioNhanPhong) > GETDATE()";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong,
						rs.getInt(7));
				dsPDP.add(pdp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}

	public PhieuDatPhong getPDPDatTruocTheoMaPhong(String maPhong) {
		PhieuDatPhong pdp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT TOP 1 * FROM PhieuDatPhong WHERE maPhong = '" + maPhong
					+ "' ORDER BY ngayGioNhanPhong DESC";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				pdp = new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pdp;
	}

	public PhieuDatPhong getPhieuDatPhongTheoMaPDP(String maPhieu) {
		PhieuDatPhong pdp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where maPhieu = '" + maPhieu + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				pdp = new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pdp;
	}

	public PhieuDatPhong getPhieuDatPhongPhongCho(String maPhong) {
		PhieuDatPhong pdp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where maPhong = '" + maPhong
					+ "' and ngayGioNhanPhong > GETDATE()";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				pdp = new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pdp;
	}

	public ArrayList<PhieuDatPhong> getPhieuDatPhongTheoMaKH(String maKhachHang) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where maKhachHang = '" + maKhachHang + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong,
						rs.getInt(7));
				dsPDP.add(pdp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}

	public ArrayList<PhieuDatPhong> getDSPhieuDatPhongTheoMaKH(String maKhachHang) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where maKhachHang = '" + maKhachHang + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				dsPDP.add(
						new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}

	public ArrayList<PhieuDatPhong> getDanhsachPhieuDatPhongTheoMa(String maPhong) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where maPhong = '" + maPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				dsPDP.add(
						new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}

	public boolean addPhieuDatPhong(PhieuDatPhong pdp) {
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
			psmt = con.prepareStatement("insert into PhieuDatPhong values(?,?,?,?,?,?,?)");
			psmt.setString(1, pdp.getMaPhieu());
			psmt.setString(2, pdp.getPhong().getMaPhong());
			psmt.setString(3, pdp.getNhanVien().getMaNhanVien());
			psmt.setString(4, pdp.getKhachHang().getMaKhachHang());
			psmt.setObject(5, pdp.getNgayGioDatPhong());
			psmt.setObject(6, pdp.getNgayGioNhanPhong());
			psmt.setInt(7, pdp.getSoNguoiHat());
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

	// lấy dữ liệu đưa lên hiển phòng chờ
	public ArrayList<PhieuDatPhong> getPhieuDatPhongInfo() {
		ArrayList<PhieuDatPhong> list = new ArrayList<>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT p.maPhong, lp.tenLoaiPhong, pdp.soNguoiHat, pdp.ngayGioDatPhong, pdp.ngayGioNhanPhong, lp.donGiaTheoGio, kh.hoTen "
					+ "FROM PhieuDatPhong pdp " + "JOIN Phong p ON pdp.maPhong = p.maPhong "
					+ "JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong "
					+ "JOIN KhachHang kh ON pdp.maKhachHang = kh.maKhachHang";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				PhieuDatPhong pdp = new PhieuDatPhong();
				pdp.setMaPhieu(rs.getString("maPhong"));
				pdp.setSoNguoiHat(rs.getInt("soNguoiHat"));

				// Chuyển đổi java.sql.Timestamp sang java.time.LocalDateTime
				LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();

				pdp.setNgayGioDatPhong(ngayGioDatPhong);
				pdp.setNgayGioNhanPhong(ngayGioNhanPhong);

				Phong phong = new Phong(rs.getString("maPhong"));

				LoaiPhong loaiPhong = new LoaiPhong();
				loaiPhong.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
				loaiPhong.setDonGiaTheoGio(rs.getDouble("donGiaTheoGio"));

				phong.setLoaiPhong(loaiPhong);
				pdp.setPhong(phong);

				KhachHang khachHang = new KhachHang();
				khachHang.setHoTen(rs.getString("hoTen"));
				pdp.setKhachHang(khachHang);

				list.add(pdp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PhieuDatPhong timThongTinPhieuDatPhongTheoMaPhong(String maPhong) {
		PhieuDatPhong pdp = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT p.maPhong, lp.tenLoaiPhong, pdp.soNguoiHat, pdp.ngayGioDatPhong, pdp.ngayGioNhanPhong, lp.donGiaTheoGio, kh.hoTen "
					+ "FROM PhieuDatPhong pdp " + "JOIN Phong p ON pdp.maPhong = p.maPhong "
					+ "JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong "
					+ "JOIN KhachHang kh ON pdp.maKhachHang = kh.maKhachHang " + "WHERE p.maPhong = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, maPhong);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Phong phong = new Phong(rs.getString("maPhong"));
				phong.getLoaiPhong().setTenLoaiPhong(rs.getString("tenLoaiPhong"));

				KhachHang kh = new KhachHang();
				kh.setHoTen(rs.getString("hoTen"));

				// Chuyển đổi java.sql.Timestamp sang java.time.LocalDateTime
				LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();

				pdp = new PhieuDatPhong(phong, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt("soNguoiHat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdp;

	}

	// xóa phiếu đặt phòng khi hủy phòng chờ
	public boolean xoaPhieuDatPhongTheoMa(String maPhong) {
		boolean result = false;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "delete from PhieuDatPhong where maPhong = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, maPhong);
			int rows = stm.executeUpdate();
			if (rows > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// tìm pdp chưa thanh toán bên hóa đơn
	public ArrayList<PhieuDatPhong> getAllsPhieuDatPhong_ChuaThanhToan() {
		ArrayList<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maPhieu = rs.getString(1);
				String maHoaDon = "HD" + maPhieu.substring(3);
				String sqlCheck = "select * from HoaDonDatPhong where maHoaDon = ? and trangThai = 0";
				PreparedStatement stmCheck = con.prepareStatement(sqlCheck);
				stmCheck.setString(1, maHoaDon);
				ResultSet rsCheck = stmCheck.executeQuery();
				if (rsCheck.next()) {
					Phong p = new Phong(rs.getString(3));
					NhanVien nv = new NhanVien(rs.getString(3));
					KhachHang kh = new KhachHang(rs.getString(4));
					LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
					LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();
					dspdp.add(new PhieuDatPhong(maPhieu, p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dspdp;
	}

	public ArrayList<PhieuDatPhong> getAllsPhieuDatPhong_DangSuDung() {
		ArrayList<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select pdp.* from PhieuDatPhong pdp " + "join Phong p on pdp.maPhong = p.maPhong "
					+ "where p.trangThai = N'Đang_sử_dụng'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maPhieu = rs.getString(1);
				String maPhong = rs.getString(3);
				Phong p = new Phong(maPhong);
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();
				dspdp.add(new PhieuDatPhong(maPhieu, p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dspdp;
	}

	// tìm 1 phòng dang su dung
	public PhieuDatPhong getPhieuDatPhongTheoMaPDP_DangSuDung(String maPhieu) {
		PhieuDatPhong pdp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select pdp.* from PhieuDatPhong pdp " + "join Phong p on pdp.maPhong = p.maPhong "
					+ "where pdp.maPhong = ? and p.trangThai = N'Đang_sử_dụng'";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, maPhieu);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				pdp = new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdp;
	}

	// get phong trang thai chờ
	public PhieuDatPhong getPhieuDatPhongTheoMaPhong_TrangThaiCho(String maPhong) {
		PhieuDatPhong pdp = null;
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select pdp.* from PhieuDatPhong pdp " + "join Phong p on pdp.maPhong = p.maPhong "
					+ "where pdp.maPhong = ? and p.trangThai = N'Chờ' and pdp.ngayGioDatPhong <> pdp.ngayGioNhanPhong";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, maPhong);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				pdp = new PhieuDatPhong(rs.getString(1), new Phong(maPhong), nv, kh, ngayGioDatPhong, ngayGioNhanPhong,
						rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdp;
	}

	public ArrayList<PhieuDatPhong> getAllsPhieuDatPhong_PhongCho() {
		ArrayList<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select pdp.* from PhieuDatPhong pdp " + "join Phong p on pdp.maPhong = p.maPhong "
					+ "where p.trangThai = N'Chờ'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maPhieu = rs.getString(1);
				String maPhong = rs.getString(3);
				Phong p = new Phong(maPhong);
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();
				dspdp.add(new PhieuDatPhong(maPhieu, p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dspdp;
	}

	// tìm pdp đã thanh toán bên hóa đơn
	public ArrayList<PhieuDatPhong> getAllsPhieuDatPhong_DaThanhToan() {
		ArrayList<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maPhieu = rs.getString(1);
				String maHoaDon = "HD" + maPhieu.substring(3);
				String sqlCheck = "select * from HoaDonDatPhong where maHoaDon = ? and trangThai = 1";
				PreparedStatement stmCheck = con.prepareStatement(sqlCheck);
				stmCheck.setString(1, maHoaDon);
				ResultSet rsCheck = stmCheck.executeQuery();
				if (rsCheck.next()) {
					Phong p = new Phong(rs.getString(3));
					NhanVien nv = new NhanVien(rs.getString(3));
					KhachHang kh = new KhachHang(rs.getString(4));
					LocalDateTime ngayGioDatPhong = rs.getTimestamp("ngayGioDatPhong").toLocalDateTime();
					LocalDateTime ngayGioNhanPhong = rs.getTimestamp("ngayGioNhanPhong").toLocalDateTime();
					dspdp.add(new PhieuDatPhong(maPhieu, p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dspdp;
	}

	public ArrayList<PhieuDatPhong> getDanhsachPhieuDatPhongTheoMaPhong(String maPhong) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where maPhong = '" + maPhong + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngayGioNhanPhong = rs.getTimestamp(6).toLocalDateTime();
				dsPDP.add(
						new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, rs.getInt(7)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}

	public ArrayList<PhieuDatPhong> getPDPTheoNgayNhan(LocalDate ngayGioNhanPhong) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where CAST(ngayGioNhanPhong AS DATE) = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setDate(1, Date.valueOf(ngayGioNhanPhong));
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngaynhan = rs.getTimestamp(6).toLocalDateTime();
				dsPDP.add(new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngaynhan, rs.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPDP;
	}

	public ArrayList<PhieuDatPhong> getPDPTheoThangNhan(YearMonth thangNhan) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where YEAR(ngayGioNhanPhong) = ? and MONTH(ngayGioNhanPhong) = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, thangNhan.getYear());
			stm.setInt(2, thangNhan.getMonthValue());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngaynhan = rs.getTimestamp(6).toLocalDateTime();
				dsPDP.add(new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngaynhan, rs.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPDP;
	}

	public ArrayList<PhieuDatPhong> getPDPTheoNamNhan(int namNhan) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		try {
			ConnectDB.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuDatPhong where YEAR(ngayGioNhanPhong) = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, namNhan);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Phong p = new Phong(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				LocalDateTime ngayGioDatPhong = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime ngaynhan = rs.getTimestamp(6).toLocalDateTime();
				dsPDP.add(new PhieuDatPhong(rs.getString(1), p, nv, kh, ngayGioDatPhong, ngaynhan, rs.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPDP;
	}

}
