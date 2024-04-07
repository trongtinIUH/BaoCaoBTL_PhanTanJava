package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import dao.ChiTietHoaDon_dao;
import dao.HoaDonDatPhong_dao;
import dao.KhachHang_dao;
import dao.LoaiPhong_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import dao.TempDatPhong_dao;
import dao.TempPhongBiChuyen_dao;
import dao.TempThanhToan_dao;
import entity.ChiTietHoaDon;
import entity.Enum_TrangThai;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;
import utils.TempDatPhong;
import utils.TempPhongBiChuyen;
import utils.TempThanhToan;

public class Dialog_PhongDangSD extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblPhong, lblGia, lblTrangThai, lblThoiGianHat, lblSoNguoi, lblLoai, lblLoai_1, lblPhong_1, lblgia_1,
			lbltrangthai_1, lblSoNguoi_1, lblTenKH, lblTenKH_1;
	private JButton btnThemDV, btnChuyenPhong, btnThanhToan, btnThemPhong;

	private Dialog_ChuyenPhong dialog_ChuyenPhong;
	private Dialog_ThemDichVu dialog_ThemDichVu;
	private Dialog_ThanhToan dialog_ThanhToan;
	private Phong_dao p_dao = new Phong_dao();
	private LoaiPhong_dao lp_dao = new LoaiPhong_dao();
	private Phong p;
	private LoaiPhong lp;
	private PhieuDatPhong_dao phieuDatPhong_dao;
	@SuppressWarnings("unused")
	private PhieuDatPhong_dao phieuDatPhong_dao_1= new PhieuDatPhong_dao();
	private ChiTietHoaDon_dao cthd_dao;
	private Date gioHienTai;
	private Date phutHienTai;
	private double soGioHat;
	private double soPhutHat;
	private KhachHang_dao kh_dao;
	private PhieuDatPhong_dao pdp_dao = new PhieuDatPhong_dao();
	private PhieuDatPhong pdp_of_room;
	private TempDatPhong_dao tmp_dao = new TempDatPhong_dao();
	private HoaDonDatPhong_dao hd_dao = new HoaDonDatPhong_dao();
	private GD_TrangChu trangChu;
	private TempThanhToan_dao tempTT_dao;
	private Phong_dao ph_dao;
	@SuppressWarnings("unused")
	private Dialog_DatPhongTrong_2 dialog_DatPhongTrong_2;
	private String maP;
	private TempPhongBiChuyen_dao tempChuyen_dao;
	private GD_DatPhong datPhong;

	public Dialog_PhongDangSD(String maPhong, GD_DatPhong datPhong) {
		// kích thước giao diện
		maP = maPhong;
		this.datPhong = datPhong;
		getContentPane().setBackground(Color.WHITE);
		setSize(335, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		ImageIcon icon = new ImageIcon("icon\\icon_white.png");
		this.setIconImage(icon.getImage());

		phieuDatPhong_dao = new PhieuDatPhong_dao();
		cthd_dao = new ChiTietHoaDon_dao();
		kh_dao = new KhachHang_dao();
		tempTT_dao = new TempThanhToan_dao();
		ph_dao = new Phong_dao();
		tempChuyen_dao = new TempPhongBiChuyen_dao();

		// các lbl góc
		// trái-----------------------------------------------------------------------
		lblPhong = new JLabel("Phòng:");
		lblPhong.setBounds(10, 10, 100, 30);
		lblPhong.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblPhong);

		lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(10, 50, 100, 30);
		lblLoai.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblLoai);

		lblSoNguoi = new JLabel("Số người:");
		lblSoNguoi.setBounds(10, 90, 100, 30);
		lblSoNguoi.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblSoNguoi);

		lblThoiGianHat = new JLabel("Thời gian hát:");
		lblThoiGianHat.setBounds(10, 130, 130, 30);
		lblThoiGianHat.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblThoiGianHat);

		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setBounds(10, 170, 100, 30);
		lblTrangThai.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblTrangThai);

		lblGia = new JLabel("Giá phòng:");
		lblGia.setBounds(10, 250, 100, 30);
		lblGia.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblGia);

		// các lbl góc
		// phải---------------------------------------------------------------------
		lblPhong_1 = new JLabel();
		lblPhong_1.setText(maPhong);
		lblPhong_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblPhong_1.setBounds(150, 10, 140, 30);
		getContentPane().add(lblPhong_1);
		p = p_dao.getPhongTheoMaPhong(maPhong);
		lp = lp_dao.getLoaiPhongTheoMaLoaiPhong(p.getLoaiPhong().getMaLoaiPhong());

		lblLoai_1 = new JLabel();
		lblLoai_1.setText(lp.getTenLoaiPhong());
		lblLoai_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblLoai_1.setBounds(130, 50, 120, 30);
		getContentPane().add(lblLoai_1);

		pdp_of_room = null;
		ArrayList<PhieuDatPhong> dsPDP = phieuDatPhong_dao
				.getDanhsachPhieuDatPhongTheoMaPhong(lblPhong_1.getText().trim());
		for (PhieuDatPhong pdp : dsPDP) {
			pdp_of_room = pdp;
		}
		lblSoNguoi_1 = new JLabel();
		lblSoNguoi_1.setText(pdp_of_room.getSoNguoiHat() + "");
		lblSoNguoi_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblSoNguoi_1.setBounds(150, 90, 120, 30);
		getContentPane().add(lblSoNguoi_1);

		ChiTietHoaDon cthd_hienTaiCuaPhong = null;
		ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getChiTietHoaDonTheoMaPhong(lblPhong_1.getText().trim());
		for (ChiTietHoaDon cthd : dsCTHD) {
			cthd_hienTaiCuaPhong = cthd;
		}

		DateFormat dateFormatGio = new SimpleDateFormat("HH");
		gioHienTai = new Date();
		double gioHT = Double.parseDouble(dateFormatGio.format(gioHienTai));
		DateFormat dateFormatPhut = new SimpleDateFormat("mm");
		phutHienTai = new Date();
		double phutHT = Double.parseDouble(dateFormatPhut.format(phutHienTai));
		double gioNhanPhong = Double.parseDouble(dateFormatGio.format(cthd_hienTaiCuaPhong.getGioNhanPhong()));
		double phutNhanPhong = Double.parseDouble(dateFormatPhut.format(cthd_hienTaiCuaPhong.getGioNhanPhong()));

		if (gioHT >= gioNhanPhong && phutHT >= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong;
			soPhutHat = phutHT - phutNhanPhong;
		} else if (gioHT <= gioNhanPhong && phutHT >= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong + 24.0;
			soPhutHat = phutHT - phutNhanPhong;
		} else if (gioHT >= gioNhanPhong && phutHT <= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong - 1;
			soPhutHat = phutHT - phutNhanPhong + 60.0;
		} else if (gioHT <= gioNhanPhong && phutHT <= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong + 24.0 - 1.0;
			soPhutHat = phutHT - phutNhanPhong + 60.0;
		}

		DecimalFormat df = new DecimalFormat("#.#");
		lblThoiGianHat = new JLabel();
		lblThoiGianHat.setText(df.format(soGioHat) + " giờ " + df.format(soPhutHat) + " phút");
		lblThoiGianHat.setFont(new Font("Arial", Font.BOLD, 15));
		lblThoiGianHat.setBounds(150, 130, 120, 30);
		getContentPane().add(lblThoiGianHat);

		lbltrangthai_1 = new JLabel();
		lbltrangthai_1.setText("Đang dùng");
		lbltrangthai_1.setFont(new Font("Arial", Font.BOLD, 15));
		lbltrangthai_1.setBounds(150, 170, 120, 30);
		getContentPane().add(lbltrangthai_1);

		p = p_dao.getPhongTheoMaPhong(maPhong);
		lp = lp_dao.getLoaiPhongTheoMaLoaiPhong(p.getLoaiPhong().getMaLoaiPhong());

		lblgia_1 = new JLabel();
		lblgia_1.setText(lp.getDonGiaTheoGio() + "VNĐ");
		lblgia_1.setBackground(Color.WHITE);
		lblgia_1.setForeground(Color.RED);
		lblgia_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblgia_1.setBounds(150, 250, 140, 30);
		getContentPane().add(lblgia_1);

		lblTenKH = new JLabel("Khách hàng:");
		lblTenKH.setBounds(10, 210, 130, 30);
		lblTenKH.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(lblTenKH);

		KhachHang kh = kh_dao.getKhachHangTheoMaKH(pdp_of_room.getKhachHang().getMaKhachHang());
		lblTenKH_1 = new JLabel();
		lblTenKH_1.setText(kh.getHoTen());
		lblTenKH_1.setBackground(Color.WHITE);
		lblTenKH_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblTenKH_1.setBounds(150, 210, 150, 30);
		getContentPane().add(lblTenKH_1);

		// nút
		// button---------------------------------------------------------------------------
		btnThemDV = new JButton("Thêm Dịch Vụ");
		btnThemDV.setBounds(35, 290, 250, 35);
		btnThemDV.setForeground(Color.WHITE);
		btnThemDV.setFont(new Font("Arial", Font.BOLD, 17));
		btnThemDV.setBackground(new Color(33, 167, 38, 255));
		btnThemDV.setBorder(new RoundedBorder(60));
//		btnThemDV.setBorderPainted(false);
		getContentPane().add(btnThemDV);

		btnChuyenPhong = new JButton("Chuyển Phòng");
		btnChuyenPhong.setBounds(35, 370, 250, 35);
		btnChuyenPhong.setForeground(Color.WHITE);
		btnChuyenPhong.setFont(new Font("Arial", Font.BOLD, 17));
		btnChuyenPhong.setBackground(new Color(26, 147, 216, 255));
		// btnChuyenPhong.setBorderPainted(false);
		btnChuyenPhong.setBorder(new RoundedBorder(60));
		getContentPane().add(btnChuyenPhong);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setBounds(35, 410, 250, 35);
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("Arial", Font.BOLD, 17));
		btnThanhToan.setBorder(new RoundedBorder(60));
		btnThanhToan.setBackground(new Color(252, 155, 78, 255));
		// btnThanhToan.setBorderPainted(false);
		getContentPane().add(btnThanhToan);

		btnThemPhong = new JButton("Đặt Thêm Phòng");
		btnThemPhong.setForeground(Color.WHITE);
		btnThemPhong.setFont(new Font("Arial", Font.BOLD, 16));
		btnThemPhong.setBorder(new RoundedBorder(60));
		btnThemPhong.setBackground(new Color(33, 167, 38));
		btnThemPhong.setBounds(35, 330, 250, 35);
		getContentPane().add(btnThemPhong);

		// thêm sự kiện button
		btnChuyenPhong.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThemDV.addActionListener(this);
		btnThemPhong.addActionListener(this);

		
		Action chuyenPhongAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnChuyenPhong.doClick();
			}
		};

		Action themDVAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnThemDV.doClick();
			}
		};

		Action thanhToanAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnThanhToan.doClick();
			}
		};
		// Lấy InputMap và ActionMap của JPanel
		InputMap inputMap = ((JComponent) getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = ((JComponent) getContentPane()).getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK), "themDv");
		actionMap.put("themDv", themDVAction);

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), "chuyenPhong");
		actionMap.put("chuyenPhong", chuyenPhongAction);

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), "thanhToan");
		actionMap.put("thanhToan", thanhToanAction);

	}

	
	// hàm cập nhật các Jlabel góc phải
	public void updateLabel(String newText) {
		lblPhong_1.setText(newText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnChuyenPhong)) {
			dialog_ChuyenPhong = new Dialog_ChuyenPhong(lblPhong_1.getText(), lblSoNguoi_1.getText());
			dialog_ChuyenPhong.setModal(true);
			dialog_ChuyenPhong.setVisible(true);
			dispose();
		}

		if (o.equals(btnThemDV)) {
			dialog_ThemDichVu = new Dialog_ThemDichVu(lblTenKH_1.getText(), DataManager.getUserName(),
					lblPhong_1.getText());
			dialog_ThemDichVu.setModal(true);
			dialog_ThemDichVu.setVisible(true);
			dispose();
		}
		if (o.equals(btnThanhToan)) {
			if(tempTT_dao.getAllTemp().size() == 0) {
				ChiTietHoaDon cthd_hienTaiCuaPhong = null;
				ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getChiTietHoaDonTheoMaPhong(lblPhong_1.getText().trim());
				for (ChiTietHoaDon cthd : dsCTHD) {
					cthd_hienTaiCuaPhong = cthd;
				}
				HoaDonDatPhong hd = null;
				hd = hd_dao.getHoaDonTheoMaHoaDon(cthd_hienTaiCuaPhong.getHoaDon().getMaHoaDon());
				
				ArrayList<Phong> dsPhongTheoMaHoaDon = ph_dao.getPhongTheoMaCTHD(hd.getMaHoaDon());
				if(dsPhongTheoMaHoaDon.size() == 1) {
					DataManager.setMaHD_trongDSThanhToan(hd.getMaHoaDon());
					dialog_ThanhToan = new Dialog_ThanhToan(lblPhong_1.getText());
					dialog_ThanhToan.setModal(true);
					dialog_ThanhToan.setVisible(true);
					dispose();
				}else {
				
					TempThanhToan tmp = new TempThanhToan(p.getMaPhong());
					tempTT_dao.addTemp(tmp);
					JOptionPane.showMessageDialog(this, "Phòng " + p.getMaPhong() + " được thêm vào danh sách thanh toán thành công.");
					
					datPhong.txtMaPhong.setText(p.getMaPhong());
					datPhong.txtMaPhong.setForeground(Color.white);
					
					int i = 0;
					for(TempPhongBiChuyen tm_Chuyen : tempChuyen_dao.getAllTemp()) {
						if(tm_Chuyen.getMaPhongMoi().equals(lblPhong_1.getText())){
							i++;
//							System.out.println(i);
						}
					}
					
					if(i > 0) {
						ChiTietHoaDon cthd_ht = null;
						ArrayList<ChiTietHoaDon> dsCTHD_ht = cthd_dao.getChiTietHoaDonTheoMaPhong(lblPhong_1.getText());
						for (ChiTietHoaDon cthd : dsCTHD_ht) {
							cthd_ht = cthd;
						}
						HoaDonDatPhong hd_ht = hd_dao.getHoaDonTheoMaHoaDon(cthd_ht.getHoaDon().getMaHoaDon());
						
						String maCt = "";
						ArrayList<TempPhongBiChuyen> ds_PhongBiChuyen = tempChuyen_dao.getAllTemp();
						for(TempPhongBiChuyen tm : ds_PhongBiChuyen) {
							ArrayList<ChiTietHoaDon> cthd_BiChuyen = cthd_dao.getChiTietHoaDonTheoMaPhong(tm.getMaPhong());
							for(ChiTietHoaDon ct : cthd_BiChuyen) {
								maCt = ct.getHoaDon().getMaHoaDon();
							}
							if(maCt.equals(hd_ht.getMaHoaDon()) && tm.getMaPhongMoi().equals(lblPhong_1.getText())) {
								TempThanhToan tmp2 = new TempThanhToan(tm.getMaPhong());
								tempTT_dao.addTemp(tmp2);
							}
//							System.out.println(tm.getMaPhong());
					}
					
					}
					dispose();
				}
			}else {
				int i = 0;
				for(TempPhongBiChuyen tm_Chuyen : tempChuyen_dao.getAllTemp()) {
					if(tm_Chuyen.getMaPhongMoi().equals(lblPhong_1.getText())){
						i++;
//						System.out.println(i);
					}
				}
				
				if(i > 0) {
					ChiTietHoaDon cthd_ht = null;
					ArrayList<ChiTietHoaDon> dsCTHD_ht = cthd_dao.getChiTietHoaDonTheoMaPhong(lblPhong_1.getText());
					for (ChiTietHoaDon cthd : dsCTHD_ht) {
						cthd_ht = cthd;
					}
					HoaDonDatPhong hd_ht = hd_dao.getHoaDonTheoMaHoaDon(cthd_ht.getHoaDon().getMaHoaDon());

					String maCt = "";
					ArrayList<TempPhongBiChuyen> ds_PhongBiChuyen = tempChuyen_dao.getAllTemp();
					for(TempPhongBiChuyen tm : ds_PhongBiChuyen) {
						ArrayList<ChiTietHoaDon> cthd_BiChuyen = cthd_dao.getChiTietHoaDonTheoMaPhong(tm.getMaPhong());
						for(ChiTietHoaDon ct : cthd_BiChuyen) {
							maCt = ct.getHoaDon().getMaHoaDon();
						}
						if(maCt.equals(hd_ht.getMaHoaDon()) && tm.getMaPhongMoi().equals(lblPhong_1.getText())) {
							TempThanhToan tmp2 = new TempThanhToan(tm.getMaPhong());
							tempTT_dao.addTemp(tmp2);
						}
					}
				}
				
				ChiTietHoaDon cthd_hienTaiCuaPhong = null;
				ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getChiTietHoaDonTheoMaPhong(lblPhong_1.getText().trim());
				for (ChiTietHoaDon cthd : dsCTHD) {
					cthd_hienTaiCuaPhong = cthd;
				}
				
				int flag = 0;
				ChiTietHoaDon cthd_hienTaiTemp = null;
				for(TempThanhToan tmp : tempTT_dao.getAllTemp()) {
					ArrayList<ChiTietHoaDon> dsCTHDTemp = cthd_dao.getChiTietHoaDonTheoMaPhong(tmp.getMaPhong().trim());
					for (ChiTietHoaDon cthd : dsCTHDTemp) {
						cthd_hienTaiTemp = cthd;
					}
					if (cthd_hienTaiCuaPhong.getHoaDon().getMaHoaDon().equals(cthd_hienTaiTemp.getHoaDon().getMaHoaDon())) {
						flag = 1;
						break;
					}
				}
				
				if(flag == 1) {
					TempThanhToan tmp = new TempThanhToan(p.getMaPhong());
					tempTT_dao.addTemp(tmp);
					JOptionPane.showMessageDialog(this, "Phòng " + p.getMaPhong() + " được thêm vào danh sách thanh toán thành công.");
					
					dispose();
				}else if(flag == 0){
					JOptionPane.showMessageDialog(null, "Phòng này không nằm trong cùng 1 hóa đơn đặt phòng của khách hàng với phòng trước đó!!");
				}
			}
		}
		if (o.equals(btnThemPhong)) {
			tmp_dao.deleteALLTempDatPhong();
			TempDatPhong tmp = null;
			KhachHang kh = kh_dao.getKhachHangTheoMaKH(pdp_of_room.getKhachHang().getMaKhachHang());

			ArrayList<PhieuDatPhong> DsPDP_Tmp = pdp_dao.getPhieuDatPhongTheoMaKH(kh.getMaKhachHang());

			// lấy ra danh sách phiếu đặt phòng mới nhất của khách hàng nếu bị trùng phòng
			ArrayList<PhieuDatPhong> DsPDP = new ArrayList<PhieuDatPhong>();
			PhieuDatPhong pdp_tmp = new PhieuDatPhong();
			for (PhieuDatPhong pdp1 : DsPDP_Tmp) {
				int chk = 0;
				if (DsPDP.size() != 0) {
					for (PhieuDatPhong pdp2 : DsPDP) {
						if (pdp2.getPhong().getMaPhong().equals(pdp1.getPhong().getMaPhong())) {
							chk = 1;
							pdp_tmp = pdp2;
						}
					}
				}
				if (chk == 1) {
					DsPDP.remove(pdp_tmp);
					chk = 0;
				}
				DsPDP.add(pdp1);
			}

			PhieuDatPhong pdp_PhongHT = null;
			for (PhieuDatPhong pdp : DsPDP) {
				if (pdp.getPhong().getMaPhong().equals(maP))
					pdp_PhongHT = pdp;
			}

			String maHD_PhongHT = hd_dao.getHoaDonDatPhongTheoMaPDP(pdp_PhongHT.getMaPhieu()).getMaHoaDon();
			for (PhieuDatPhong pdp : DsPDP) {
				Phong p = p_dao.getPhongTheoMaPhong(pdp.getPhong().getMaPhong());
				HoaDonDatPhong hd = hd_dao.getHoaDonDatPhongTheoMaPDP(pdp.getMaPhieu());
				if (p.getTrangThai() == Enum_TrangThai.Đang_sử_dụng && hd.getMaHoaDon().equals(maHD_PhongHT)) {
					tmp = new TempDatPhong(pdp.getPhong().getMaPhong(), pdp.getSoNguoiHat());
					tmp_dao.addTemp(tmp);
				}
			}
			DataManager.setSoDienThoaiKHDat(kh.getSoDienThoai());
			dialog_DatPhongTrong_2 = new Dialog_DatPhongTrong_2(lblPhong_1.getText(), p, lp,
					pdp_of_room.getSoNguoiHat(), trangChu);
			dispose();
			JOptionPane.showMessageDialog(this, "Vui lòng chọn thêm phòng cần đặt");
		}
	}
}
