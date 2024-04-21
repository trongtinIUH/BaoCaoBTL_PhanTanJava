package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.DangNhapServices;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DangNhap_dao_impl  extends UnicastRemoteObject implements DangNhapServices{

	
	private EntityManager em;
	
	public DangNhap_dao_impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4745325277275024946L;

	@Override
	public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
	    String sql = "SELECT t FROM TaiKhoan t";
	    return em.createQuery(sql, TaiKhoan.class).getResultList();
	}

	@Override
	public boolean Timkiem(String maNV, String mk) throws RemoteException {
	    String jpql = "SELECT t FROM TaiKhoan t WHERE t.maTaiKhoan = :maNV AND t.matKhau = :mk";
	    List<TaiKhoan> result = em.createQuery(jpql, TaiKhoan.class)
	                               .setParameter("maNV", maNV)
	                               .setParameter("mk", mk)
	                               .getResultList();
	    return !result.isEmpty();
	}

	@Override
	public boolean doiMatKhau(String maNhanVien, String matKhauMoi)  throws RemoteException{
	    String jpql = "SELECT t FROM TaiKhoan t WHERE t.maTaiKhoan = :maNhanVien";
	    List<TaiKhoan> taiKhoans = em.createQuery(jpql, TaiKhoan.class)
	                                  .setParameter("maNhanVien", maNhanVien)
	                                  .getResultList();
	    if (!taiKhoans.isEmpty()) {
	        TaiKhoan tk = taiKhoans.get(0);
	        em.getTransaction().begin();
	        tk.setMatKhau(matKhauMoi);
	        em.getTransaction().commit();
	        return true;
	    }
	    return false;
	}
	

	@Override
	public boolean TimkiemSDT(String SDT) throws RemoteException{
	    String jpql = "SELECT n FROM NhanVien n WHERE n.soDienThoai = :soDienThoai";
	    List<NhanVien> result = em.createQuery(jpql, NhanVien.class)
	                               .setParameter("soDienThoai", SDT)
	                               .getResultList();
	    return !result.isEmpty();
	}
  
  
	@Override
	public TaiKhoan LayMatKhauTheoMaNhanVien(String maNhanVien)  throws RemoteException{
	    String jpql = "SELECT t FROM TaiKhoan t WHERE t.maTaiKhoan = :maNhanVien";
	    List<TaiKhoan> taiKhoans = em.createQuery(jpql, TaiKhoan.class)
	                                  .setParameter("maNhanVien", maNhanVien)
	                                  .getResultList();
	    return taiKhoans.isEmpty() ? null : taiKhoans.get(0);
	}

	@Override
	public boolean doiMatKhauTheoMaNV(String maNhanVien, String matKhauMoi) throws RemoteException {
	    String jpql = "UPDATE TaiKhoan t SET t.matKhau = :matKhauMoi WHERE t.maTaiKhoan = :maNhanVien";
	    int rowsUpdated = em.createQuery(jpql)
	                         .setParameter("matKhauMoi", matKhauMoi)
	                         .setParameter("maNhanVien", maNhanVien)
	                         .executeUpdate();
	    return rowsUpdated > 0;
	}

	@Override
	public boolean Them_taiKhoan_matKhau(TaiKhoan tk)  throws RemoteException{
	    try {
	        em.getTransaction().begin();
	        em.persist(tk);
	        em.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public String getRole(String maTaiKhoan, String matkhau) throws RemoteException {
	    String jpql = "SELECT t.roleName FROM TaiKhoan t WHERE t.maTaiKhoan = :maTaiKhoan AND t.matKhau = :matkhau";
	    List<String> roles = em.createQuery(jpql, String.class)
	                            .setParameter("maTaiKhoan", maTaiKhoan)
	                            .setParameter("matkhau", matkhau)
	                            .getResultList();
	    return roles.isEmpty() ? null : roles.get(0);
	}
	




}
