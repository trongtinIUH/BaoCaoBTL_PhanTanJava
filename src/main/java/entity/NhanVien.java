package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "maNhanVien", columnDefinition = "varchar(20)", nullable = false, unique = true)
	private String maNhanVien;
	@Column(name = "hoTen", columnDefinition = "nvarchar(100)", nullable = false)
	private String hoTen;
	@Column(name = "soDienThoai", columnDefinition = "varchar(20)", nullable = false)
	private String soDienThoai;
	@Column(name = "gioiTinh", columnDefinition = "bit", nullable = false)
	private boolean gioiTinh;
	@Column(name = "ngaySinh", columnDefinition = "date", nullable = false)
	private Date ngaySinh;
	@Column(name = "chucVu", columnDefinition = "nvarchar(255)", nullable = false, unique = false)
	private String chucVu;
	@Column(name = "anhDaiDien", columnDefinition = "nvarchar(50)", nullable = true)
	private String anhDaiDien;

	@OneToMany(mappedBy = "nhanVien")
	private Set<HoaDonDatPhong> hoaDonDatPhongs = new HashSet<HoaDonDatPhong>();
	
	@OneToMany(mappedBy = "nhanVien")
	private Set<PhieuDatPhong>phieuDatPhongs= new HashSet<PhieuDatPhong>();

	
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

	
	
	
	
	
	
	
	
	
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String hoTen, String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.chucVu = chucVu;
	}
	public NhanVien(String maNhanVien, String hoTen, String soDienThoai, boolean gioiTinh, Date ngaySinh, String chucVu,
			String anhDaiDien) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.anhDaiDien = anhDaiDien;
	}



	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
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

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	@Override
	public String toString() {
		return String.format(
				"NhanVien [maNhanVien=%s, hoTen=%s, soDienThoai=%s, gioiTinh=%s, ngaySinh=%s, chucVu=%s, anhDaiDien=%s]",
				maNhanVien, hoTen, soDienThoai, gioiTinh, ngaySinh, chucVu, anhDaiDien);
	}

}
