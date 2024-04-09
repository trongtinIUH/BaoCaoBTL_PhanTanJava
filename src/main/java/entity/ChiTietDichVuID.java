package entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ChiTietDichVuID implements Serializable {
		private String hoaDonDatPhongId;
	    private String sanPhamId;
	    private String phongId;
		public ChiTietDichVuID() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ChiTietDichVuID(String hoaDonDatPhongId, String sanPhamId, String phongId) {
			super();
			this.hoaDonDatPhongId = hoaDonDatPhongId;
			this.sanPhamId = sanPhamId;
			this.phongId = phongId;
		}
		public String getHoaDonDatPhongId() {
			return hoaDonDatPhongId;
		}
		public void setHoaDonDatPhongId(String hoaDonDatPhongId) {
			this.hoaDonDatPhongId = hoaDonDatPhongId;
		}
		public String getSanPhamId() {
			return sanPhamId;
		}
		public void setSanPhamId(String sanPhamId) {
			this.sanPhamId = sanPhamId;
		}
		public String getPhongId() {
			return phongId;
		}
		public void setPhongId(String phongId) {
			this.phongId = phongId;
		}
	    
	    
}
