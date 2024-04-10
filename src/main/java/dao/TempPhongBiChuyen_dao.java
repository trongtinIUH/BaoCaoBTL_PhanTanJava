package dao;
import entity.TempPhongBiChuyen;

import java.util.ArrayList;

public interface TempPhongBiChuyen_dao {
	public ArrayList<TempPhongBiChuyen> getAllTemp();
	public boolean addTemp(TempPhongBiChuyen tmp);
	public boolean deleteALLTempPhongBiChuyen();
	public boolean deleteTempPhongBiChuyen(String maPhong);
	public boolean updateTempPhongBiChuyen(String maPhongCu, String maPhongMoi);
}
