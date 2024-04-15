//package dao.impl;
//
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.List;
//
//import dao.NhanVienDao;
//import entity.NhanVien;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
//public class NhanVienImpl extends UnicastRemoteObject implements NhanVienDao{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1372799584794874561L;
//	private EntityManager em;
//	
//	public NhanVienImpl() throws RemoteException {
//		try {
//	        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
//	    } catch (Exception e) {
//	        e.printStackTrace();
////	        throw new RemoteException(e.getMessage());
//	        System.out.println("tttttt");
//	    }
//	}
//
//	public boolean deleteNhanVien(String id) throws RemoteException{
//		EntityTransaction tx = em.getTransaction();
//
//		try {
//			tx.begin();
//			NhanVien nv = em.find(NhanVien.class, id);
//			em.remove(nv);
//			tx.commit();
//			return true;
//		} catch (Exception e) {
//			tx.rollback();
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean addNhanVien(NhanVien nv) throws RemoteException {
//		EntityTransaction tx = em.getTransaction();
//
//		try {
//			tx.begin();
//			em.persist(nv);
//			tx.commit();
//			return true;
//		} catch (Exception e) {
//			tx.rollback();
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean updateNhanVien(NhanVien nv) throws RemoteException {
//		EntityTransaction tx = em.getTransaction();
//
//		try {
//			tx.begin();
//			em.merge(nv);
//			tx.commit();
//			return true;
//		} catch (Exception e) {
//			tx.rollback();
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	@Override
//	public List<NhanVien> findAllNhanVien() throws RemoteException {
//		// TODO Auto-generated method stub
//		return em.createNamedQuery("NhanVien.findAll", NhanVien.class).getResultList();
//	}
//
//	@Override
//	public NhanVien findByID(String maNV) throws RemoteException {
//		// TODO Auto-generated method stub
//		return em.find(NhanVien.class, maNV);
//	}
//
//	@Override
//	public List<NhanVien> findByName(String name) throws RemoteException {
//		// TODO Auto-generated method stub
//		return em.createNamedQuery("NhanVien.findByName", NhanVien.class)
//				.setParameter("hoTen", "%"+name+"%")
//				.getResultList();
//		
//	}
//
//	@Override
//	public NhanVien findNhanVienToLogin(String maNV) throws RemoteException {
//		// TODO Auto-generated method stub
//		// TODO Auto-generated method stub
//				return em.createQuery("NhanVien.findNVToLogin", NhanVien.class)
//						.setParameter("maNhanVien", maNV)
//						.getResultList()
//						.stream()
//						.findFirst()
//						.orElse(null);
//	}
//}
