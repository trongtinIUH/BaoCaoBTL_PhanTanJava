package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import app.App_Karaoke4T;
import dao.DangNhapServices;
import dao.PhongService;
import dao.impl.DangNhap_dao_impl;
import dao.impl.PhongImpl;



public class Server {
	private static final String URL = "rmi://192.168.43.157:2081/";
	public static void main(String[] args) throws NamingException {
		try {
			PhongService phongService = new PhongImpl();
			DangNhapServices dangNhapServices = new DangNhap_dao_impl();
			
			App_Karaoke4T app_Karaoke4T = new App_Karaoke4T();
			Context context = new InitialContext();
			LocateRegistry.createRegistry(2081);
			
			context.bind(URL + "phongServices", phongService);
			context.bind(URL + "dangNhapServices", dangNhapServices);
			context.bind(URL + "app_Karaoke4T", app_Karaoke4T);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server is running...");

	}
}