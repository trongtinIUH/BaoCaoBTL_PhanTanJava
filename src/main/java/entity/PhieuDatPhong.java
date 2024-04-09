package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class PhieuDatPhong implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "maPhieu", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String maPhieu;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong", columnDefinition = "VARCHAR(10)", nullable = false)
	private Phong phong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien", columnDefinition = "VARCHAR(20)", nullable = false)
	private NhanVien nhanVien;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang", columnDefinition = "VARCHAR(20)", nullable = false)
	private KhachHang khachHang;

	@Column(name = "ngayGioDatPhong", columnDefinition = "DATETIME", nullable = false)
	private LocalDateTime ngayGioDatPhong;

	@Column(name = "ngayGioNhanPhong", columnDefinition = "DATETIME", nullable = false)
	private LocalDateTime ngayGioNhanPhong;

	@Column(name = "soNguoiHat", columnDefinition = "INT", nullable = false)
	private int soNguoiHat;
	public PhieuDatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PhieuDatPhong(Phong phong, KhachHang khachHang, LocalDateTime ngayGioDatPhong, LocalDateTime ngayGioNhanPhong,
			int soNguoiHat) {
		super();
		this.phong = phong;
		this.khachHang = khachHang;
		this.ngayGioDatPhong = ngayGioDatPhong;
		this.ngayGioNhanPhong = ngayGioNhanPhong;
		this.soNguoiHat = soNguoiHat;
	}
	public PhieuDatPhong(String maPhieu, Phong phong, NhanVien nhanVien, KhachHang khachHang, LocalDateTime ngayGioDatPhong,
			LocalDateTime ngayGioNhanPhong, int soNguoiHat) {
		super();
		this.maPhieu = maPhieu;
		this.phong = phong;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayGioDatPhong = ngayGioDatPhong;
		this.ngayGioNhanPhong = ngayGioNhanPhong;
		this.soNguoiHat = soNguoiHat;
	}
	public String getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public LocalDateTime getNgayGioDatPhong() {
		return ngayGioDatPhong;
	}
	public void setNgayGioDatPhong(LocalDateTime ngayGioDatPhong) {
		this.ngayGioDatPhong = ngayGioDatPhong;
	}
	public LocalDateTime getNgayGioNhanPhong() {
		return ngayGioNhanPhong;
	}
	public void setNgayGioNhanPhong(LocalDateTime ngayGioNhanPhong) {
		this.ngayGioNhanPhong = ngayGioNhanPhong;
	}
	public int getSoNguoiHat() {
		return soNguoiHat;
	}
	public void setSoNguoiHat(int soNguoiHat) {
		this.soNguoiHat = soNguoiHat;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatPhong other = (PhieuDatPhong) obj;
		return Objects.equals(maPhieu, other.maPhieu);
	}
	@Override
	public String toString() {
		return String.format(
				"PhieuDatPhong [maPhieu=%s, phong=%s, nhanVien=%s, khachHang=%s, ngayGioDatPhong=%s, ngayGioNhanPhong=%s, soNguoiHat=%s]",
				maPhieu, phong, nhanVien, khachHang, ngayGioDatPhong, ngayGioNhanPhong, soNguoiHat);
	}
	
	
}
