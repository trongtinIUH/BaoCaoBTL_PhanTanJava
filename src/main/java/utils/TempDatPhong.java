package utils;

import java.io.Serializable;

public class TempDatPhong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6238220535725074578L;
	private String maPhong;
	private int soNguoiHat;

	public TempDatPhong(String maPhong, int soNguoiHat) {
		super();
		this.maPhong = maPhong;
		this.soNguoiHat = soNguoiHat;
	}

	public TempDatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public int getSoNguoiHat() {
		return soNguoiHat;
	}

	public void setSoNguoiHat(int soNguoiHat) {
		this.soNguoiHat = soNguoiHat;
	}
}
