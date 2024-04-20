package dao.impl;

import dao.TempThanhToanServices;
import entity.TempThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TempThanhToanImpl extends UnicastRemoteObject implements TempThanhToanServices {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5736666970816123717L;
	private EntityManager em;

    public TempThanhToanImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public ArrayList<TempThanhToan> getAllTemp() throws RemoteException {
        List<TempThanhToan> result = em.createNamedQuery("TempThanhToan.getAllTemp", TempThanhToan.class).getResultList();
        return new ArrayList<>(result);
    }

    @Override
    public boolean addTemp(TempThanhToan tmp) throws RemoteException {
        em.getTransaction().begin();
        em.persist(tmp);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteALLTempThanhToan() throws RemoteException {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempThanhToan.deleteALLTempThanhToan").executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }
}