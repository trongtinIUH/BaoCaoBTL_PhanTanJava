package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
    	@NamedQuery(name = "LoaiPhong.getAllLoaiPhongs", query = "SELECT lp FROM LoaiPhong lp"),
    	@NamedQuery(name = "LoaiPhong.getLoaiPhongTheoMaLoaiPhong", query = "SELECT lp FROM LoaiPhong lp WHERE lp.maLoaiPhong = :maLoaiPhong"),
    	@NamedQuery(name = "LoaiPhong.getLoaiPhongTheoSucChua", query = "SELECT lp FROM LoaiPhong lp WHERE lp.sucChua = :sucChua"),
    	@NamedQuery(name = "LoaiPhong.getLoaiPhongTheoDonGia", query = "SELECT lp FROM LoaiPhong lp WHERE lp.donGiaTheoGio = :donGiaTheoGio"),
    	@NamedQuery(name = "LoaiPhong.getTenLoaiPhongTheoMaLoaiPhong", query = "SELECT lp.tenLoaiPhong FROM LoaiPhong lp WHERE lp.maLoaiPhong = :maLoaiPhong"),
    	@NamedQuery(name = "LoaiPhong.getSucChuaTheoMaLoaiPhong", query = "SELECT lp.sucChua FROM LoaiPhong lp WHERE lp.maLoaiPhong = :maLoaiPhong"),
    	@NamedQuery(name = "LoaiPhong.getDonGiaTheoMaLoaiPhong", query = "SELECT lp.donGiaTheoGio FROM LoaiPhong lp WHERE lp.maLoaiPhong = :maLoaiPhong")
})
public class LoaiPhong implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6142860253165348219L;
	@Id
	@Column(name = "maLoaiPhong", columnDefinition = "VARCHAR(20)", unique = true, nullable = false)
	private String maLoaiPhong;
	@Column(name = "tenLoaiPhong", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String tenLoaiPhong;
	@Column(name = "sucChua", columnDefinition = "INT", nullable = false)
	private int sucChua;
	@Column(name = "donGiaTheoGio", columnDefinition = "Money", nullable = false)
	private double donGiaTheoGio;
	@OneToMany(mappedBy = "loaiPhong")
	private Set<Phong> phongs = new HashSet<Phong>();
	
	public Set<Phong> getPhongs() {
		return phongs;
	}
	public void setPhongs(Set<Phong> phongs) {
		this.phongs = phongs;
	}
	public LoaiPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int sucChua, double donGiaTheoGio) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.sucChua = sucChua;
		this.donGiaTheoGio = donGiaTheoGio;
	}
	
	public LoaiPhong(String maLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
	}
	
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
	public int getSucChua() {
		return sucChua;
	}
	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}
	public double getDonGiaTheoGio() {
		return donGiaTheoGio;
	}
	public void setDonGiaTheoGio(double donGiaTheoGio) {
		this.donGiaTheoGio = donGiaTheoGio;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLoaiPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoaiPhong, other.maLoaiPhong);
	}
	@Override
	public String toString() {
		return String.format("LoaiPhong [maLoaiPhong=%s, tenLoaiPhong=%s, sucChua=%s, donGiaTheoGio=%s]", maLoaiPhong,
				tenLoaiPhong, sucChua, donGiaTheoGio);
	}
	

}
