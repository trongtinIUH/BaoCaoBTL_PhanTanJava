package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "KhuyenMai.getAllKhuyenMais", query = "SELECT maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, phanTramKhuyenMai FROM KhuyenMai", resultClass = KhuyenMai.class),

		@NamedNativeQuery(name = "KhuyenMai.getKhuyenMaiTheoTenKhuyenMai", query = "SELECT maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, phanTramKhuyenMai FROM KhuyenMai WHERE tenKhuyenMai LIKE CONCAT('%', :tenKhuyenMai, '%')", resultClass = KhuyenMai.class),

		@NamedNativeQuery(name = "KhuyenMai.getKhuyenMaiTheoNgayBatDauKM", query = "SELECT maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, phanTramKhuyenMai FROM KhuyenMai WHERE ngayBatDau = :ngayBatDau", resultClass = KhuyenMai.class),

		@NamedNativeQuery(name = "KhuyenMai.getPhanTramKhuyenMaiTheoMaKM", query = "SELECT phanTramKhuyenMai FROM KhuyenMai WHERE maKhuyenMai = :maKM",resultClass =  Float.class),
		@NamedNativeQuery(name = "KhuyenMai.getKhuyenMaiTheoMaKhuyenMai", query = "SELECT maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, phanTramKhuyenMai FROM KhuyenMai WHERE maKhuyenMai = :maKhuyenMai", resultClass = KhuyenMai.class),
		
})
public class KhuyenMai implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7953143939375949866L;
	@Id
	@Column(name = "maKhuyenMai", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String maKhuyenMai;
	@Column(name = "tenKhuyenMai", columnDefinition = "NVARCHAR(50)", nullable = false)
	private String tenKhuyenMai;

	@Column(name = "ngayBatDau", columnDefinition = "DATE", nullable = false)
	private Date ngayBatDau;

	@Column(name = "ngayKetThuc", columnDefinition = "DATE", nullable = false)
	private Date ngayKetThuc;

	@Column(name = "phanTramKhuyenMai", columnDefinition = "FLOAT", nullable = false)
	private float phanTramKhuyenMai;

	@OneToMany(mappedBy = "khuyenMai")
	private Set<HoaDonDatPhong> hoaDonDatPhongs = new HashSet<HoaDonDatPhong>();

	public Set<HoaDonDatPhong> getHoaDonDatPhongs() {
		return hoaDonDatPhongs;
	}

	public void setHoaDonDatPhongs(Set<HoaDonDatPhong> hoaDonDatPhongs) {
		this.hoaDonDatPhongs = hoaDonDatPhongs;
	}

	public KhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, Date ngayBatDau, Date ngayKetThuc,
			float phanTramKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public float getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}

	public void setPhanTramKhuyenMai(float phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}

	@Override
	public String toString() {
		return String.format(
				"KhuyenMai [maKhuyenMai=%s, tenKhuyenMai=%s, ngayBatDau=%s, ngayKetThuc=%s, phanTramKhuyenMai=%s]",
				maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, phanTramKhuyenMai);
	}

}
