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
import dao.ChiTietDichVuServices;
import entity.ChiTietDichVu;
import entity.HoaDonDatPhong;
import entity.Phong;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ChiTietDichVu_dao_impl extends UnicastRemoteObject implements ChiTietDichVuServices {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4609465373599378623L;
	private EntityManager em;

	public ChiTietDichVu_dao_impl() throws RemoteException {

		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();

	}

	@Override
	public List<ChiTietDichVu> getAllChiTietDichVu() throws RemoteException {
		String sql = "select * from ChiTietDichVu";
		List<ChiTietDichVu> dsChiTietDichVu = new ArrayList<ChiTietDichVu>();
		return dsChiTietDichVu = em.createNativeQuery(sql, ChiTietDichVu.class).getResultList();

	}

	@Override
	public List<ChiTietDichVu> getChiTietDichVuTheoMaHD(String maHD) {
		String jpql = "SELECT c FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHD";
		return em.createQuery(jpql, ChiTietDichVu.class).setParameter("maHD", maHD).getResultList();
	}

	@Override
	public List<ChiTietDichVu> getChiTietDichVuTheoMaHDVaMaPhong(String maHD, String maPhong) {
		String jpql = "SELECT c FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHD AND c.phong.maPhong = :maPhong";
		return em.createQuery(jpql, ChiTietDichVu.class).setParameter("maHD", maHD).setParameter("maPhong", maPhong)
				.getResultList();
	}

	@Override
	public List<ChiTietDichVu> getChiTietDichVuTheoMaPhong(String maPhong) {
		String jpql = "SELECT c FROM ChiTietDichVu c WHERE c.phong.maPhong = :maPhong";
		return em.createQuery(jpql, ChiTietDichVu.class).setParameter("maPhong", maPhong).getResultList();
	}

	@Override
	public double tinhTongTienDVTheoMaHoaDon(String maHD) {
		String jpql = "SELECT SUM(c.gia * c.soLuong) FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHD GROUP BY c.hoaDon.maHoaDon";
		return em.createQuery(jpql, Double.class).setParameter("maHD", maHD).getSingleResult();
	}

	@Override
	public boolean addChiTietDV(ChiTietDichVu ctdv) {
	    try {
	        em.getTransaction().begin(); // Bắt đầu một transaction
	        em.persist(ctdv); // Lưu đối tượng vào cơ sở dữ liệu
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
	public ChiTietDichVu findChiTietDichVu(String maHoaDon, String maPhong, String maSanPham) {
	    // Tạo câu truy vấn JPQL
	    String jpql = "SELECT c FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHoaDon AND c.phong.maPhong = :maPhong AND c.sanPham.maSanPham = :maSanPham";

	    // Thực hiện truy vấn và trả về kết quả
	    return em.createQuery(jpql, ChiTietDichVu.class)
	              .setParameter("maHoaDon", maHoaDon)
	              .setParameter("maPhong", maPhong)
	              .setParameter("maSanPham", maSanPham)
	              .getSingleResult();
	}
	@Override
	public boolean UpdateChiTietDV(ChiTietDichVu ctdv) {
	    try {
	        em.getTransaction().begin(); // Bắt đầu một transaction
	        ctdv = em.merge(ctdv); // Cập nhật đối tượng và lấy bản sao được quản lý
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
	public boolean deleteChiTietDV(String maSanPham) {
	    try {
	        em.getTransaction().begin(); // Bắt đầu một transaction
	        String jpql = "SELECT c FROM ChiTietDichVu c WHERE c.sanPham.maSanPham = :maSanPham";
	        ChiTietDichVu ctdv = em.createQuery(jpql, ChiTietDichVu.class)
	                                .setParameter("maSanPham", maSanPham)
	                                .getSingleResult();
	        if (ctdv != null) {
	            em.remove(ctdv); // Xóa đối tượng khỏi cơ sở dữ liệu
	            em.getTransaction().commit(); // Commit transaction
	            return true;
	        }
	        em.getTransaction().rollback(); // Rollback transaction nếu không tìm thấy đối tượng
	        return false;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); // Rollback transaction nếu có lỗi
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteChiTietDV2(String maHD, String maSanPham, String maPhong) {
	    try {
	        em.getTransaction().begin(); // Bắt đầu một transaction
	        String jpql = "DELETE FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHD AND c.sanPham.maSanPham = :maSanPham AND c.phong.maPhong = :maPhong";
	        int deletedCount = em.createQuery(jpql)
	                             .setParameter("maHD", maHD)
	                             .setParameter("maSanPham", maSanPham)
	                             .setParameter("maPhong", maPhong)
	                             .executeUpdate();
	        em.getTransaction().commit(); // Commit transaction
	        return deletedCount > 0;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); // Rollback transaction nếu có lỗi
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

}
