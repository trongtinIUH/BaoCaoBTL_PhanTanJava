package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonDatPhongServices;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDonDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class HoaDonDatPhongImpl extends UnicastRemoteObject implements HoaDonDatPhongServices {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8178500090634837819L;
	private EntityManager em;

	public HoaDonDatPhongImpl() throws RemoteException {
		try {
			em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		} catch (Exception e) {
			System.out.println("Loi: " + e.getMessage());
		}
	}

	@Override
	public ArrayList<HoaDonDatPhong> getAllHoaDonDatPhong() throws RemoteException {
		return (ArrayList<HoaDonDatPhong>) em
				.createNamedQuery("HoaDonDatPhong.getAllHoaDonDatPhong", HoaDonDatPhong.class).getResultList();
	}

	@Override
	public HoaDonDatPhong getHoaDonTheoMaHoaDon(String maHoaDon) throws RemoteException {
	    return em.createNamedQuery("HoaDonDatPhong.getHoaDonTheoMaHoaDon", HoaDonDatPhong.class)
	             .setParameter("maHoaDon", maHoaDon)
	             .getSingleResult();
	}


	@Override
	public String getMaHDTheoMaPhieuDP(String maPhieuDatPhong) throws RemoteException{
	    return em.createNamedQuery("HoaDonDatPhong.getMaHDTheoMaPhieuDP", String.class)
	             .setParameter("maPhieuDatPhong", maPhieuDatPhong)
	             .getSingleResult();
	}

	@Override
	public HoaDonDatPhong getHoaDonDatPhongTheoMaHD(String maHoaDon) throws RemoteException{
	    return em.createNamedQuery("HoaDonDatPhong.getHoaDonDatPhongTheoMaHD", HoaDonDatPhong.class)
	             .setParameter("maHoaDon", maHoaDon)
	             .getSingleResult();
	}

	@Override
	public HoaDonDatPhong getHoaDonDatPhongTheoMaPDP(String maPDP) throws RemoteException{
	    return em.createNamedQuery("HoaDonDatPhong.getHoaDonDatPhongTheoMaPDP", HoaDonDatPhong.class)
	             .setParameter("maPDP", maPDP)
	             .getSingleResult();
	}

	@Override
	public ArrayList<HoaDonDatPhong> getHoaDonDatPhongTheoTenKH(String tenKH) throws RemoteException{
	    return (ArrayList<HoaDonDatPhong>) em
	            .createNamedQuery("HoaDonDatPhong.getHoaDonDatPhongTheoTenKH", HoaDonDatPhong.class)
	            .setParameter("tenKH", tenKH)
	            .getResultList();
	}

	@Override
	public ArrayList<HoaDonDatPhong> getHoaDonDatPhongTheoMaNV(String maNV) throws RemoteException{
	    return (ArrayList<HoaDonDatPhong>) em
	            .createNamedQuery("HoaDonDatPhong.getHoaDonDatPhongTheoMaNV", HoaDonDatPhong.class)
	            .setParameter("maNV", maNV)
	            .getResultList();
	}

	@Override
	public ArrayList<HoaDonDatPhong> getHoaDonTheoNgayLapHD(String ngayLapHD) throws RemoteException{
	    return (ArrayList<HoaDonDatPhong>) em
	            .createNamedQuery("HoaDonDatPhong.getHoaDonTheoNgayLapHD", HoaDonDatPhong.class)
	            .setParameter("ngayLapHD", ngayLapHD)
	            .getResultList();
	}

	@Override
	public ArrayList<HoaDonDatPhong> getHoaDonTheoThang(String thang, int nam) throws RemoteException{
	    return (ArrayList<HoaDonDatPhong>) em
	            .createNamedQuery("HoaDonDatPhong.getHoaDonTheoThang", HoaDonDatPhong.class)
	            .setParameter("thang", thang)
	            .setParameter("nam", nam)
	            .getResultList();
	}

	@Override
	public ArrayList<HoaDonDatPhong> getHoaDonTheoNam(int nam) throws RemoteException{
	    return (ArrayList<HoaDonDatPhong>) em
	            .createNamedQuery("HoaDonDatPhong.getHoaDonTheoNam", HoaDonDatPhong.class)
	            .setParameter("nam", nam)
	            .getResultList();
	}

	@Override
	public boolean updateHoaDon(String maHD, Date ngayLap, Boolean status, String maNV) throws RemoteException{
	    EntityTransaction transaction = em.getTransaction();
	    transaction.begin();
	    try {
	        Query query = em.createNamedQuery("HoaDonDatPhong.updateHoaDon");
	        query.setParameter("maNV", maNV);
	        query.setParameter("ngayLap", ngayLap);
	        query.setParameter("status", status);
	        query.setParameter("maHD", maHD);
	        int updatedRows = query.executeUpdate();
	        transaction.commit();
	        return updatedRows > 0;
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean updateHoaDon2(HoaDonDatPhong hd) throws RemoteException{
	    EntityTransaction transaction = em.getTransaction();
	    transaction.begin();
	    try {
	        Query query = em.createNamedQuery("HoaDonDatPhong.updateHoaDon2");
	        query.setParameter("maKH", hd.getKhachHang().getMaKhachHang());
	        query.setParameter("maNV", hd.getNhanVien().getMaNhanVien());
	        query.setParameter("ngayLap", hd.getNgayLapHoaDon());
	        query.setParameter("status", hd.isTrangThai());
	        query.setParameter("maKM", hd.getKhuyenMai().getMaKhuyenMai());
	        query.setParameter("tienKD", hd.getTienKhachDua());
	        query.setParameter("maHD", hd.getMaHoaDon());
	        int updatedRows = query.executeUpdate();
	        transaction.commit();
	        return updatedRows > 0;
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean updateHoaDon3(HoaDonDatPhong hd) throws RemoteException{
	    EntityTransaction transaction = em.getTransaction();
	    transaction.begin();
	    try {
	        Query query = em.createNamedQuery("HoaDonDatPhong.updateHoaDon3");
	        query.setParameter("maKH", hd.getKhachHang().getMaKhachHang());
	        query.setParameter("maNV", hd.getNhanVien().getMaNhanVien());
	        query.setParameter("ngayLap", hd.getNgayLapHoaDon());
	        query.setParameter("status", hd.isTrangThai());
	        query.setParameter("maKM", hd.getKhuyenMai().getMaKhuyenMai());
	        query.setParameter("tienKD", hd.getTienKhachDua());
	        query.setParameter("maHD", hd.getMaHoaDon());
	        int updatedRows = query.executeUpdate();
	        transaction.commit();
	        return updatedRows > 0;
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean addHoaDonDatPhong(HoaDonDatPhong hd) throws RemoteException{
	    EntityTransaction transaction = em.getTransaction();
	    transaction.begin();
	    try {
	        Query query = em.createNamedQuery("HoaDonDatPhong.addHoaDonDatPhong");
	        query.setParameter("maHD", hd.getMaHoaDon());
	        query.setParameter("maKH", hd.getKhachHang().getMaKhachHang());
	        query.setParameter("maNV", hd.getNhanVien().getMaNhanVien());
	        query.setParameter("ngayLap", hd.getNgayLapHoaDon());
	        query.setParameter("status", hd.isTrangThai());
	        query.setParameter("maKM", hd.getKhuyenMai().getMaKhuyenMai());
	        query.setParameter("tienKD", hd.getTienKhachDua());
	        int updatedRows = query.executeUpdate();
	        transaction.commit();
	        return updatedRows > 0;
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteHoaDon(String maHD) throws RemoteException {
	    EntityTransaction transaction = em.getTransaction();
	    transaction.begin();
	    try {
	        // Tìm tất cả các ChiTietHoaDon liên quan đến HoaDonDatPhong
	        String jpqlChiTietHoaDon = "SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHoaDon = :maHD";
	        List<ChiTietHoaDon> chiTietHoaDons = em.createQuery(jpqlChiTietHoaDon, ChiTietHoaDon.class).setParameter("maHD", maHD).getResultList();

	        // Xóa tất cả các ChiTietHoaDon liên quan
	        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
	            em.remove(chiTietHoaDon);
	        }

	        // Tìm và xóa tất cả các ChiTietDichVu liên quan đến HoaDonDatPhong
	        String jpqlChiTietDichVu = "SELECT c FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHD";
	        List<ChiTietDichVu> chiTietDichVus = em.createQuery(jpqlChiTietDichVu, ChiTietDichVu.class).setParameter("maHD", maHD).getResultList();
	        for (ChiTietDichVu chiTietDichVu : chiTietDichVus) {
	            em.remove(chiTietDichVu);
	        }

	        // Tìm HoaDonDatPhong cần xóa
	        HoaDonDatPhong hoaDonDatPhong = em.find(HoaDonDatPhong.class, maHD);
	        if (hoaDonDatPhong != null) {
	            em.remove(hoaDonDatPhong);
	        }

	        transaction.commit();
	        return true;
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

}
