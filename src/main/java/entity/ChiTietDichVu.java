package entity;

import java.io.Serializable;



import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "ChiTietDichVu.getAllChiTietDichVu", query = "select s from ChiTietDichVu s")
})
public class ChiTietDichVu  implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -7257157286341808456L;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon", columnDefinition = "VARCHAR(20)", nullable = false)
	private HoaDonDatPhong hoaDon;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong")
	private Phong phong;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSanPham")
	private SanPham sanPham;
	@Column(name = "soLuong", columnDefinition = "INT", nullable = false)
	private int soLuong;
	@Column(name = "gia", columnDefinition = "FLOAT", nullable = false)
	private double gia;
	
	
	public ChiTietDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietDichVu(HoaDonDatPhong hoaDon, Phong phong, SanPham sanPham, int soLuong, double gia) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.gia = gia;
	}
	
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public HoaDonDatPhong getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDonDatPhong hoaDon) {
		this.hoaDon = hoaDon;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
	    return "ChiTietDichVu [hoaDonId=" + hoaDon.getMaHoaDon() + ", phongId=" + phong.getMaPhong() + 
	    		", sanPhamId=" + sanPham.getMaSanPham() + ", soLuong=" + soLuong
	            + ", gia=" + gia + "]";
	}
	
}
