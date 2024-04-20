package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.PhongService;
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
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
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
	public boolean updatePhong(Phong ph, String maPhongMoi) {
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
	public boolean deletePhong(String maPhong) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Phong ph = em.find(Phong.class, maPhong);
			em.remove(ph);
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
		return em.createNamedQuery("Phong.getPhongTKTheoTrangThai", Phong.class)
				.setParameter("trangThai", trangThai)
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
		return em.createNamedQuery("Phong.getPhongTKTheoTenLoaiPhongVaTrangThai",Phong.class)
				.setParameter("tenLoaiPhong", tenLoaiPhong)
				.setParameter("trangThai", trangThai)
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

	@Override
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNgay(String ngay) throws RemoteException {
		return em.createNamedQuery("Phong.tinhTongDoanhThuLoaiPhongTheoNgay", DoanhThuLoaiPhong.class)
				.setParameter("ngay", ngay).getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoThang(String thang, int nam) throws RemoteException {
		try {
	        Query query = em.createNamedQuery("Phong.tinhTongDoanhThuLoaiPhongTheoThang");
	        query.setParameter("thang", Integer.parseInt(thang));
	        query.setParameter("nam", nam);

	        return (DoanhThuLoaiPhong) query.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	

	@Override
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNam(int nam) throws RemoteException {
		TypedQuery<DoanhThuLoaiPhong> query = em.createNamedQuery("DoanhThuLoaiPhong.tinhTongDoanhThuTheoNam", DoanhThuLoaiPhong.class);
        query.setParameter("nam", nam);
        
        List<DoanhThuLoaiPhong> results = query.getResultList();
        
        // Chỉ trả về kết quả nếu có ít nhất một dòng kết quả
        return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public DoanhThuLoaiPhong tinhTongDoanhThuLoaiPhongTheoNhieuNam(int nam_bd, int nam_kt) throws RemoteException {
		TypedQuery<DoanhThuLoaiPhong> query = em.createNamedQuery("Phong.tinhTongDoanhThuLoaiPhongTheoNhieuNam", DoanhThuLoaiPhong.class);
	    query.setParameter("startYear", nam_bd);
	    query.setParameter("endYear", nam_kt);
	    
	    List<DoanhThuLoaiPhong> results = query.getResultList();
	    
	    return results.isEmpty() ? null : results.get(0);
	}

}
