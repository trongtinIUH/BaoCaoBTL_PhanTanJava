package entity;

import java.io.Serializable;

import jakarta.persistence.*;

/**
 * @author VoDinhThong
 * @description doing task
 * @update 4/10/2024
 * @since 4/10/2024
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "TempPhongBiChuyen.getAllTemp",
                query = "select * from TempPhongBiChuyen", resultClass = TempPhongBiChuyen.class
        ),
        @NamedNativeQuery(name="TempPhongBiChuyen.addTemp",
                query = "insert into TempPhongBiChuyen values(?,?)", resultClass = TempPhongBiChuyen.class),
        @NamedNativeQuery(name="TempPhongBiChuyen.deleteALLTempPhongBiChuyen",
                query = "delete TempPhongBiChuyen where maPhongBiChuyen <> '000'", resultClass = TempPhongBiChuyen.class),
        @NamedNativeQuery(name="TempPhongBiChuyen.deleteTempPhongBiChuyen",
                query = "delete TempPhongBiChuyen where maPhongBiChuyen=?", resultClass = TempPhongBiChuyen.class),
        @NamedNativeQuery(name="TempPhongBiChuyen.updateTempPhongBiChuyen",
                query = "update TempPhongBiChuyen set maPhongMoi=? where maPhongBiChuyen=?", resultClass = TempPhongBiChuyen.class),
})
public class TempPhongBiChuyen implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 857923939714958971L;
	@Id
    @Column(name = "maPhongBiChuyen", columnDefinition = "varchar(20)", nullable = false)
    private String maPhongBiChuyen;
    @Column(name = "maPhongMoi", columnDefinition = "varchar(20)")
    private String maPhongMoi;

    public TempPhongBiChuyen() {
    }

    public TempPhongBiChuyen(String maPhongBiChuyen, String maPhongMoi) {
        this.maPhongBiChuyen = maPhongBiChuyen;
        this.maPhongMoi = maPhongMoi;
    }

    public String getMaPhongBiChuyen() {
        return maPhongBiChuyen;
    }

    public void setMaPhongBiChuyen(String maPhongBiChuyen) {
        this.maPhongBiChuyen = maPhongBiChuyen;
    }

    public String getMaPhongMoi() {
        return maPhongMoi;
    }

    public void setMaPhongMoi(String maPhongMoi) {
        this.maPhongMoi = maPhongMoi;
    }

    @Override
    public String toString() {
        return "TempPhongBiChuyen{" +
                "maPhongBiChuyen='" + maPhongBiChuyen + '\'' +
                ", maPhongMoi='" + maPhongMoi + '\'' +
                '}';
    }
}
