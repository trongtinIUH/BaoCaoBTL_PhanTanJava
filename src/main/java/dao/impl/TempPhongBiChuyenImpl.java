package dao.impl;

import dao.TempPhongBiChuyenServices;
import entity.TempPhongBiChuyen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TempPhongBiChuyenImpl extends UnicastRemoteObject implements TempPhongBiChuyenServices {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2062186162577686408L;
	private EntityManager em;

    public TempPhongBiChuyenImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("BaiTapLonPTUD").createEntityManager();
    }

    @Override
    public ArrayList<TempPhongBiChuyen> getAllTemp() throws RemoteException {
        List<TempPhongBiChuyen> result = em.createNamedQuery("TempPhongBiChuyen.getAllTemp", TempPhongBiChuyen.class).getResultList();
        return new ArrayList<>(result);
    }

    @Override
    public boolean addTemp(TempPhongBiChuyen tmp) throws RemoteException {
        em.getTransaction().begin();
        em.persist(tmp);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteALLTempPhongBiChuyen() throws RemoteException {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempPhongBiChuyen.deleteALLTempPhongBiChuyen").executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean deleteTempPhongBiChuyen(String maPhong) throws RemoteException {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempPhongBiChuyen.deleteTempPhongBiChuyen")
                .setParameter(1, maPhong)
                .executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean updateTempPhongBiChuyen(String maPhongCu, String maPhongMoi) throws RemoteException {
        em.getTransaction().begin();
        int updatedCount = em.createNamedQuery("TempPhongBiChuyen.updateTempPhongBiChuyen")
                .setParameter(1, maPhongMoi)
                .setParameter(2, maPhongCu)
                .executeUpdate();
        em.getTransaction().commit();
        return updatedCount > 0;
    }
}