package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.KhuyenMaiServices;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class KhuyenMaiImpl extends UnicastRemoteObject implements KhuyenMaiServices {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4460391886290147799L;
	private EntityManager em;

	public KhuyenMaiImpl() throws RemoteException {
		try {
			em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		} catch (Exception e) {
			System.out.println("Loi: " + e.getMessage());
		}
	}
	
	@Override
	public ArrayList<KhuyenMai> getAllKhuyenMais() throws RemoteException {
		TypedQuery<KhuyenMai> query = em.createNamedQuery("KhuyenMai.getAllKhuyenMais", KhuyenMai.class);
		return (ArrayList<KhuyenMai>) query.getResultList();
	}

	@Override
	public KhuyenMai getKhuyenMaiTheoMaKhuyenMai(String maKhuyenMai) throws RemoteException {
		TypedQuery<KhuyenMai> query = em.createNamedQuery("KhuyenMai.getKhuyenMaiTheoMaKhuyenMai", KhuyenMai.class);
		query.setParameter("maKhuyenMai", maKhuyenMai);
		return query.getSingleResult();
	}

	@Override
	public ArrayList<KhuyenMai> getKhuyenMaiTheoTenKhuyenMai(String tenKhuyenMai) throws RemoteException {
		TypedQuery<KhuyenMai> query = em.createNamedQuery("KhuyenMai.getKhuyenMaiTheoTenKhuyenMai", KhuyenMai.class);
		query.setParameter("tenKhuyenMai", tenKhuyenMai);
		return (ArrayList<KhuyenMai>) query.getResultList();
	}
	
	@Override
	public KhuyenMai getKhuyenMaiTheoNgayBatDauKM(String ngayBatDau) throws RemoteException {
		TypedQuery<KhuyenMai> query = em.createNamedQuery("KhuyenMai.getKhuyenMaiTheoNgayBatDauKM", KhuyenMai.class);
		query.setParameter("ngayBatDau", ngayBatDau);
		return query.getSingleResult();
	}
	
	@Override
	public float getPhanTramKhuyenMaiTheoMaKM(String maKM) throws RemoteException {
	    if (maKM == null) {
	        return 0.0f;
	    } else {
	        TypedQuery<Float> query = em.createNamedQuery("KhuyenMai.getPhanTramKhuyenMaiTheoMaKM", Float.class);
	        query.setParameter("maKM", maKM);
	        List<Float> results = query.getResultList();
	        return results.isEmpty() ? 0.0f : results.get(0);
	    }
	}

	@Override
	public boolean addKhuyenMai(KhuyenMai km) throws RemoteException {
		try {
			em.getTransaction().begin();
			em.persist(km);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateKhuyenMai(KhuyenMai km) throws RemoteException {
		try {
			em.getTransaction().begin();
			em.merge(km);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteKhuyenMai(String maKM) throws RemoteException {
		try {
			em.getTransaction().begin();
			KhuyenMai km = em.find(KhuyenMai.class, maKM);
			if (km != null) {
				em.remove(km);
				em.getTransaction().commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}
}
