package dao.impl;

import dao.TempThanhToan_dao;
import entity.TempThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class TempThanhToanImpl implements TempThanhToan_dao {
    private EntityManager em;

    public TempThanhToanImpl() {
        em = Persistence.createEntityManagerFactory("BaiTapLonPTUD").createEntityManager();
    }

    @Override
    public ArrayList<TempThanhToan> getAllTemp() {
        List<TempThanhToan> result = em.createNamedQuery("TempThanhToan.getAllTemp", TempThanhToan.class).getResultList();
        return new ArrayList<>(result);
    }

    @Override
    public boolean addTemp(TempThanhToan tmp) {
        em.getTransaction().begin();
        em.persist(tmp);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteALLTempThanhToan() {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempThanhToan.deleteALLTempThanhToan").executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }
}