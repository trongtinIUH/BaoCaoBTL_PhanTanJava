package entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ChiTietDichVuID {
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
