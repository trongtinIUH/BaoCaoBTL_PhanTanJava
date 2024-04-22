package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(name = "KhachHang.getAllKhachHangs", query = "SELECT maKhachHang, hoTen, soDienThoai, gioiTinh FROM KhachHang", resultClass = KhachHang.class),

	@NamedNativeQuery(name = "KhachHang.getKhachHangTheoTenKH", query = "SELECT maKhachHang, hoTen, soDienThoai, gioiTinh FROM KhachHang WHERE hoTen LIKE CONCAT('%', :tenKhachHang, '%')", resultClass = KhachHang.class),

	@NamedNativeQuery(name = "KhachHang.getKhachHangTheoSDT", query = "SELECT maKhachHang, hoTen, soDienThoai, gioiTinh FROM KhachHang WHERE soDienThoai = :sdt", resultClass = KhachHang.class)
})
public class KhachHang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4267039113765479718L;

	@Id
	@Column(name = "maKhachHang", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String maKhachHang;

	@Column(name = "hoTen", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String hoTen;

	@Column(name = "soDienThoai", columnDefinition = "VARCHAR(20)", nullable = false)
	private String soDienThoai;

	@Column(name = "gioiTinh", columnDefinition = "BIT", nullable = false)
	private boolean gioiTinh;
	
	@OneToMany(mappedBy = "khachHang")
	private Set<HoaDonDatPhong> hoaDonDatPhongs = new HashSet<HoaDonDatPhong>();
	@OneToMany(mappedBy = "khachHang")
	private Set<PhieuDatPhong> phieuDatPhongs = new HashSet<PhieuDatPhong>();
	
	
	public Set<PhieuDatPhong> getPhieuDatPhongs() {
		return phieuDatPhongs;
	}
	public void setPhieuDatPhongs(Set<PhieuDatPhong> phieuDatPhongs) {
		this.phieuDatPhongs = phieuDatPhongs;
	}
	public Set<HoaDonDatPhong> getHoaDonDatPhongs() {
		return hoaDonDatPhongs;
	}
	public void setHoaDonDatPhongs(Set<HoaDonDatPhong> hoaDonDatPhongs) {
		this.hoaDonDatPhongs = hoaDonDatPhongs;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}
	public KhachHang(String maKhachHang, String hoTen, String soDienThoai, boolean gioiTinh) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
	}


	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return String.format("KhachHang [maKhachHang=%s, hoTen=%s, soDienThoai=%s, gioiTinh=%s]", maKhachHang, hoTen,
				soDienThoai, gioiTinh);
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}
	
	
	

}
