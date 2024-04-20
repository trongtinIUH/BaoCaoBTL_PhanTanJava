package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "PDP.getAllsPhieu", query = "select pdp from PhieuDatPhong pdp"),
	@NamedQuery(
		    name = "PhieuDatPhong.getMaPhongDatTruoc",
		    query = "SELECT pdp FROM PhieuDatPhong pdp WHERE FUNCTION('DATEADD', 'MINUTE', 21, pdp.ngayGioNhanPhong) > CURRENT_TIMESTAMP"
		),
	 @NamedQuery(
		        name = "PhieuDatPhong.getPDPDatTruocTheoMaPhong",
		        query = "SELECT p FROM PhieuDatPhong p WHERE p.maPhong = :maPhong ORDER BY p.ngayGioNhanPhong DESC limit 1"
		    ),
	 @NamedQuery(
		        name = "PhieuDatPhong.getPhieuDatPhongPhongCho",
		        query = "SELECT p FROM PhieuDatPhong p WHERE p.maPhong = :maPhong AND p.ngayGioNhanPhong > CURRENT_TIMESTAMP"
		    ),
	 @NamedQuery(
		        name = "PhieuDatPhong.getPhieuDatPhongTheoMaKH",
		        query = "SELECT p FROM PhieuDatPhong p WHERE p.maKhachHang = :maKhachHang"
		    ),
	 @NamedQuery(
		        name = "PhieuDatPhong.getPhieuDatPhongTheoMaPhong",
		        query = "SELECT p FROM PhieuDatPhong p WHERE p.maPhong = :maPhong"
		    ),
	 @NamedQuery(
		        name = "PhieuDatPhong.getPhieuDatPhongInfo",
		        query = "SELECT NEW PhieuDatPhongInfo(p.maPhong, lp.tenLoaiPhong, pdp.soNguoiHat, pdp.ngayGioDatPhong, pdp.ngayGioNhanPhong, lp.donGiaTheoGio, kh.hoTen) " +
		                "FROM PhieuDatPhong pdp " +
		                "JOIN Phong p ON pdp.maPhong = p.maPhong " +
		                "JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
		                "JOIN KhachHang kh ON pdp.maKhachHang = kh.maKhachHang"
		    ),
	 @NamedQuery(
		        name = "PhieuDatPhong.timThongTinPhieuDatPhongTheoMaPhong",
		        query = "SELECT NEW PhieuDatPhong(p.maPhong, lp.tenLoaiPhong, pdp.soNguoiHat, pdp.ngayGioDatPhong, pdp.ngayGioNhanPhong, lp.donGiaTheoGio, kh.hoTen) " +
		                "FROM PhieuDatPhong pdp " +
		                "JOIN pdp.phong p " +
		                "JOIN p.loaiPhong lp " +
		                "JOIN pdp.khachHang kh " +
		                "WHERE p.maPhong = :maPhong"
		    ),
	 @NamedQuery(
			    name = "PhieuDatPhong.getPDPTheoNgayNhan",
			    query = "SELECT pdp FROM PhieuDatPhong pdp WHERE FUNCTION('DATE', pdp.ngayGioNhanPhong) = :ngayGioNhanPhong"
			),
	 @NamedQuery(
			    name = "PhieuDatPhong.getPDPTheoThangNhan",
			    query = "SELECT pdp FROM PhieuDatPhong pdp WHERE YEAR(pdp.ngayGioNhanPhong) = :year AND MONTH(pdp.ngayGioNhanPhong) = :month"
			),
	 @NamedQuery(
			    name = "PhieuDatPhong.getPDPTheoNamNhan",
			    query = "SELECT pdp FROM PhieuDatPhong pdp WHERE FUNCTION('YEAR', pdp.ngayGioNhanPhong) = :namNhan"
			),
	 @NamedQuery(
			    name = "PhieuDatPhong.getAllsPhieuDatPhong_DangSuDung",
			    query = "SELECT pdp FROM PhieuDatPhong pdp JOIN pdp.phong p WHERE p.trangThai = 'Dang_su_dung'"
			),
	 @NamedQuery(
			    name = "PhieuDatPhong.getPhieuDatPhongTheoMaPDP_DangSuDung",
			    query = "SELECT pdp FROM PhieuDatPhong pdp JOIN Phong p ON pdp.maPhong = p.maPhong WHERE pdp.maPhieu = :maPhieu AND p.trangThai = N'Dang_su_dung'"
			),
	 @NamedQuery(
		        name = "PhieuDatPhong.getPhieuDatPhongTheoMaPhong_TrangThaiCho",
		        query = "SELECT pdp FROM PhieuDatPhong pdp JOIN pdp.phong p WHERE p.maPhong = :maPhong AND p.trangThai = 'Cho' AND pdp.ngayGioDatPhong <> pdp.ngayGioNhanPhong"
		    ),
	 @NamedQuery(
			    name = "PhieuDatPhong.getAllsPhieuDatPhong_PhongCho",
			    query = "SELECT pdp FROM PhieuDatPhong pdp JOIN pdp.phong p WHERE p.trangThai = 'Cho'"
			)
})
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
