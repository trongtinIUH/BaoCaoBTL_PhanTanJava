package dao;
import entity.TempThanhToan;

import java.util.ArrayList;

public interface TempThanhToan_dao {
	public ArrayList<TempThanhToan> getAllTemp();

	public boolean addTemp(TempThanhToan tmp);

	public boolean deleteALLTempThanhToan();
}
