package utils;

import java.io.Serializable;

public class TempThanhToan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4918917749377908187L;
	private String maPhong;

	public TempThanhToan(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public TempThanhToan() {
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
