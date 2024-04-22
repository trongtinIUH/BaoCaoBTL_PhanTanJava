package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.KhachHangServices;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.PhieuDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class KhachHangImpl extends UnicastRemoteObject implements KhachHangServices{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2427093403348630239L;
	private EntityManager em;
	
	public KhachHangImpl() throws RemoteException {
		try {
			em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		} catch (Exception e) {
			System.out.println("Loi: " + e.getMessage());
		}
	}

	@Override
    public KhachHang getKhachHangTheoMaKH(String maKhachHang) throws RemoteException{
        return em.find(KhachHang.class, maKhachHang);
    }


	@Override
    public boolean addKhachHang(KhachHang kh) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.persist(kh);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

	@Override
    public boolean updateKhachHang(KhachHang kh) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.merge(kh);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

	@Override
    public boolean deleteKhachHang(String maKH) throws RemoteException{
        try {
            em.getTransaction().begin();
            
            String maHD = em.createQuery("select hd.maHoaDon from HoaDonDatPhong hd join hd.khachHang kh "
            								+ "where hd.khachHang.maKhachHang = :maKhachHang", String.class)
            		.setParameter("maKhachHang", maKH)
            		.getSingleResult();
            
         // Tìm tất cả các ChiTietHoaDon liên quan đến HoaDonDatPhong
	        String jpqlChiTietHoaDon = "SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHoaDon = :maHD";
	        List<ChiTietHoaDon> chiTietHoaDons = em.createQuery(jpqlChiTietHoaDon, ChiTietHoaDon.class).setParameter("maHD", maHD).getResultList();
	        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
	            em.remove(chiTietHoaDon);
	        }

	        // Tìm và xóa tất cả các ChiTietDichVu liên quan đến HoaDonDatPhong
	        String jpqlChiTietDichVu = "SELECT c FROM ChiTietDichVu c WHERE c.hoaDon.maHoaDon = :maHD";
	        List<ChiTietDichVu> chiTietDichVus = em.createQuery(jpqlChiTietDichVu, ChiTietDichVu.class).setParameter("maHD", maHD).getResultList();
	        for (ChiTietDichVu chiTietDichVu : chiTietDichVus) {
	            em.remove(chiTietDichVu);
	        }
            
            // Tìm tất cả các HoaDon liên quan đến KhachHang này để xóa
	        String jpqlHoaDon = "SELECT hd FROM HoaDonDatPhong hd "
	        					+ "WHERE hd.khachHang.maKhachHang = :maKhachHang";
	        List<HoaDonDatPhong> hoaDons = em.createQuery(jpqlHoaDon, HoaDonDatPhong.class)
	        								 .setParameter("maKhachHang", maKH).getResultList();
	        // Xóa tất cả các HoaDon liên quan
	        for (HoaDonDatPhong hd : hoaDons) {
	            em.remove(hd);
	        }
	        
	        // Tìm tất cả các PhieuDatPhong liên quan đến KhachHang này để xóa
	        String jpqlPDP = "SELECT pdp FROM PhieuDatPhong pdp "
	        					+ "WHERE pdp.khachHang.maKhachHang = :maKhachHang";
	        List<PhieuDatPhong> PDPs = em.createQuery(jpqlPDP, PhieuDatPhong.class)
	        								 .setParameter("maKhachHang", maKH).getResultList();
	        // Xóa tất cả các PhieuDatPhong liên quan
	        for (PhieuDatPhong pdp : PDPs) {
	            em.remove(pdp);
	        }
	                
            KhachHang kh = em.find(KhachHang.class, maKH);
            if (kh != null) {
                em.remove(kh);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

	@Override
	public ArrayList<KhachHang> getAllKhachHangs() throws RemoteException{
        TypedQuery<KhachHang> query = em.createNamedQuery("KhachHang.getAllKhachHangs", KhachHang.class);
        return (ArrayList<KhachHang>) query.getResultList();
    }

	@Override
    public ArrayList<KhachHang> getKhachHangTheoTenKH(String tenKhachHang) throws RemoteException{
        TypedQuery<KhachHang> query = em.createNamedQuery("KhachHang.getKhachHangTheoTenKH", KhachHang.class);
        query.setParameter("tenKhachHang", tenKhachHang);
        return (ArrayList<KhachHang>) query.getResultList();
    }

	@Override
    public KhachHang getKhachHangTheoSDT(String soDienThoai) throws RemoteException{
        TypedQuery<KhachHang> query = em.createNamedQuery("KhachHang.getKhachHangTheoSDT", KhachHang.class);
        query.setParameter("sdt", soDienThoai);
        return query.getSingleResult();
    }
		
}
