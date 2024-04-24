package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Phong.getallPhongs", query = "select p from Phong p"),
	@NamedQuery(name = "Phong.getPhongTheoMaLoaiPhong", query = "select p from Phong p INNER JOIN p.loaiPhong lp where lp.maLoaiPhong = :maLoaiPhong"),
	@NamedQuery(name = "Phong.getPhongTheoTrangThai", query = "select p from Phong p where p.trangThai = :trangThai"),
	@NamedQuery(name = "Phong.getPhongTheoTenLoaiPhongVaTrangThai", query = "SELECT p FROM Phong p JOIN p.loaiPhong lp WHERE lp.tenLoaiPhong = :tenLoaiPhong AND p.trangThai = :trangThai"),
	@NamedQuery(name = "Phong.getPhongTheoSuChua", query = "SELECT p FROM Phong p JOIN p.loaiPhong lp WHERE lp.sucChua = :sucChua"),
	@NamedQuery(name = "Phong.getPhongTheoTenLoaiPhong", query = "SELECT p FROM Phong p JOIN p.loaiPhong lp WHERE lp.tenLoaiPhong = :tenLoaiPhong"),
	@NamedQuery(name = "Phong.getPhongTheoMaCTHD", query = "SELECT p FROM Phong p JOIN p.chiTietHoaDons cthd JOIN cthd.hoaDon hd WHERE hd.maHoaDon = :maHoaDon"),
	@NamedQuery(name = "Phong.getPhongTKTheoTrangThai",query = "SELECT p FROM Phong p JOIN p.loaiPhong lp WHERE p.trangThai = :trangThai AND lp.sucChua >= :soNguoi"),
	@NamedQuery(
		    name = "Phong.updatePhong",
		    query = "UPDATE Phong p SET p.trangThai = :trangThaiMoi WHERE p.maPhong = :maPhong"
		),
	
	
	@NamedQuery(name = "Phong.getPhongTKTheoTenLoaiPhong",
				query = "SELECT p FROM Phong p JOIN p.loaiPhong lp "
						+ "WHERE lp.tenLoaiPhong = :tenLoaiPhong AND lp.sucChua >= :soNguoi"),
	
	@NamedQuery(name = "Phong.getPhongTKTheoTenLoaiPhongVaTrangThai",
				query = "SELECT p FROM Phong p INNER JOIN p.loaiPhong lp "
						+ "WHERE lp.tenLoaiPhong = :tenLoaiPhong AND p.trangThai = :trangThai "
						+ "AND lp.sucChua >= :soNguoi"),
	
	
	@NamedQuery(name = "Phong.getPhongTKTheoSoNguoiHat",query = "SELECT p FROM Phong p JOIN p.loaiPhong lp WHERE lp.sucChua >= :soNguoi"),
	@NamedQuery(name = "Phong.tinhTongTienPhongTheoMaHoaDon", 
			query = "SELECT SUM(lp.donGiaTheoGio * cthd.soGioHat) "
			+ "AS tongTien FROM Phong p JOIN p.chiTietHoaDons cthd JOIN cthd.hoaDon hd "
			+ "JOIN p.loaiPhong lp WHERE hd.maHoaDon = :maHoaDon GROUP BY hd.maHoaDon"),
	@NamedQuery(
		    name = "Phong.laydsPhongMoi",
		    query = "SELECT p FROM Phong p JOIN p.loaiPhong lp WHERE p.trangThai = 'Trong'"
		),
	@NamedQuery(
	        name = "Phong.tinhTongDoanhThuLoaiPhongTheoNgay",
	        query = "SELECT NEW utils.DoanhThuLoaiPhong("
	                + "CASE WHEN HDDP.ngayLapHoaDon IS NULL THEN :ngay ELSE HDDP.ngayLapHoaDon END,"
	                + "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio * CTHD.soGioHat ELSE 0 END),"
	                + "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio * CTHD.soGioHat ELSE 0 END)) "
	                + "FROM HoaDonDatPhong HDDP "
	                + "INNER JOIN HDDP.chiTietHoaDons CTHD "
	                + "INNER JOIN CTHD.phong P "
	                + "INNER JOIN P.loaiPhong LP "
	                + "WHERE HDDP.ngayLapHoaDon = :ngay "
	                + "GROUP BY HDDP.ngayLapHoaDon"
	    ),
	@NamedQuery(
		name = "Phong.tinhTongDoanhThuLoaiPhongTheoThang", 
		query = "SELECT new utils.DoanhThuLoaiPhong(" +
				"FORMAT(hddp.ngayLapHoaDon, 'yyyy-MM'), " +
				"SUM(cthd.soGioHat * CASE WHEN lp.maLoaiPhong LIKE 'PT%' THEN lp.donGiaTheoGio ELSE 0 END), " +
				"SUM(cthd.soGioHat * CASE WHEN lp.maLoaiPhong LIKE 'PV%' THEN lp.donGiaTheoGio ELSE 0 END) " +
				") " +
				"FROM HoaDonDatPhong hddp " +
				"INNER JOIN hddp.chiTietHoaDons cthd " +
				"INNER JOIN cthd.phong p " +
				"INNER JOIN p.loaiPhong lp " +
				"WHERE FUNCTION('MONTH', hddp.ngayLapHoaDon) = :thang " +
				"AND FUNCTION('YEAR', hddp.ngayLapHoaDon) = :nam " +
				"GROUP BY FORMAT(hddp.ngayLapHoaDon, 'yyyy-MM')"
		),
	@NamedQuery(
		    name = "Phong.tinhTongDoanhThuLoaiPhongTheoNhieuNam",
		    query = "SELECT NEW utils.DoanhThuLoaiPhong(" +
		            "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END), " +
		            "SUM(CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) " +
		            ") " +
		            "FROM HoaDonDatPhong HDDP " +
		            "INNER JOIN HDDP.chiTietHoaDons CTHD " +
		            "INNER JOIN CTHD.phong P " +
		            "INNER JOIN P.loaiPhong LP " +
		            "WHERE FUNCTION('YEAR', HDDP.ngayLapHoaDon) BETWEEN :startYear AND :endYear"
		)
})
@NamedNativeQueries({
	@NamedNativeQuery(
            name = "Phong.tinhTongDoanhThuLoaiPhongTheoNam",
            query = "DECLARE @nam INT = :year "
            		+ "SELECT "
					+ "FORMAT(DATEFROMPARTS(@nam, 1, 1), 'yyyy') AS Nam,  "
					+ "0 AS TongTienPhongThuong, "
					+ "0 AS TongTienPhongVIP "
					+ "WHERE NOT EXISTS ( "
					+ "SELECT * "
					+ "FROM HoaDonDatPhong "
					+ "WHERE YEAR(ngayLapHoaDon) = @nam"
					+ ")"
					+ "UNION ALL "
					+ "SELECT  "
					+ "FORMAT(ngayLapHoaDon,'yyyy') AS Nam, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PT%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongThuong, "
					+ "  SUM(CTHD.soGioHat * CASE WHEN LP.maLoaiPhong LIKE 'PV%' THEN LP.donGiaTheoGio ELSE 0 END) AS TongTienPhongVIP "
					+ "FROM HoaDonDatPhong HDDP "
					+ "INNER JOIN ChiTietHoaDon CTHD ON HDDP.maHoaDon = CTHD.maHoaDon "
					+ "INNER JOIN Phong P ON CTHD.maPhong = P.maPhong "
					+ "INNER JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong "
					+ "WHERE YEAR(ngayLapHoaDon) = @nam "
					+ "GROUP BY FORMAT(ngayLapHoaDon,'yyyy')"
        )
})
public class Phong implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3802269524994377355L;

	/**
	 * 
	 */
	@Id
	@Column(name = "maPhong", columnDefinition = "VARCHAR(10)", nullable = false, unique = true)
	private String maPhong;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maLoaiPhong", columnDefinition = "VARCHAR(20)", nullable = false)
	private LoaiPhong loaiPhong;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "trangThai", columnDefinition = "VARCHAR(50)", nullable = false)
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
		return String.format("Phong [maPhong=%s, loaiPhong=%s, trangThai=%s]", maPhong, loaiPhong.getTenLoaiPhong(), trangThai);
	}

}
