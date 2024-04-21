package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import dao.ChiTietHoaDonServices;
import entity.ChiTietHoaDon;
import entity.Enum_TrangThai;
import entity.HoaDonDatPhong;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ChiTietHoaDon_dao_impl extends UnicastRemoteObject implements ChiTietHoaDonServices {


	
	private EntityManager em;
	public ChiTietHoaDon_dao_impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4623944122801083268L;
	
	@Override
	public List<ChiTietHoaDon> getAllChiTietHoaDon() {
	    String jpql = "SELECT c FROM ChiTietHoaDon c";
	    List<ChiTietHoaDon> dsChiTietHoaDon = em.createQuery(jpql, ChiTietHoaDon.class).getResultList();
	    return dsChiTietHoaDon;
	}

	@Override
	public List<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) {
	    String jpql = "SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHoaDon = :maHD";
	    List<ChiTietHoaDon> dsChiTietHoaDon = em.createQuery(jpql, ChiTietHoaDon.class)
	                                             .setParameter("maHD", maHD)
	                                             .getResultList();
	    return dsChiTietHoaDon;
	}

	@Override
	public List<ChiTietHoaDon> getChiTietHoaDonTheoMaPhong(String maPhong) {
	    String jpql = "SELECT c FROM ChiTietHoaDon c WHERE c.phong.maPhong = :maPhong";
	    List<ChiTietHoaDon> dsChiTietHoaDon = em.createQuery(jpql, ChiTietHoaDon.class)
	                                             .setParameter("maPhong", maPhong)
	                                             .getResultList();
	    return dsChiTietHoaDon;
	}
 
 
	@Override
	public double tinhSoGioHatTheoNgay(String date) {
	    String jpql = "SELECT SUM(c.soGioHat) FROM ChiTietHoaDon c WHERE FUNCTION('DATE', c.ngayLapHoaDon) = :date";
	    Double soGioHat = em.createQuery(jpql, Double.class)
	                         .setParameter("date", date)
	                         .getSingleResult();
	    return soGioHat != null ? soGioHat : 0;
	}

	@Override
	public double tinhSoGioHatTheoThang(String thang, int nam) {
	    String jpql = "SELECT SUM(c.soGioHat) FROM ChiTietHoaDon c WHERE FUNCTION('YEAR', c.ngayLapHoaDon) = :nam AND FUNCTION('MONTH', c.ngayLapHoaDon) = :thang";
	    Double soGioHat = em.createQuery(jpql, Double.class)
	                         .setParameter("nam", nam)
	                         .setParameter("thang", thang)
	                         .getSingleResult();
	    return soGioHat != null ? soGioHat : 0;
	}

	@Override
	public double tinhSoGioHatTheoNam(int nam) {
	    String jpql = "SELECT SUM(c.soGioHat) FROM ChiTietHoaDon c WHERE FUNCTION('YEAR', c.ngayLapHoaDon) = :nam";
	    Double soGioHat = em.createQuery(jpql, Double.class)
	                         .setParameter("nam", nam)
	                         .getSingleResult();
	    return soGioHat != null ? soGioHat : 0;
	}

	@Override
	public double tinhSoGioHatTheoNhieuNam(int nambt, int namkt) {
	    String jpql = "SELECT SUM(c.soGioHat) FROM ChiTietHoaDon c WHERE FUNCTION('YEAR', c.ngayLapHoaDon) BETWEEN :nambt AND :namkt";
	    Double soGioHat = em.createQuery(jpql, Double.class)
	                         .setParameter("nambt", nambt)
	                         .setParameter("namkt", namkt)
	                         .getSingleResult();
	    return soGioHat != null ? soGioHat : 0;
	}

	@Override
	public boolean addChiTietHD(ChiTietHoaDon cthd) {
	    try {
	        em.getTransaction().begin(); // Bắt đầu một transaction
	        em.persist(cthd); // Lưu đối tượng vào database
	        em.getTransaction().commit(); // Commit transaction
	        return true;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); // Rollback transaction nếu có lỗi
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean UpdateChiTietHD(ChiTietHoaDon cthd) {
	    try {
	        em.getTransaction().begin(); // Bắt đầu một transaction
	        cthd = em.merge(cthd); // Cập nhật đối tượng và lấy bản sao được quản lý
	        em.getTransaction().commit(); // Commit transaction
	        return true;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); // Rollback transaction nếu có lỗi
	        }
	        e.printStackTrace();
	        return false;
	    }
	}
             
             
             
	@Override
	public boolean UpdateChiTietHD_ChuyenPhong(ChiTietHoaDon cthd) {
	    try {
	        em.getTransaction().begin();
	        cthd = em.merge(cthd);
	        em.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteChiTietHD(String maPhong) {
	    try {
	        ChiTietHoaDon cthd = em.find(ChiTietHoaDon.class, maPhong);
	        if (cthd != null) {
	            em.getTransaction().begin();
	            em.remove(cthd);
	            em.getTransaction().commit();
	            return true;
	        } else {
	            return false;
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<ChiTietHoaDon> getCTHDPhongDangSD() {
	    try {
	        TypedQuery<ChiTietHoaDon> query = em.createQuery(
	            "SELECT c FROM ChiTietHoaDon c JOIN c.phong p WHERE p.trangThai = :trangThai", 
	            ChiTietHoaDon.class);
	        query.setParameter("trangThai", Enum_TrangThai.Dang_su_dung);
	        List<ChiTietHoaDon> results = query.getResultList();
	        for (ChiTietHoaDon cthd : results) {
	            System.out.println(cthd);
	        }
	        return results;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}
	
}
