package entity;

import jakarta.persistence.*;

/**
 * @author VoDinhThong
 * @description doing task
 * @update 4/10/2024
 * @since 4/10/2024
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "TempDatPhong.getAllTemp", query = "select * from TempDatPhong"),
        @NamedNativeQuery(name = "TempDatPhong.addTemp", query = "insert into TempDatPhong values(?,?)"),
        @NamedNativeQuery(name = "TempDatPhong.deleteALLTempDatPhong", query = "delete TempDatPhong where maPhong <> '000'"),
        @NamedNativeQuery(name = "TempDatPhong.deleteTempDP", query = "delete TempDatPhong where maPhong=?"),
        @NamedNativeQuery(name = "TempDatPhong.getTempDP", query = "update TempDatPhong set soNguoi=? where maPhong=?")
})
public class TempDatPhong {
    @Id
    @Column(name = "maPhong", columnDefinition = "varchar(20)", nullable = false)
    private String maPhong;

    @Column(name = "soNguoiHat", columnDefinition = "int", nullable = false)
    private int soNguoiHat;

    public TempDatPhong() {
    }

    public TempDatPhong(String maPhong, int soNguoi) {
        this.maPhong = maPhong;
        this.soNguoiHat = soNguoi;
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

    @Override
    public String toString() {
        return "TempDatPhong [maPhong=" + maPhong + ", soNguoi=" + soNguoiHat + "]";
    }
}
