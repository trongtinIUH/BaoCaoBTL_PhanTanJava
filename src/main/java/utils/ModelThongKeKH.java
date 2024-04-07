package utils;

public class ModelThongKeKH {
	private String maKH;
	private String tenKH;
	private String soDienThoai;
	private boolean gioiTinh;
	private double TongSoGioHat;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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
	public double getTongSoGioHat() {
		return TongSoGioHat;
	}
	public void setTongSoGioHat(double tongSoGioHat) {
		TongSoGioHat = tongSoGioHat;
	}
	public ModelThongKeKH(String maKH, String tenKH, String soDienThoai, boolean gioiTinh, double tongSoGioHat) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		TongSoGioHat = tongSoGioHat;
	}
	public ModelThongKeKH() {
		super();
	}
	@Override
	public String toString() {
		return "ModelThongKeKH [maKH=" + maKH + ", tenKH=" + tenKH + ", soDienThoai=" + soDienThoai + ", gioiTinh="
				+ gioiTinh + ", TongSoGioHat=" + TongSoGioHat + "]";
	}
	
}
