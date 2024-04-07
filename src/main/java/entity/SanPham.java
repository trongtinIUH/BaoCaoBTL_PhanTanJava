package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class SanPham implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maSanPham;
	private String tenSanPham;
	private Date ngaySanXuat;
	private String loaiSanPham;
	private double donGia;
	private String donViTinh;
	private int soLuongTon;
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
		// TODO Auto-generated constructor stub
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
