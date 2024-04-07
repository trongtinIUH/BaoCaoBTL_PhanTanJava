package entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ChiTietHoaDonID {
	private String hoaDonId;
	private String phongId;
	public ChiTietHoaDonID() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietHoaDonID(String hoaDonId, String phongId) {
		super();
		this.hoaDonId = hoaDonId;
		this.phongId = phongId;
	}
	public String getHoaDonId() {
		return hoaDonId;
	}
	public void setHoaDonId(String hoaDonId) {
		this.hoaDonId = hoaDonId;
	}
	public String getPhongId() {
		return phongId;
	}
	public void setPhongId(String phongId) {
		this.phongId = phongId;
	}
	
	
}
