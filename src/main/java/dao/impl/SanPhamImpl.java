package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.SanPhamService;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SanPhamImpl extends UnicastRemoteObject implements SanPhamService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4379741962020664041L;
	EntityManager em;
	public SanPhamImpl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
	
	@Override
	public boolean addSanPham(SanPham sp) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(sp);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateSanPham(SanPham sp) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(sp);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateSLTon(int slTon, String ma) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

	    try {
	        tx.begin();
	        SanPham sanPham = em.find(SanPham.class, ma);
	        if (sanPham != null) {
	            sanPham.setSoLuongTon(slTon);
	            em.persist(sanPham); 
	            tx.commit(); 
	            return true;
	        } else {
	            // Không tìm thấy sản phẩm
	            return false;
	        }
	    } catch (Exception e) {
	        tx.rollback();
	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean deleteSanPham(String maSP) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			SanPham sp = em.find(SanPham.class, maSP);
			em.remove(sp);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<SanPham> getAllSanPhams() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Product.getallSanPhams", SanPham.class).getResultList();
	}

	@Override
	public SanPham getSanPhamTheoMaSP(String maSP) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(SanPham.class, maSP);
	}

	@Override
	public String getLoaiSanPhamTheoMaSP(String maSP) throws RemoteException {
		 try {
		        String loaiSanPham = em.createNamedQuery("SanPham.getLoaiSanPhamByMaSanPham", String.class)
		                                .setParameter("maSanPham", maSP)
		                                .getSingleResult();
		        return loaiSanPham;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
	}

	@Override
	public List<SanPham> getSanPhamTheoTenSanPham(String tenSP) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Product.findByProductName", SanPham.class).setParameter("tenSanPham", "%"+tenSP+"%").getResultList();
	}

	@Override
	public List<SanPham> getSanPhamTheoLoaiSanPham(String loaiSP) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Product.findByProductCategory", SanPham.class).setParameter("loaiSanPham", loaiSP).getResultList();
	}

	@Override
	public List<SanPham> getallSp() throws RemoteException {
		try {
	        List<Object[]> results = em.createNamedQuery("SanPham.getAllSanPhamByIndex", Object[].class).getResultList();
	        
	        List<SanPham> sanPhams = new ArrayList<>();
	        for (Object[] result : results) {
	            SanPham sanPham = new SanPham();
	            sanPham.setTenSanPham((String) result[0]);
	            sanPham.setSoLuongTon((int) result[1]);
	            sanPham.setDonGia((double) result[2]);
	            sanPhams.add(sanPham);
	        }
	        
	        return sanPhams;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public SanPham getSanPhamTheoTenSanPham2(String tenSP) throws RemoteException {
		return em.createNamedQuery("Product.findByProductName", SanPham.class)
				.setParameter("tenSanPham", tenSP)
				.getResultList()
				.stream()
				.findFirst()
				.orElse(null);
	}
	
	
	 
}
