package dao.impl;

import dao.TempDatPhongServices;
import entity.TempDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TempDatPhongImpl extends UnicastRemoteObject implements TempDatPhongServices {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7678385181917056336L;
	private EntityManager em;

    public TempDatPhongImpl() throws RemoteException {
       try {
    	   em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
		} catch (Exception e) {
			System.out.println("Loi: " + e.getMessage());
		}
    }

    @Override
    public ArrayList<TempDatPhong> getAllTemp() throws RemoteException {
        List<TempDatPhong> result = em.createNamedQuery("TempDatPhong.getAllTemp", TempDatPhong.class).getResultList();
        return new ArrayList<>(result);
    }

    @Override
    public boolean addTemp(TempDatPhong tempDatPhong) throws RemoteException {
    	EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(tempDatPhong);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
//			e.printStackTrace();
		}

		return false;
    }

    @Override
    public boolean deleteALLTempDatPhong() throws RemoteException {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempDatPhong.deleteALLTempDatPhong").executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean deleteTempDP(String maDP) throws RemoteException {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempDatPhong.deleteTempDP")
                .setParameter(1, maDP)
                .executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean updateTempDP(String maPhong, int soNguoi) throws RemoteException {
        em.getTransaction().begin();
        int updatedCount = em.createNamedQuery("TempDatPhong.updateTempDP")
                .setParameter(1, soNguoi)
                .setParameter(2, maPhong)
                .executeUpdate();
        em.getTransaction().commit();
        return updatedCount > 0;
    }
}