package utils;

public class TempPhongBiChuyen {
	private String maPhong;
	private String maPhongMoi;

	public String getMaPhongMoi() {
		return maPhongMoi;
	}

	public void setMaPhongMoi(String maPhongMoi) {
		this.maPhongMoi = maPhongMoi;
	}

	public TempPhongBiChuyen(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public TempPhongBiChuyen(String maPhong, String maPhongMoi) {
		super();
		this.maPhong = maPhong;
		this.maPhongMoi = maPhongMoi;
	}

	public TempPhongBiChuyen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
}
