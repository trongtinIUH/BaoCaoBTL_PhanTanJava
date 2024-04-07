package utils;

public class ModelThongKeDTNhieuNam {
	private String nam;
	private int tongSoHoaDon;
	private double tongDoanhThu;
	private double tongDoanhThuPhongThuong;
	private double tongDoanhThuPhongVIP;
	private double tongTienPhong;
	private double tongTienDichVu;
	private double tongSoGioHat;
	public ModelThongKeDTNhieuNam(String nam, int tongSoHoaDon, double tongDoanhThu, double tongDoanhThuPhongThuong,
			double tongDoanhThuPhongVIP, double tongTienPhong, double tongTienDichVu, double tongSoGioHat) {
		super();
		this.nam = nam;
		this.tongSoHoaDon = tongSoHoaDon;
		this.tongDoanhThu = tongDoanhThu;
		this.tongDoanhThuPhongThuong = tongDoanhThuPhongThuong;
		this.tongDoanhThuPhongVIP = tongDoanhThuPhongVIP;
		this.tongTienPhong = tongTienPhong;
		this.tongTienDichVu = tongTienDichVu;
		this.tongSoGioHat = tongSoGioHat;
	}
	public ModelThongKeDTNhieuNam() {
		super();
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public int getTongSoHoaDon() {
		return tongSoHoaDon;
	}
	public void setTongSoHoaDon(int tongSoHoaDon) {
		this.tongSoHoaDon = tongSoHoaDon;
	}
	public double getTongDoanhThu() {
		return tongDoanhThu;
	}
	public void setTongDoanhThu(double tongDoanhThu) {
		this.tongDoanhThu = tongDoanhThu;
	}
	public double getTongDoanhThuPhongThuong() {
		return tongDoanhThuPhongThuong;
	}
	public void setTongDoanhThuPhongThuong(double tongDoanhThuPhongThuong) {
		this.tongDoanhThuPhongThuong = tongDoanhThuPhongThuong;
	}
	public double getTongDoanhThuPhongVIP() {
		return tongDoanhThuPhongVIP;
	}
	public void setTongDoanhThuPhongVIP(double tongDoanhThuPhongVIP) {
		this.tongDoanhThuPhongVIP = tongDoanhThuPhongVIP;
	}
	public double getTongTienPhong() {
		return tongTienPhong;
	}
	public void setTongTienPhong(double tongTienPhong) {
		this.tongTienPhong = tongTienPhong;
	}
	public double getTongTienDichVu() {
		return tongTienDichVu;
	}
	public void setTongTienDichVu(double tongTienDichVu) {
		this.tongTienDichVu = tongTienDichVu;
	}
	public double getTongSoGioHat() {
		return tongSoGioHat;
	}
	public void setTongSoGioHat(double tongSoGioHat) {
		this.tongSoGioHat = tongSoGioHat;
	}
	@Override
	public String toString() {
		return "ModelThongKeDTNhieuNam [nam=" + nam + ", tongSoHoaDon=" + tongSoHoaDon + ", tongDoanhThu="
				+ tongDoanhThu + ", tongDoanhThuPhongThuong=" + tongDoanhThuPhongThuong + ", tongDoanhThuPhongVIP="
				+ tongDoanhThuPhongVIP + ", tongTienPhong=" + tongTienPhong + ", tongTienDichVu=" + tongTienDichVu
				+ ", tongSoGioHat=" + tongSoGioHat + "]";
	}
	
	
	
}
