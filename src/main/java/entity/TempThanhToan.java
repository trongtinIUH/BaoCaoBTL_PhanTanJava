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
                name = "TempThanhToan.getAllTemp",
                query = "select * from TempThanhToan", resultClass = TempThanhToan.class),
        @NamedNativeQuery(name="TempThanhToan.addTemp",
                query = "insert into TempThanhToan values(?)", resultClass = TempThanhToan.class),
        @NamedNativeQuery(name="TempThanhToan.deleteALLTempThanhToan",
                query = "delete TempThanhToan where maPhong <> '000'", resultClass = TempThanhToan.class),

})
public class TempThanhToan implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3104681637952995224L;
	@Id
    @Column(name = "maPhong", columnDefinition = "varchar(20)", nullable = false)
    private String maPhong;

    public TempThanhToan() {
    }

    public TempThanhToan(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    @Override
    public String toString() {
        return "TempThanhToan{" +
                "maPhong='" + maPhong + '\'' +
                '}';
    }
}
