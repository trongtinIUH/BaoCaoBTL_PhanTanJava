package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.NhanVienService;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class NhanVienImpl extends UnicastRemoteObject implements NhanVienService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1372799584794874561L;
	private EntityManager em;
	
	public NhanVienImpl() throws RemoteException {
	        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	public boolean deleteNhanVien(String id) throws RemoteException{
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			NhanVien nv = em.find(NhanVien.class, id);
			em.remove(nv);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean addNhanVien(NhanVien nv) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(nv);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateNhanVien(NhanVien nv) throws RemoteException {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(nv);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<NhanVien> findAllNhanVien() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.findAll", NhanVien.class).getResultList();
	}

	@Override
	public NhanVien findByID(String maNV) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(NhanVien.class, maNV);
	}

	@Override
	public List<NhanVien> findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.findByName", NhanVien.class)
				.setParameter("hoTen", "%"+name+"%")
				.getResultList();
		
	}

	@Override
	public NhanVien findNhanVienToLogin(String maNV) throws RemoteException {
		try {
	  
	        Object[] result = (Object[]) em.createNamedQuery("NhanVien.findNhanVienToLogin")
	                                        .setParameter("maNhanVien", maNV)
	                                        .getSingleResult();

	        return new NhanVien((String) result[0], (String) result[1], (String) result[2]);
	    } catch (NoResultException e) {
	        // Không tìm thấy nhân viên với mã nhân viên đã cho
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
