package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class ChiTietHoaDon implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = -3901969153102295736L;
//	@EmbeddedId
//	private ChiTietHoaDonID chiTietHoaDonID;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon", columnDefinition = "VARCHAR(20)", nullable = false)
	private HoaDonDatPhong hoaDon;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maPhong")
	private Phong phong;

	private Timestamp gioNhanPhong;
	private Timestamp gioTraPhong;
	private double soGioHat;
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(HoaDonDatPhong hoaDon, Phong phong,  Timestamp gioNhanPhong, Timestamp gioTraPhong, double soGioHat) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.gioNhanPhong = gioNhanPhong;
		this.gioTraPhong = gioTraPhong;
		this.soGioHat = soGioHat;
	}
	public HoaDonDatPhong getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDonDatPhong hoaDon) {
		this.hoaDon = hoaDon;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public Timestamp getGioNhanPhong() {
		return gioNhanPhong;
	}
	public void setGioNhanPhong(Timestamp gioNhanPhong) {
		this.gioNhanPhong = gioNhanPhong;
	}
	public Timestamp getGioTraPhong() {
		return gioTraPhong;
	}
	public void setGioTraPhong(Timestamp gioTraPhong) {
		this.gioTraPhong = gioTraPhong;
	}
	public double getSoGioHat() {
		return soGioHat;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(gioNhanPhong, gioTraPhong, hoaDon, phong, soGioHat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(gioNhanPhong, other.gioNhanPhong) && Objects.equals(gioTraPhong, other.gioTraPhong)
				&& Objects.equals(hoaDon, other.hoaDon) && Objects.equals(phong, other.phong)
				&& Double.doubleToLongBits(soGioHat) == Double.doubleToLongBits(other.soGioHat);
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", phong=" + phong + ", gioNhanPhong=" + gioNhanPhong
				+ ", gioTraPhong=" + gioTraPhong + ", soGioHat=" + soGioHat + "]";
	}
	
}
