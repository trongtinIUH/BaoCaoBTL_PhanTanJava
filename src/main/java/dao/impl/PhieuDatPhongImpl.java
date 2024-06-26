package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import dao.PhieuDatPhongService;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class PhieuDatPhongImpl extends UnicastRemoteObject implements PhieuDatPhongService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5629311621241070109L;
	private EntityManager em;
	public PhieuDatPhongImpl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public boolean addPhieuDatPhong(PhieuDatPhong pdp) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(pdp);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean xoaPhieuDatPhongTheoMa(String maPhong) throws RemoteException {
	    try {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        Query query = em.createNativeQuery("DELETE FROM PhieuDatPhong WHERE maPhong = :maPhong")
	            .setParameter("maPhong", maPhong);
	        int rows = query.executeUpdate();
	        tx.commit();
	        return rows > 0;
	    } catch (Exception e) {
	        System.out.println("Error deleting PhieuDatPhong: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}
	@Override
	public List<PhieuDatPhong> getAllsPhieuDatPhong() throws RemoteException {
		return em.createNamedQuery("PDP.getAllsPhieu", PhieuDatPhong.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuDatPhong> getMaPhongDatTruoc() throws RemoteException {
	    List<PhieuDatPhong> result = new ArrayList<>();
	    try {
	        result = em.createNativeQuery("PhieuDatPhong.getMaPhongDatTruoc", PhieuDatPhong.class).getResultList();
	    } catch (Exception e) {
	        // Log the exception here, if necessary
	        // e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public PhieuDatPhong getPDPDatTruocTheoMaPhong(String maPhong) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPDPDatTruocTheoMaPhong",PhieuDatPhong.class)
				.setParameter("maPhong", maPhong)
				.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public PhieuDatPhong getPhieuDatPhongTheoMaPDP(String maPhieu) throws RemoteException {
		return em.find(PhieuDatPhong.class, maPhieu);
	}

	@Override
	public PhieuDatPhong getPhieuDatPhongPhongCho(String maPhong) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPhieuDatPhongPhongCho",PhieuDatPhong.class)
				.setParameter("maPhong", maPhong).getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<PhieuDatPhong> getPhieuDatPhongTheoMaKH(String maKhachHang) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPhieuDatPhongTheoMaKH",PhieuDatPhong.class)
				.setParameter("maKhachHang", maKhachHang).getResultList();
	}

	@Override
	public List<PhieuDatPhong> getDanhSachPhieuDatPhongTheoMaPhong(String maPhong) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPhieuDatPhongTheoMaPhong",PhieuDatPhong.class)
				.setParameter("maPhong", maPhong).getResultList();
	}

	@Override
	public List<PhieuDatPhong> getPhieuDatPhongInfo() throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPhieuDatPhongInfo",PhieuDatPhong.class).getResultList();
	}

	@Override
	public PhieuDatPhong timThongTinPhieuDatPhongTheoMaPhong(String maPhong) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.timThongTinPhieuDatPhongTheoMaPhong",PhieuDatPhong.class)
				.setParameter("maPhong", maPhong).getResultList().stream().findFirst().orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuDatPhong> getAllsPhieuDatPhong_ChuaThanhToan() {
	    List<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
	    try {
	        String sql = "select maPhieu, maPhong, maNhanVien, maKhachHang, ngayGioDatPhong, ngayGioNhanPhong, soNguoiHat \r\n"
	        		+ "from PhieuDatPhong";
	        List<Object[]> results = em.createNativeQuery(sql).getResultList();
	        for (Object[] row : results) {
	            String maPhieu = row[0].toString();
	            String maHoaDon = "HD" + maPhieu.substring(3);
	            String sqlCheck = "select * from HoaDonDatPhong where maHoaDon = ? and trangThai = 0";
	            List<Object[]> resultsCheck = em.createNativeQuery(sqlCheck).setParameter(1, maHoaDon).getResultList();
	            if (!resultsCheck.isEmpty()) {
	                Phong p = new Phong(row[1].toString());
	                NhanVien nv = new NhanVien(row[2].toString());
	                KhachHang kh = new KhachHang(row[3].toString());
	                LocalDateTime ngayGioDatPhong = ((Timestamp) row[4]).toLocalDateTime();
	                LocalDateTime ngayGioNhanPhong = ((Timestamp) row[5]).toLocalDateTime();
	                dspdp.add(new PhieuDatPhong(maPhieu, p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, (Integer) row[6]));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dspdp;
	}

	@Override
	public List<PhieuDatPhong> getAllsPhieuDatPhong_DangSuDung() throws RemoteException {
		 return em.createNamedQuery("PhieuDatPhong.getAllsPhieuDatPhong_DangSuDung", PhieuDatPhong.class)
                 .getResultList();
	}

	@Override
	public PhieuDatPhong getPhieuDatPhongTheoMaPDP_DangSuDung(String maPhieu) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPhieuDatPhongTheoMaPDP_DangSuDung", PhieuDatPhong.class)
                .setParameter("maPhieu", maPhieu)
                .getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public PhieuDatPhong getPhieuDatPhongTheoMaPhong_TrangThaiCho(String maPhong) throws RemoteException {
		TypedQuery<PhieuDatPhong> query = em.createNamedQuery("PhieuDatPhong.getPhieuDatPhongTheoMaPhong_TrangThaiCho", PhieuDatPhong.class);
        query.setParameter("maPhong", maPhong);
        
        List<PhieuDatPhong> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public List<PhieuDatPhong> getAllsPhieuDatPhong_PhongCho() throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getAllsPhieuDatPhong_PhongCho", PhieuDatPhong.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuDatPhong> getAllsPhieuDatPhong_DaThanhToan() throws RemoteException {
		List<PhieuDatPhong> dspdp = new ArrayList<PhieuDatPhong>();
	    try {
	        String sql = "select maPhieu, maPhong, maNhanVien, maKhachHang, ngayGioDatPhong, ngayGioNhanPhong, soNguoiHat \r\n"
	        		+ "from PhieuDatPhong";
	        List<Object[]> results = em.createNativeQuery(sql).getResultList();
	        for (Object[] row : results) {
	            String maPhieu = row[0].toString();
	            String maHoaDon = "HD" + maPhieu.substring(3);
	            String sqlCheck = "select * from HoaDonDatPhong where maHoaDon = ? and trangThai = 1";
	            List<Object[]> resultsCheck = em.createNativeQuery(sqlCheck).setParameter(1, maHoaDon).getResultList();
	            if (!resultsCheck.isEmpty()) {
	                Phong p = new Phong(row[1].toString());
	                NhanVien nv = new NhanVien(row[2].toString());
	                KhachHang kh = new KhachHang(row[3].toString());
	                LocalDateTime ngayGioDatPhong = ((Timestamp) row[4]).toLocalDateTime();
	                LocalDateTime ngayGioNhanPhong = ((Timestamp) row[5]).toLocalDateTime();
	                dspdp.add(new PhieuDatPhong(maPhieu, p, nv, kh, ngayGioDatPhong, ngayGioNhanPhong, (Integer) row[6]));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dspdp;
	}


	@Override
	public List<PhieuDatPhong> getPDPTheoNgayNhan(LocalDate ngayGioNhanPhong) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPDPTheoNgayNhan", PhieuDatPhong.class)
                .setParameter("ngayGioNhanPhong", ngayGioNhanPhong)
                .getResultList();
	}

	@Override
	public List<PhieuDatPhong> getPDPTheoThangNhan(YearMonth thangNhan) throws RemoteException {
		TypedQuery<PhieuDatPhong> query = em.createNamedQuery("PhieuDatPhong.getPDPTheoThangNhan", PhieuDatPhong.class);
        query.setParameter("year", thangNhan.getYear());
        query.setParameter("month", thangNhan.getMonthValue());
        return query.getResultList();
	}

	@Override
	public List<PhieuDatPhong> getPDPTheoNamNhan(int namNhan) throws RemoteException {
		return em.createNamedQuery("PhieuDatPhong.getPDPTheoNamNhan", PhieuDatPhong.class)
	            .setParameter("namNhan", namNhan)
	            .getResultList();
	}

}
