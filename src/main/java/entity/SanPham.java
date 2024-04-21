package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


@Entity
@NamedQueries({
	@NamedQuery(name = "Product.getallSanPhams", query ="select s from SanPham s"),
	@NamedQuery(name = "Product.findByProductName", query ="select s from SanPham s where s.tenSanPham like :tenSanPham"),
	@NamedQuery(name = "Product.findByProductCategory", query ="select s from SanPham s where s.loaiSanPham = :loaiSanPham"),
	@NamedQuery(name = "SanPham.getLoaiSanPhamByMaSanPham", query = "SELECT s.loaiSanPham FROM SanPham s WHERE s.maSanPham = :maSanPham"),
	@NamedQuery( name = "updateSoLuongTon", query = "UPDATE SanPham s SET s.soLuongTon = :soLuongTon WHERE s.maSanPham = :maSanPham"),
	@NamedQuery(name = "Product.getAllSanPhamByIndex", query ="select s.tenSanPham, s.soLuongTon, s.donGia from SanPham s")
})
public class SanPham implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8873778006362133238L;
	/**
	 * 
	 */
	@Id
	@Column(name="maSanPham", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
	private String maSanPham;
	@Column(name = "tenSanPham", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String tenSanPham;
	@Column(name = "ngaySanXuat", columnDefinition = "Date", nullable = false)
	private Date ngaySanXuat;
	@Column(name = "loaiSanPham", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String loaiSanPham;

	@Column(name = "donGia", columnDefinition = "Money", nullable = false)
	private double donGia;
	@Column(name = "donViTinh", columnDefinition = "NVARCHAR(20)", nullable = false)
	private String donViTinh;
	@Column(name = "soLuongTon", columnDefinition = "int", nullable = false)
	private int soLuongTon;
	@Column(name = "hinhAnh", columnDefinition = "NVARCHAR(200)", nullable = false)
	private String hinhAnh;
	
	@OneToMany(mappedBy = "sanPham")
	private Set<ChiTietDichVu> chiTietDichVus = new HashSet<ChiTietDichVu>();
	
	
	public Set<ChiTietDichVu> getChiTietDichVus() {
		return chiTietDichVus;
	}
	public void setChiTietDichVus(Set<ChiTietDichVu> chiTietDichVus) {
		this.chiTietDichVus = chiTietDichVus;
	}
	public SanPham() {
		super();
	}
	public SanPham(String maSanPham, String tenSanPham, Date ngaySanXuat, String loaiSanPham, double donGia,
			String donViTinh, int soLuongTon, String hinhAnh) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.ngaySanXuat = ngaySanXuat;
		this.loaiSanPham = loaiSanPham;
		this.donGia = donGia;
		this.donViTinh = donViTinh;
		this.soLuongTon = soLuongTon;
		this.hinhAnh = hinhAnh;
	}
	
	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	
	public SanPham(String maSanPham, String tenSanPham, int soLuongTon, double donGia) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.donGia = donGia;
		this.soLuongTon = soLuongTon;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(Date ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public String getloaiSanPham() {
		return loaiSanPham;
	}
	public void setloaiSanPham(String loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}
	@Override
	public String toString() {
		return String.format(
				"SanPham [maSanPham=%s, tenSanPham=%s, ngaySanXuat=%s, loaiSanPham=%s, donGia=%s, soLuongTon=%s, hinhAnh=%s, donViTinh=%s]",
				maSanPham, tenSanPham, ngaySanXuat, loaiSanPham, donGia, soLuongTon, hinhAnh, donViTinh);
	}
	
	
}
