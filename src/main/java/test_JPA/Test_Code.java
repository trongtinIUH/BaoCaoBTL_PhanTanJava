package test_JPA;

import java.rmi.RemoteException;

import dao.ChiTietDichVuServices;
import dao.impl.ChiTietDichVu_dao_impl;


public class Test_Code {
public static void main(String[] args) throws RemoteException {
	ChiTietDichVuServices ctdv = new ChiTietDichVu_dao_impl();
	System.out.println(ctdv.getAllChiTietDichVu());
	//System.out.println(ctdv.getChiTietDichVuTheoMaHD("HD2007150001"));
}
}
