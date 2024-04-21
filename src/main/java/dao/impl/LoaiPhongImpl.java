package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.LoaiPhongServices;
import entity.LoaiPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class LoaiPhongImpl extends UnicastRemoteObject implements LoaiPhongServices{

	/**
	 * 
	 */
	private static final long serialVersionUID = 830121546955709380L;
	private EntityManager em;
	
	public LoaiPhongImpl() throws RemoteException {
		try {
			em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		} catch (Exception e) {
			System.out.println("Loi: " + e.getMessage());
		}
	}

	@Override
	public ArrayList<LoaiPhong> getallLoaiPhongs() throws RemoteException {
	    TypedQuery<LoaiPhong> query = em.createNamedQuery("LoaiPhong.getAllLoaiPhongs", LoaiPhong.class);
	    return (ArrayList<LoaiPhong>) query.getResultList();
	}

	@Override
	public LoaiPhong getLoaiPhongTheoMaLoaiPhong(String maLoaiPhong) throws RemoteException {
	    TypedQuery<LoaiPhong> query = em.createNamedQuery("LoaiPhong.getLoaiPhongTheoMaLoaiPhong", LoaiPhong.class);
	    query.setParameter("maLoaiPhong", maLoaiPhong);
	    return query.getSingleResult();
	}

	@Override
	public ArrayList<LoaiPhong> getLoaiPhongTheoSucChua(int sucChua) throws RemoteException {
	    TypedQuery<LoaiPhong> query = em.createNamedQuery("LoaiPhong.getLoaiPhongTheoSucChua", LoaiPhong.class);
	    query.setParameter("sucChua", sucChua);
	    return (ArrayList<LoaiPhong>) query.getResultList();
	}

	@Override
	public ArrayList<LoaiPhong> getLoaiPhongTheoDonGia(Double donGia) throws RemoteException {
	    TypedQuery<LoaiPhong> query = em.createNamedQuery("LoaiPhong.getLoaiPhongTheoDonGia", LoaiPhong.class);
	    query.setParameter("donGia", donGia);
	    return (ArrayList<LoaiPhong>) query.getResultList();
	}

	@Override
	public String getTenLoaiPhongTheoMaLoaiPhong(String maLoaiPhong) throws RemoteException {
	    TypedQuery<String> query = em.createNamedQuery("LoaiPhong.getTenLoaiPhongTheoMaLoaiPhong", String.class);
	    query.setParameter("maLoaiPhong", maLoaiPhong);
	    return query.getSingleResult();
	}

	@Override
	public int getSucChuaTheoMaLoaiPhong(String maLoaiPhong) throws RemoteException {
	    TypedQuery<Integer> query = em.createNamedQuery("LoaiPhong.getSucChuaTheoMaLoaiPhong", Integer.class);
	    query.setParameter("maLoaiPhong", maLoaiPhong);
	    return query.getSingleResult();
	}

	@Override
	public double getDonGiaTheoMaLoaiPhong(String maLoaiPhong) throws RemoteException {
	    TypedQuery<Double> query = em.createNamedQuery("LoaiPhong.getDonGiaTheoMaLoaiPhong", Double.class);
	    query.setParameter("maLoaiPhong", maLoaiPhong);
	    return query.getSingleResult();
	}

	@Override
	public boolean addLoaiPhong(LoaiPhong loaiPh) throws RemoteException {
	    try {
	        em.getTransaction().begin();
	        em.persist(loaiPh);
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
	public boolean updateLoaiPhong(LoaiPhong loaiPh) throws RemoteException {
	    try {
	        em.getTransaction().begin();
	        em.merge(loaiPh);
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
	public boolean deleteLoaiPhong(String maLoaiPhong) throws RemoteException {
	    try {
	        em.getTransaction().begin();
	        LoaiPhong loaiPh = em.find(LoaiPhong.class, maLoaiPhong);
	        em.remove(loaiPh);
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

}
