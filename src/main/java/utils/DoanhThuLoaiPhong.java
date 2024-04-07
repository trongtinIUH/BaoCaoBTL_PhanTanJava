package utils;

import java.util.Date;
import java.util.Objects;

public class DoanhThuLoaiPhong {
	private Date ngay;
	private double doanhThuPhongThuong;
	private double doanhThuPhongVIP;
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	public double getDoanhThuPhongThuong() {
		return doanhThuPhongThuong;
	}
	public void setDoanhThuPhongThuong(double doanhThuPhongThuong) {
		this.doanhThuPhongThuong = doanhThuPhongThuong;
	}
	public double getDoanhThuPhongVIP() {
		return doanhThuPhongVIP;
	}
	public void setDoanhThuPhongVIP(double doanhThuPhongVIP) {
		this.doanhThuPhongVIP = doanhThuPhongVIP;
	}
	public DoanhThuLoaiPhong(Date ngay, double doanhThuPhongThuong, double doanhThuPhongVIP) {
		super();
		this.ngay = ngay;
		this.doanhThuPhongThuong = doanhThuPhongThuong;
		this.doanhThuPhongVIP = doanhThuPhongVIP;
	}
	public DoanhThuLoaiPhong(double doanhThuPhongThuong, double doanhThuPhongVIP) {
		super();
		this.doanhThuPhongThuong = doanhThuPhongThuong;
		this.doanhThuPhongVIP = doanhThuPhongVIP;
	}
	public DoanhThuLoaiPhong() {
		super();
	}
	@Override
	public String toString() {
		return "DoanhThuLoaiPhong [ngay=" + ngay + ", doanhThuPhongThuong=" + doanhThuPhongThuong
				+ ", doanhThuPhongVIP=" + doanhThuPhongVIP + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(doanhThuPhongThuong, doanhThuPhongVIP, ngay);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoanhThuLoaiPhong other = (DoanhThuLoaiPhong) obj;
		return Double.doubleToLongBits(doanhThuPhongThuong) == Double.doubleToLongBits(other.doanhThuPhongThuong)
				&& Double.doubleToLongBits(doanhThuPhongVIP) == Double.doubleToLongBits(other.doanhThuPhongVIP)
				&& Objects.equals(ngay, other.ngay);
	}
	
	
}
