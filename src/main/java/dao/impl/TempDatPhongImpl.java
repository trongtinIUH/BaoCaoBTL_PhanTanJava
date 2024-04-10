package dao.impl;

import dao.TempDatPhong_dao;
import entity.TempDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class TempDatPhongImpl implements TempDatPhong_dao {
    private EntityManager em;

    public TempDatPhongImpl() {
        em = Persistence.createEntityManagerFactory("BaiTapLonPTUD").createEntityManager();
    }

    @Override
    public ArrayList<TempDatPhong> getAllTemp() {
        List<TempDatPhong> result = em.createNamedQuery("TempDatPhong.getAllTemp", TempDatPhong.class).getResultList();
        return new ArrayList<>(result);
    }

    @Override
    public boolean addTemp(TempDatPhong tempDatPhong) {
        em.getTransaction().begin();
        em.persist(tempDatPhong);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteALLTempDatPhong() {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempDatPhong.deleteALLTempDatPhong").executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean deleteTempDP(String maDP) {
        em.getTransaction().begin();
        int deletedCount = em.createNamedQuery("TempDatPhong.deleteTempDP")
                .setParameter(1, maDP)
                .executeUpdate();
        em.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean updateTempDP(String maPhong, int soNguoi) {
        em.getTransaction().begin();
        int updatedCount = em.createNamedQuery("TempDatPhong.getTempDP")
                .setParameter(1, soNguoi)
                .setParameter(2, maPhong)
                .executeUpdate();
        em.getTransaction().commit();
        return updatedCount > 0;
    }
}