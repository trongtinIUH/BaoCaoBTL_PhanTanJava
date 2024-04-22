package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "HoaDonDatPhong.getAllHoaDonDatPhong", query = "SELECT * FROM HoaDonDatPhong", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonTheoMaHoaDon", query = "SELECT * FROM HoaDonDatPhong WHERE maHoaDon = :maHoaDon", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getMaHDTheoMaPhieuDP", 
		query = "select maHoaDon from PhieuDatPhong p "
				+ "join ChiTietHoaDon o "
				+ "on gioNhanPhong = ngayGioNhanPhong where p.maPhieu = :maPhieu", 
				resultClass = String.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonDatPhongTheoMaHD", query = "SELECT * FROM HoaDonDatPhong WHERE maHoaDon = :maHoaDon", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonDatPhongTheoMaPDP", query = "SELECT * FROM PhieuDatPhong p JOIN ChiTietHoaDon o ON p.maPhong = o.maPhong JOIN HoaDonDatPhong hd ON hd.maKhachHang = p.maKhachHang WHERE maPhieu = :maPDP AND FORMAT(p.ngayGioNhanPhong, 'yyyy-MM-dd HH:mm') = FORMAT(o.gioNhanPhong, 'yyyy-MM-dd HH:mm')", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonDatPhongTheoTenKH", query = "SELECT maHoaDon, hd.maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua FROM HoaDonDatPhong hd JOIN KhachHang kh ON kh.maKhachHang = hd.maKhachHang WHERE kh.hoTen LIKE CONCAT('%', :tenKH, '%')", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonDatPhongTheoMaNV", query = "SELECT maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua "
				+ "FROM HoaDonDatPhong " + "WHERE maNhanVien = :maNV", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonTheoNgayLapHD", query = "SELECT maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua "
				+ "FROM HoaDonDatPhong " + "WHERE ngayLapHoaDon = :ngayLapHD", resultClass = HoaDonDatPhong.class),
		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonTheoThang", query = "SELECT maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua "
				+ "FROM HoaDonDatPhong "
				+ "WHERE MONTH(ngayLapHoaDon) = :thang AND YEAR(ngayLapHoaDon) = :nam", resultClass = HoaDonDatPhong.class),

		@NamedNativeQuery(name = "HoaDonDatPhong.getHoaDonTheoNam", query = "SELECT maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua "
				+ "FROM HoaDonDatPhong " + "WHERE YEAR(ngayLapHoaDon) = :nam", resultClass = HoaDonDatPhong.class),

		@NamedNativeQuery(name = "HoaDonDatPhong.updateHoaDon", query = "UPDATE HoaDonDatPhong "
				+ "SET maNhanVien = :maNV, ngayLapHoaDon = :ngayLap, trangThai = :status " + "WHERE maHoaDon = :maHD"),

		@NamedNativeQuery(name = "HoaDonDatPhong.updateHoaDon2", query = "UPDATE HoaDonDatPhong "
				+ "SET maKhachHang = :maKH, maNhanVien = :maNV, ngayLapHoaDon = :ngayLap, trangThai = :status, tienKhachDua = :tienKD "
				+ "WHERE maHoaDon = :maHD"),

		@NamedNativeQuery(name = "HoaDonDatPhong.updateHoaDon3", query = "UPDATE HoaDonDatPhong "
				+ "SET maKhachHang = :maKH, maNhanVien = :maNV, ngayLapHoaDon = :ngayLap, trangThai = :status, maKhuyenMai = :maKM, tienKhachDua = :tienKD "
				+ "WHERE maHoaDon = :maHD"),

		@NamedNativeQuery(name = "HoaDonDatPhong.addHoaDonDatPhong", query = "INSERT INTO HoaDonDatPhong (maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, trangThai, maKhuyenMai, tienKhachDua) "
				+ "VALUES (:maHD, :maKH, :maNV, :ngayLap, :status, :maKM, :tienKD)"),

		@NamedNativeQuery(name = "HoaDonDatPhong.deleteHoaDon", query = "DELETE FROM HoaDonDatPhong "
				+ "WHERE maHoaDon = :maHD")

})
public class HoaDonDatPhong implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095518865707011712L;

	@Id
	@Column(name = "maHoaDon", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String maHoaDon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang", columnDefinition = "VARCHAR(20)", nullable = false)
	private KhachHang khachHang;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien", columnDefinition = "VARCHAR(20)", nullable = false)
	private NhanVien nhanVien;

	@Column(name = "ngayLapHoaDon", columnDefinition = "DATE", nullable = false)
	private Date ngayLapHoaDon;

	@Column(name = "trangThai", columnDefinition = "BIT", nullable = false)
	private boolean trangThai;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhuyenMai", columnDefinition = "VARCHAR(20)", nullable = true)
	private KhuyenMai khuyenMai;

	@Column(name = "tienKhachDua", columnDefinition = "MONEY", nullable = false)
	private double tienKhachDua;

	@OneToMany(mappedBy = "hoaDon")
	private Set<ChiTietDichVu> chiTietDichVus = new HashSet<ChiTietDichVu>();

	@OneToMany(mappedBy = "hoaDon")
	private Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<ChiTietHoaDon>();

	public Set<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}

	public void setChiTietHoaDons(Set<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}

	public Set<ChiTietDichVu> getChiTietDichVus() {
		return chiTietDichVus;
	}

	public void setChiTietDichVus(Set<ChiTietDichVu> chiTietDichVus) {
		this.chiTietDichVus = chiTietDichVus;
	}

	public HoaDonDatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDonDatPhong(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Date ngayLapHoaDon,
			boolean trangThai, KhuyenMai khuyenMai, double tienKhachDua) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.trangThai = trangThai;
		this.khuyenMai = khuyenMai;
		this.tienKhachDua = tienKhachDua;
	}

	public HoaDonDatPhong(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Date getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public double getTienKhachDua() {
		return tienKhachDua;
	}

	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double tinhTongTienThanhToan(double tongTienPhong, double tongTienDV, float phanTramKhuyenMai) {
		return ((tongTienPhong + tongTienDV) * (100 - phanTramKhuyenMai * 100) / 100) * 1.1;
	}

	public double tinhTienTraLai(double tongTienPhong, double tongTienDV, float phanTramKhuyenMai,
			double tienKhachDua) {
		double tongTien = tinhTongTienThanhToan(tongTienPhong, tongTienDV, phanTramKhuyenMai);
		return tongTien - tienKhachDua;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDonDatPhong other = (HoaDonDatPhong) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return String.format(
				"HoaDonDatPhong [maHoaDon=%s, khachHang=%s, nhanVien=%s, ngayLapHoaDon=%s, trangThai=%s, khuyenMai=%s, tienKhachDua=%s]",
				maHoaDon, khachHang, nhanVien, ngayLapHoaDon, trangThai, khuyenMai, tienKhachDua);
	}

}
