package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Phong implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maPhong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maLoaiPhong")
	private LoaiPhong loaiPhong;

//	public enum TrangThai {
//		Trống, Chờ, Đang_sử_dụng, Đang_sửa_chữa
//	}
	@Enumerated(EnumType.STRING)
	private Enum_TrangThai trangThai;
	
	
	@OneToMany(mappedBy = "phong")
	private Set<ChiTietDichVu> chiTietDichVus = new HashSet<ChiTietDichVu>();
	
	@OneToMany(mappedBy = "phong")
	private Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<ChiTietHoaDon>();
	
	@OneToMany(mappedBy = "phong")
	private Set<PhieuDatPhong> phieuDatPhongs = new HashSet<PhieuDatPhong>();
	

	public Set<PhieuDatPhong> getPhieuDatPhongs() {
		return phieuDatPhongs;
	}

	public void setPhieuDatPhongs(Set<PhieuDatPhong> phieuDatPhongs) {
		this.phieuDatPhongs = phieuDatPhongs;
	}

	public Set<ChiTietDichVu> getChiTietDichVus() {
		return chiTietDichVus;
	}

	public void setChiTietDichVus(Set<ChiTietDichVu> chiTietDichVus) {
		this.chiTietDichVus = chiTietDichVus;
	}

	public Set<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}

	public void setChiTietHoaDons(Set<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}

	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Phong(String maPhong, LoaiPhong loaiPhong, Enum_TrangThai trangThai, Set<ChiTietDichVu> chiTietDichVus,
			Set<ChiTietHoaDon> chiTietHoaDons, Set<PhieuDatPhong> phieuDatPhongs) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
		this.chiTietDichVus = chiTietDichVus;
		this.chiTietHoaDons = chiTietHoaDons;
		this.phieuDatPhongs = phieuDatPhongs;
	}

	public Phong(String maPhong, LoaiPhong loaiPhong, Enum_TrangThai trangThai) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
	}
	
	public Phong(String maPhong, Enum_TrangThai trangThai) {
		super();
		this.maPhong = maPhong;
		this.trangThai = trangThai;
	}

	public Phong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public Enum_TrangThai getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Enum_TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maPhong);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	@Override
	public String toString() {
		return String.format("Phong [maPhong=%s, loaiPhong=%s, trangThai=%s]", maPhong, loaiPhong, trangThai);
	}

}
