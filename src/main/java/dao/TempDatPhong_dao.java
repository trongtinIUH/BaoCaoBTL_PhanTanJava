package dao;

import entity.TempDatPhong;

import java.util.ArrayList;

public interface TempDatPhong_dao {
	public  ArrayList<TempDatPhong> getAllTemp();
	public boolean addTemp(TempDatPhong tempDatPhong);
	public boolean deleteALLTempDatPhong();
	public boolean deleteTempDP(String maDP);
	public boolean updateTempDP(String maPhong, int soNguoi);
}
