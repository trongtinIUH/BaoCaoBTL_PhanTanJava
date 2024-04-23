package dao.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import dao.PhongService;
import entity.Enum_TrangThai;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import utils.DoanhThuLoaiPhong;

public class PhongImpl extends UnicastRemoteObject implements PhongService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7649949616502779665L;
	EntityManager em;
	
	public PhongImpl() throws RemoteException {
		try {
			em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		} catch (Exception e) {
			System.out.println("Loi: " + e.getMessage());
			System.out.println("Cause: " + e.getCause());
		}
	}
	
	@Override
	public boolean addPhong(Phong ph) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(ph);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePhong(Phong ph, String maPhongMoi) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(ph);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deletePhong(String maPhong) throws RemoteException {
	    EntityTransaction tx = em.getTransaction();

	    try {
	        tx.begin();
	        Phong ph = em.find(Phong.class, maPhong);
	        ph.setTrangThai(Enum_TrangThai.Da_xoa);
	        em.merge(ph);
	        tx.commit();
	        return true;
	    } catch (Exception e) {
	        tx.rollback();
	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public List<Phong> getallPhongs() throws RemoteException {
		return em.createNamedQuery("Phong.getallPhongs", Phong.class).getResultList();
	}

	@Override
	public Phong getPhongTheoMaPhong(String maPhong) throws RemoteException {
		return em.find(Phong.class, maPhong);
	}

	@Override
	public List<Phong> getPhongTheoMaLoaiPhong(String maLoaiPhong) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTheoMaLoaiPhong", Phong.class)
				.setParameter("maLoaiPhong", maLoaiPhong)
				.getResultList();
	}

	@Override
	public List<Phong> getPhongTheoTrangThai(String trangThai) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTheoTrangThai",Phong.class)
				.setParameter("trangThai", trangThai)
				.getResultList();
	}

	@Override
	public List<Phong> getPhongTheoTenLoaiPhongVaTrangThai(String tenLoaiPhong, String trangThai) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTheoTenLoaiPhongVaTrangThai", Phong.class)
				.setParameter("tenLoaiPhong", tenLoaiPhong)
				.setParameter("trangThai", trangThai)
				.getResultList();
	}

	@Override
	public List<Phong> getPhongTheoSucChua(String sucChua) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTheoSuChua",Phong.class)
				.setParameter("sucChua", sucChua)
				.getResultList();
	}

	@Override
	public List<Phong> getPhongTheoTenLoaiPhong(String tenLoaiPhong) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTheoTenLoaiPhong",Phong.class)
				.setParameter("tenLoaiPhong", tenLoaiPhong)
				.getResultList();
	}

	@Override
	public List<Phong> getPhongTheoMaCTHD(String maHoaDon) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTheoMaCTHD", Phong.class)
				.setParameter("maHoaDon", maHoaDon)
				.getResultList();
	}

	@Override
	public List<Phong> laydsPhongMoi() throws RemoteException {
		return em.createNamedQuery("Phong.laydsPhongMoi", Phong.class).getResultList();
	}

	@Override
	public List<Phong> getPhongTKTheoTrangThai(String trangThai, int soNguoi) throws RemoteException {
		Enum_TrangThai enumTrangThai = Enum_TrangThai.valueOf(trangThai);
		return em.createNamedQuery("Phong.getPhongTKTheoTrangThai", Phong.class)
				.setParameter("trangThai", enumTrangThai)
				.setParameter("soNguoi", soNguoi)
				.getResultList();
	}

	@Override
	public List<Phong> getPhongTKTheoTenLoaiPhong(String tenLoaiPhong, int soNguoi) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTKTheoTenLoaiPhong",Phong.class)
				.setParameter("tenLoaiPhong", tenLoaiPhong)
				.setParameter("soNguoi", soNguoi)
				.getResultList();
	}
	
	@Override
	public List<Phong> getPhongTKTheoTenLoaiPhongVaTrangThai(String tenLoaiPhong, String trangThai, int soNguoi)
	        throws RemoteException {
	    Enum_TrangThai enumTrangThai = Enum_TrangThai.valueOf(trangThai);
	    return em.createNamedQuery("Phong.getPhongTKTheoTenLoaiPhongVaTrangThai",Phong.class)
	            .setParameter("tenLoaiPhong", tenLoaiPhong)
	            .setParameter("trangThai", enumTrangThai)
	            .setParameter("soNguoi", soNguoi)
	            .getResultList();
	}

	@Override
	public List<Phong> getPhongTKTheoSoNguoiHat(int soNguoi) throws RemoteException {
		return em.createNamedQuery("Phong.getPhongTKTheoSoNguoiHat",Phong.class)
				.setParameter("soNguoi", soNguoi)
				.getResultList();
	}

	@Override
	public double tinhTongTienPhongTheoMaHoaDon(String maHD) throws RemoteException {
		TypedQuery<Double> query = em.createNamedQuery("Phong.tinhTongTienPhongTheoMaHoaDon", Double.class);
        query.setParameter("maHoaDon", maHD);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; 
        }
	}

	@SuppressWarnings("unchecked")
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNgay(String ngay) {
	    DoanhThuLoaiPhong dtlp = null;
	    try {
	    	String sql = "DECLARE @ngayNhap DATE = :ngay " +
		            "SELECT " +
		            " @ngayNhap AS ngayLapHoaDon, " +
		            " 0 AS TongTienPhongThuong, " +
		            " 0 AS TongTienPhongVIP " +
		            "WHERE NOT EXISTS (" +
		            " SELECT * FROM HoaDonDatPhong " +
		            " WHERE ngayLapHoaDon = @ngayNhap " +
		            ") " +
		            "UNION ALL " +
		            "SELECT " +
		            " ngayLapHoaDon, " +
		            " SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, " +
		            " SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP " +
		            "FROM HoaDonDatPhong HDDP " +
		            "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon " +
		            "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong " +
		            "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong " +
		            "WHERE HDDP.ngayLapHoaDon = @ngayNhap " +
		            "GROUP BY HDDP.ngayLapHoaDon";

	    	List<Object[]> results = em.createNativeQuery(sql)
	                .setParameter("ngay", ngay)
	                .getResultList();

	            for (Object[] row : results) {
	                Date date = (Date) row[0];
	                dtlp = new DoanhThuLoaiPhong(date, (Double) row[1], (Double) row[2]);
	            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dtlp;
	}

	@SuppressWarnings("unchecked")
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoThang(String thang, int nam) {
	    DoanhThuLoaiPhong dtlp = null;
	    try {
	        String sql = "DECLARE @thang INT = :thang, @nam INT = :nam " +
	            "SELECT " +
	            "  FORMAT(DATEFROMPARTS(@nam, @thang, 1), 'yyyy-MM') AS ThangNam, " +
	            "  0 AS TongTienPhongThuong, " +
	            "  0 AS TongTienPhongVIP " +
	            "WHERE NOT EXISTS (" +
	            "  SELECT * " +
	            "  FROM HoaDonDatPhong " +
	            "  WHERE MONTH(ngayLapHoaDon) = @thang " +
	            "    AND YEAR(ngayLapHoaDon) = @nam" +
	            ")" +
	            "UNION ALL " +
	            "SELECT " +
	            "  FORMAT(ngayLapHoaDon,'yyyy-MM') AS ThangNam, " +
	            "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, " +
	            "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP " +
	            "FROM HoaDonDatPhong HDDP " +
	            "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon " +
	            "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong " +
	            "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong " +
	            "WHERE MONTH(ngayLapHoaDon) = @thang " +
	            "  AND YEAR(ngayLapHoaDon) = @nam " +
	            "GROUP BY FORMAT(ngayLapHoaDon,'yyyy-MM')";

	        List<Object[]> results = em.createNativeQuery(sql)
	            .setParameter("thang", thang)
	            .setParameter("nam", nam)
	            .getResultList();

	        for (Object[] row : results) {
	            dtlp = new DoanhThuLoaiPhong((Double) row[1], (Double) row[2]);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dtlp;
	}
	

	@SuppressWarnings("unchecked")
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNam(int nam) {
	    DoanhThuLoaiPhong dtlp = null;
	    try {
	        String sql = "DECLARE @nam INT = :nam " +
	            "SELECT " +
	            "FORMAT(DATEFROMPARTS(@nam, 1, 1), 'yyyy') AS Nam,  " +
	            "0 AS TongTienPhongThuong, " +
	            "0 AS TongTienPhongVIP " +
	            "WHERE NOT EXISTS ( " +
	            "SELECT * " +
	            "FROM HoaDonDatPhong " +
	            "WHERE YEAR(ngayLapHoaDon) = @nam" +
	            ")" +
	            "UNION ALL " +
	            "SELECT  " +
	            "FORMAT(ngayLapHoaDon,'yyyy') AS Nam, " +
	            "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, " +
	            "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP " +
	            "FROM HoaDonDatPhong HDDP " +
	            "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon " +
	            "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong " +
	            "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong " +
	            "WHERE YEAR(ngayLapHoaDon) = @nam " +
	            "GROUP BY FORMAT(ngayLapHoaDon,'yyyy')";

	        List<Object[]> results = em.createNativeQuery(sql)
	            .setParameter("nam", nam)
	            .getResultList();

	        for (Object[] row : results) {
	            dtlp = new DoanhThuLoaiPhong((Double) row[1], (Double) row[2]);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dtlp;
	}

	@SuppressWarnings("unchecked")
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNhieuNam(int nambt, int namkt) {
	    DoanhThuLoaiPhong dtlp = null;
	    try {
	        String sql = "DECLARE @startYear INT = :startYear, @endYear INT = :endYear " +
	            "SELECT  " +
	            "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, " +
	            "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP " +
	            "FROM HoaDonDatPhong HDDP " +
	            "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon " +
	            "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong " +
	            "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong " +
	            "WHERE YEAR(ngayLapHoaDon) BETWEEN @startYear AND @endYear";

	        List<Object[]> results = em.createNativeQuery(sql)
	            .setParameter("startYear", nambt)
	            .setParameter("endYear", namkt)
	            .getResultList();

	        for (Object[] row : results) {
	            dtlp = new DoanhThuLoaiPhong(((BigDecimal) row[0]).doubleValue(), ((BigDecimal) row[1]).doubleValue());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dtlp;
	}

}
