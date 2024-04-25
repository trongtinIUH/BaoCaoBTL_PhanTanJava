package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.TempThemDV;

public class DataManager {

	private static String role = "QL";
	private static String rolePassword = "QLpassword";
	private static String soDienThoaiKHDat = "";
	private static String soDienThoaiKHDatCho = "";
	private static String maPhongDatCho = "";
	private static String soNguoiHatDatCho = "";
	private static Map<String, Boolean> loadData = new HashMap<>();
	private static Map<String, String> mapIP_MSNV = new HashMap<>();
	private static ArrayList<TempThemDV> ctdvTempList;
	private static double tongTienDV;
	private static boolean loadDV = false;
	private static boolean loadSDT = false;
	private static String maHD_trongDSThanhToan;
	private static boolean timerChayTB = false;
	private static boolean loadSDTCho = false;
	private static String maNhanVien;
	private static String rmiURL = "rmi://172.20.10.2:7878/";
	private static String ipServer = "172.20.10.2";

	public static Map<String, String> getMapIP_MSNV() {
		return mapIP_MSNV;
	}
	
	public static void deleteFromMapLoadData(String key) {
        if (DataManager.loadData.containsKey(key)) {
        	DataManager.loadData.remove(key);
        }
    }
	
	public static void deleteFromMapIP_MSNV(String key) {
        if (DataManager.mapIP_MSNV.containsKey(key)) {
        	DataManager.mapIP_MSNV.remove(key);
        }
    }

	public static void setMapIP_MSNV(Map<String, String> mapIP_MSNV) {
		DataManager.mapIP_MSNV = mapIP_MSNV;
	}
	
	public static void addMapIP_MSNV(String key, String value) {
		DataManager.mapIP_MSNV.put(key, value);
	}

	public static Map<String, Boolean> getLoadData() {
		return loadData;
	}

	public static void setLoadData(Map<String, Boolean> loadData) {
		DataManager.loadData = loadData;
	}

	public static String getMaNV() {
		return maNhanVien;
	}

	public static void setMaNV(String maNhanVien) {
		DataManager.maNhanVien = maNhanVien;
	}

	public static boolean isLoadSDTCho() {
		return loadSDTCho;
	}

	public static void setLoadSDTCho(boolean loadSDTCho) {
		DataManager.loadSDTCho = loadSDTCho;
	}

	public static String getSoDienThoaiKHDatCho() {
		return soDienThoaiKHDatCho;
	}

	public static void setSoDienThoaiKHDatCho(String soDienThoaiKHDatCho) {
		DataManager.soDienThoaiKHDatCho = soDienThoaiKHDatCho;
	}

	public static String getMaPhongDatCho() {
		return maPhongDatCho;
	}

	public static void setMaPhongDatCho(String maPhongDatCho) {
		DataManager.maPhongDatCho = maPhongDatCho;
	}

	public static String getSoNguoiHatDatCho() {
		return soNguoiHatDatCho;
	}

	public static void setSoNguoiHatDatCho(String soNguoiHatDatCho) {
		DataManager.soNguoiHatDatCho = soNguoiHatDatCho;
	}

	public static boolean isTimerChayTB() {
		return timerChayTB;
	}

	public static void setTimerChayTB(boolean timerChayTB) {
		DataManager.timerChayTB = timerChayTB;
	}

	public static String getRole() {
		return role;
	}

	public static void setRole(String role) {
		DataManager.role = role;
	}

	public static String getRolePassword() {
		return rolePassword;
	}

	public static void setRolePassword(String rolePassword) {
		DataManager.rolePassword = rolePassword;
	}

	public static boolean isLoadSDT() {
		return loadSDT;
	}

	public static void setLoadSDT(boolean loadSDT) {
		DataManager.loadSDT = loadSDT;
	}

	public static boolean isLoadDV() {
		return loadDV;
	}

	public static void setLoadDV(boolean loadDV) {
		DataManager.loadDV = loadDV;
	}

	public static double getTongTienDV() {
		return tongTienDV;
	}

	public static void setTongTienDV(double tongTienDV) {
		DataManager.tongTienDV = tongTienDV;
	}

	public static ArrayList<TempThemDV> getCtdvTempList() {
		return ctdvTempList;
	}

	public static void setCtdvTempList(ArrayList<TempThemDV> ctdvTempList) {
		DataManager.ctdvTempList = ctdvTempList;
	}

	public static String getSoDienThoaiKHDat() {
		return soDienThoaiKHDat;
	}

	public static void setSoDienThoaiKHDat(String soDienThoaiKHDat) {
		DataManager.soDienThoaiKHDat = soDienThoaiKHDat;
	}

	public static String getMaHD_trongDSThanhToan() {
		return maHD_trongDSThanhToan;
	}

	public static void setMaHD_trongDSThanhToan(String maHD_trongDSThanhToan) {
		DataManager.maHD_trongDSThanhToan = maHD_trongDSThanhToan;
	}

	public static String getRmiURL() {
		return rmiURL;
	}

	public static String getIpServer() {
		return ipServer;
	}

	public static void addLoadData(String key, boolean value) {
		loadData.put(key, value);
	}
}