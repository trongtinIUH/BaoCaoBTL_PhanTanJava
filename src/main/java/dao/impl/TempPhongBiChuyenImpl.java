package dao.impl;

import dao.TempPhongBiChuyen_dao;
import entity.TempPhongBiChuyen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class TempPhongBiChuyenImpl implements TempPhongBiChuyen_dao {
    private EntityManager em;

    public TempPhongBiChuyenImpl() {
        em = Persistence.createEntityManagerFactory("BaiTapLonPTUD").createEntityManager();
    }

    @Override
    public ArrayList<TempPhongBiChuyen> getAllTemp() {
        List<TempPhongBiChuyen> result = em.createNamedQuery("TempPhongBiChuyen.getAllTemp", TempPhongBiChuyen.class).getResultList();
        return new ArrayList<>(result);
    }

    @Override
    public boolean addTemp(TempPhongBiChuyen tmp) {
        em.getTransaction().begin();
        em.persist(tmp);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteALLTempPhongBiChuyen() {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempPhongBiChuyen.deleteALLTempPhongBiChuyen").executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean deleteTempPhongBiChuyen(String maPhong) {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempPhongBiChuyen.deleteTempPhongBiChuyen")
                .setParameter(1, maPhong)
                .executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean updateTempPhongBiChuyen(String maPhongCu, String maPhongMoi) {
        em.getTransaction().begin();
        int updatedCount = em.createNamedQuery("TempPhongBiChuyen.updateTempPhongBiChuyen")
                .setParameter(1, maPhongMoi)
                .setParameter(2, maPhongCu)
                .executeUpdate();
        em.getTransaction().commit();
        return updatedCount > 0;
    }
}