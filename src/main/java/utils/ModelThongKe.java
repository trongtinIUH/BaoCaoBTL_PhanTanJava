package utils;

import java.util.Objects;

public class ModelThongKe {
	private String month;
	private String year;
	private double tongDoanhThu;
	private double doanhThuPhong;
	private double doanhThuDichVu;
	public String getYear() {
		return year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public double getTongDoanhThu() {
		return tongDoanhThu;
	}
	public void setTongDoanhThu(double tongDoanhThu) {
		this.tongDoanhThu = tongDoanhThu;
	}
	public double getDoanhThuPhong() {
		return doanhThuPhong;
	}
	public void setDoanhThuPhong(double doanhThuPhong) {
		this.doanhThuPhong = doanhThuPhong;
	}
	public double getDoanhThuDichVu() {
		return doanhThuDichVu;
	}
	public void setDoanhThuDichVu(double doanhThuDichVu) {
		this.doanhThuDichVu = doanhThuDichVu;
	}
	public ModelThongKe(String year, double tongDoanhThu, double doanhThuPhong, double doanhThuDichVu) {
		super();
		this.year = year;
		this.tongDoanhThu = tongDoanhThu;
		this.doanhThuPhong = doanhThuPhong;
		this.doanhThuDichVu = doanhThuDichVu;
	}
	public ModelThongKe(String year) {
		super();
		this.year = year;
	}
	
	
	public ModelThongKe() {
		super();
	}
	@Override
	public String toString() {
		return "ModelThongKe [year=" + year + ", tongDoanhThu=" + tongDoanhThu + ", doanhThuPhong=" + doanhThuPhong
				+ ", doanhThuDichVu=" + doanhThuDichVu + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelThongKe other = (ModelThongKe) obj;
		return Objects.equals(year, other.year);
	}
	
}
