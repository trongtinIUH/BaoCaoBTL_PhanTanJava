package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.KhachHangServices;
import entity.KhachHang;
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
