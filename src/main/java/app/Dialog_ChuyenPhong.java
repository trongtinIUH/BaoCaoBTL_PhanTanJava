package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietDichVu_dao;
import dao.ChiTietHoaDon_dao;
import dao.HoaDonDatPhong_dao;
import dao.KhachHang_dao;
import dao.LoaiPhong_dao;
import dao.NhanVien_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import dao.TempPhongBiChuyen_dao;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.Enum_TrangThai;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.SanPham;
import utils.TempPhongBiChuyen;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Dialog_ChuyenPhong extends JDialog implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox_LoaiPhong;
	private JTextField txtMaPhong;
	private JButton btnTimKiem, btn_ChuyenPhong, btn_QuayLai;
	private JPanel panel_2, panel, panel_3;
	private JTable tblChuyenPhong;
	private DefaultTableModel model;
	private String col[] = { "Mã Phòng", "Loại Phòng", "Sức Chứa", "Đơn Giá", "Trạng Thái" };
	private Phong_dao ph_dao;
	private LoaiPhong_dao loaiPhong_dao;
	private JLabel lblPhongHienTai_1_1;
	private JPanel panel_1;
	private JLabel lblPhongHT;
	private JTextField txtMa;
	private JLabel lblSoNguoi;
	private JLabel lblTGHat;
	private JTextField txtNguoi;
	private JTextField txtTGHat;
	private Date gioHienTai;
	private Date phutHienTai;
	private Date tgHT;
	private double soGioHat;
	private double soPhutHat;
	private ChiTietHoaDon_dao cthd_dao;
	private PhieuDatPhong_dao pdp_dao;
	private Date ngayHienTai;
	private Date date;
	private JLabel lblMaNV;
	private JTextField txtMaNV;
	private JLabel lblMaKH;
	private JTextField txtMaKH;
	private NhanVien_dao nv_dao;
	private HoaDonDatPhong_dao hd_dao;
	private KhachHang_dao kh_dao;
	private LocalDateTime ngayGioDatPhong;
	private LocalDateTime ngay_GioNhanPhong;
	private String loaiPhong;
	private JLabel lblPhongHienTai_1;
	private TempPhongBiChuyen_dao tempChuyen_dao;
	private ChiTietDichVu_dao ctdv_dao;
	public Dialog_ChuyenPhong(String maPhong, String soNguoi) {
		getContentPane().setBackground(Color.WHITE);
		setSize(800, 480);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		ImageIcon icon = new ImageIcon("icon\\icon_white.png");
	    this.setIconImage(icon.getImage());
	    
		ph_dao = new Phong_dao();
		loaiPhong_dao = new LoaiPhong_dao();
		cthd_dao = new ChiTietHoaDon_dao();
		pdp_dao = new PhieuDatPhong_dao();
		nv_dao = new NhanVien_dao();
		hd_dao = new HoaDonDatPhong_dao();
		kh_dao = new KhachHang_dao();
		tempChuyen_dao = new TempPhongBiChuyen_dao();
		ctdv_dao = new ChiTietDichVu_dao();
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				NhanVien nv = null;
				nv = nv_dao.getNhanVienTheoMa(DataManager.getUserName());
				txtMaNV.setText(nv.getMaNhanVien());
			}
		});

		// panel chứa tiêu đề--------------------------------------
		panel = new JPanel();
		panel.setBounds(0, 0, 784, 35);
		panel.setBackground(new Color(181, 230, 251));
		getContentPane().add(panel);
		panel.setLayout(null);
		getContentPane().add(panel);

		JLabel lblTieuDe = new JLabel("Chuyển  Phòng");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLACK);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
		lblTieuDe.setBounds(0, 0, 790, 35);
		panel.add(lblTieuDe);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 34, 784, 120);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(242, 240, 255));

		panel_1.add(lblMaNV = new JLabel("Mã nhân viên: "));
		lblMaNV.setBounds(10, 20, 130, 30);
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblMaNV);

		panel_1.add(txtMaNV = new JTextField());
		txtMaNV.setBounds(140, 20, 110, 30);
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(txtMaNV);

		panel_1.add(lblMaKH = new JLabel("Mã khách hàng: "));
		lblMaKH.setBounds(280, 20, 130, 30);
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblMaKH);

		ChiTietHoaDon cthd_hienTaiCuaPhong2 = null;
		ArrayList<ChiTietHoaDon> dsCTHD2 = cthd_dao.getChiTietHoaDonTheoMaPhong(maPhong);
		for (ChiTietHoaDon cthd : dsCTHD2) {
			cthd_hienTaiCuaPhong2 = cthd;
		}

		HoaDonDatPhong hd = null;
		hd = hd_dao.getHoaDonTheoMaHoaDon(cthd_hienTaiCuaPhong2.getHoaDon().getMaHoaDon());
		KhachHang kh1 = null;
		kh1 = kh_dao.getKhachHangTheoMaKH(hd.getKhachHang().getMaKhachHang());

		panel_1.add(txtMaKH = new JTextField(kh1.getMaKhachHang()));
		txtMaKH.setBounds(405, 20, 150, 30);
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(txtMaKH);

		panel_1.add(lblPhongHT = new JLabel("Phòng hiện tại: "));
		lblPhongHT.setBounds(10, 70, 130, 30);
		lblPhongHT.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblPhongHT);

		panel_1.add(txtMa = new JTextField(maPhong));
		txtMa.setBounds(140, 70, 110, 30);
		txtMa.setEditable(false);
		txtMa.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(txtMa);

		panel_1.add(lblSoNguoi = new JLabel("Số người: "));
		lblSoNguoi.setBounds(280, 70, 80, 30);
		lblSoNguoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblSoNguoi);

		panel_1.add(txtNguoi = new JTextField(soNguoi));
		txtNguoi.setBounds(360, 70, 100, 30);
		txtNguoi.setEditable(false);
		txtNguoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(txtNguoi);

		panel_1.add(lblTGHat = new JLabel("Thời gian đã hát: "));
		lblTGHat.setBounds(485, 70, 135, 30);
		lblTGHat.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblTGHat);

		ChiTietHoaDon cthd_hienTaiCuaPhong = null;
		ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getChiTietHoaDonTheoMaPhong(txtMa.getText());
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
		
		if(gioHT >= gioNhanPhong && phutHT >= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong;
			soPhutHat = phutHT - phutNhanPhong;
		}else if(gioHT <= gioNhanPhong && phutHT >= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong + 24.0;
			soPhutHat = phutHT - phutNhanPhong;
		}else if(gioHT >= gioNhanPhong && phutHT <= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong - 1;
			soPhutHat = phutHT - phutNhanPhong + 60.0;
		}else if(gioHT <= gioNhanPhong && phutHT <= phutNhanPhong) {
			soGioHat = gioHT - gioNhanPhong + 24.0 - 1.0;
			soPhutHat = phutHT - phutNhanPhong + 60.0;
		}
		DecimalFormat df = new DecimalFormat("#.#");
		panel_1.add(txtTGHat = new JTextField(df.format(soGioHat) + " giờ " + df.format(soPhutHat) + " phút"));

		txtTGHat.setBounds(620, 70, 150, 30);
		txtTGHat.setEditable(false);
		txtTGHat.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(txtTGHat);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 154, 784, 58);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblLoiPhng = new JLabel("Loại Phòng");
		lblLoiPhng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoiPhng.setBounds(10, 10, 90, 25);
		panel_2.add(lblLoiPhng);

		comboBox_LoaiPhong = new JComboBox<String>();
		comboBox_LoaiPhong.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Phòng Vip", "Phòng Thường" }));
		comboBox_LoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox_LoaiPhong.setBackground(Color.WHITE);
		comboBox_LoaiPhong.setBounds(110, 10, 200, 30);
		panel_2.add(comboBox_LoaiPhong);

		JLabel lblMaPhong = new JLabel("Mã Phòng");
		lblMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaPhong.setBounds(330, 10, 80, 30);
		panel_2.add(lblMaPhong);

		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMaPhong.setColumns(10);
		txtMaPhong.setBounds(420, 10, 110, 30);
		panel_2.add(txtMaPhong);

		// --- cuối góc phải là 3 nút jbutton
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimKiem.setIcon(new ImageIcon("icon\\Research_icon.png"));
		btnTimKiem.setBounds(570, 10, 200, 35);
		btnTimKiem.setBackground(new Color(13, 153, 255, 255));
		panel_2.add(btnTimKiem);

		panel_3 = new JPanel();
		panel_3.setBounds(0, 210, 784, 231);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		// bảng chuyển phòng
		model = new DefaultTableModel(col, 0);
		tblChuyenPhong = new JTable(model);
		tblChuyenPhong.setFont(new Font("Arial", Font.PLAIN, 12));
		tblChuyenPhong.setBackground(Color.WHITE);
		JScrollPane sp = new JScrollPane(tblChuyenPhong);
		sp.setBounds(0, 0, 784, 140);
		panel_3.add(sp);
		panel_3.setPreferredSize(new Dimension(800, 300));
		getContentPane().add(panel_3);

		JLabel lblPhongHienTai = new JLabel("Phòng hiện tại:");
		lblPhongHienTai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPhongHienTai.setBounds(5, 145, 105, 20);
		panel_3.add(lblPhongHienTai);

		lblPhongHienTai_1 = new JLabel(maPhong);
		lblPhongHienTai_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPhongHienTai_1.setBounds(115, 145, 30, 20);
		panel_3.add(lblPhongHienTai_1);

		JLabel lblChuyenDen = new JLabel("--->");
		lblChuyenDen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChuyenDen.setBounds(155, 145, 40, 20);
		panel_3.add(lblChuyenDen);

		lblPhongHienTai_1_1 = new JLabel("...");
		lblPhongHienTai_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhongHienTai_1_1.setBounds(200, 145, 30, 20);
		panel_3.add(lblPhongHienTai_1_1);

		// các nút
		// jbutton-------------------------------------------------------------------
		btn_ChuyenPhong = new JButton("Chuyển Phòng");
		btn_ChuyenPhong.setBackground(Color.GREEN);
		btn_ChuyenPhong.setForeground(Color.WHITE);
		btn_ChuyenPhong.setFont(new Font("Arial", Font.BOLD, 18));
		btn_ChuyenPhong.setBackground(new Color(33, 167, 38, 255));
		btn_ChuyenPhong.setBounds(33, 176, 250, 40);
		panel_3.add(btn_ChuyenPhong);

		btn_QuayLai = new JButton("Quay Lại");
		btn_QuayLai.setForeground(Color.WHITE);
		btn_QuayLai.setFont(new Font("Arial", Font.BOLD, 18));
		btn_QuayLai.setBackground(new Color(255, 83, 83, 255));
		btn_QuayLai.setBounds(500, 176, 250, 40);
		panel_3.add(btn_QuayLai);

		// thêm sự kiện
		btnTimKiem.addActionListener(this);
		btn_ChuyenPhong.addActionListener(this);
		btn_QuayLai.addActionListener(this);
		tblChuyenPhong.addMouseListener(this);
//		cb

		loadData_Phong();

	}

	private void clearTable() {
		while (tblChuyenPhong.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	private void loadData_Phong() {
		for (Phong ph : ph_dao.getallPhongs()) {
			if (Integer.parseInt(txtNguoi.getText().trim()) <= loaiPhong_dao
					.getSucChuaTheoMaLoaiPhong(ph.getLoaiPhong().getMaLoaiPhong())
					&& (ph.getTrangThai() == Enum_TrangThai.Trống)) {
				Object[] row = { ph.getMaPhong(),
						loaiPhong_dao.getTenLoaiPhongTheoMaLoaiPhong(ph.getLoaiPhong().getMaLoaiPhong()),
						loaiPhong_dao.getSucChuaTheoMaLoaiPhong(ph.getLoaiPhong().getMaLoaiPhong()),
						loaiPhong_dao.getDonGiaTheoMaLoaiPhong(ph.getLoaiPhong().getMaLoaiPhong()), ph.getTrangThai() };
				model.addRow(row);
			}
		}
	}

	@SuppressWarnings("unused")
	private void timKiemPhong() {
		thoat: if(btnTimKiem.getText().equals("Tìm kiếm")) {
			ArrayList<Phong> dsPhong = new ArrayList<Phong>();
			loaiPhong = comboBox_LoaiPhong.getSelectedItem().toString();
			if (txtMaPhong.getText().trim().equals("") && comboBox_LoaiPhong.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập bất kì thông tin nào để tìm");
			} else {
				if (!txtMaPhong.getText().trim().equals("")) {
					if (ph_dao.getPhongTheoMaPhong(txtMaPhong.getText()) != null) {
						dsPhong.add(ph_dao.getPhongTheoMaPhong(txtMaPhong.getText()));
					}
				}else if (!comboBox_LoaiPhong.getSelectedItem().toString().equals("")) {
					dsPhong = ph_dao.getPhongTheoLoaiPhong(loaiPhong);
				}
			}
			
			// Kiểm tra điều kiện tìm thấy hay không
			if (dsPhong != null && dsPhong.size() != 0) {
				clearTable();
				for (Phong ph2 : dsPhong) {
					if (Integer.parseInt(txtNguoi.getText().trim()) <= loaiPhong_dao.getSucChuaTheoMaLoaiPhong(ph2.getLoaiPhong().getMaLoaiPhong())
							&& ph2.getTrangThai() == Enum_TrangThai.Trống){
						Object[] row = { ph2.getMaPhong(),
								loaiPhong_dao.getTenLoaiPhongTheoMaLoaiPhong(ph2.getLoaiPhong().getMaLoaiPhong()),
								loaiPhong_dao.getSucChuaTheoMaLoaiPhong(ph2.getLoaiPhong().getMaLoaiPhong()),
								loaiPhong_dao.getDonGiaTheoMaLoaiPhong(ph2.getLoaiPhong().getMaLoaiPhong()),
								ph2.getTrangThai() };
						model.addRow(row);
					}
				}
				btnTimKiem.setText("Hủy tìm");
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp");
			}
		}
		else {
			clearTable();
			loadData_Phong();
			btnTimKiem.setText("Tìm kiếm");
		}
	}

	private void chuyenPhong() {
		int flag = 0;
		ChiTietHoaDon cthd_hienTaiCuaPhong = null;
		ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getChiTietHoaDonTheoMaPhong(txtMa.getText());
		for (ChiTietHoaDon cthd : dsCTHD) {
			cthd_hienTaiCuaPhong = cthd;
		}
		ChiTietHoaDon cthd_hienTai_Item = null;
		ArrayList<ChiTietHoaDon> dsCTHD_Item = cthd_dao.getChiTietHoaDonTheoMaPhong(model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString());
		if(dsCTHD_Item != null) {
			for (ChiTietHoaDon cthd : dsCTHD_Item) {
				cthd_hienTai_Item = cthd;
			}
			if(cthd_hienTai_Item != null) {
				if(cthd_hienTai_Item.getHoaDon().getMaHoaDon().equals(cthd_hienTaiCuaPhong.getHoaDon().getMaHoaDon())) {
					flag = 1;
				}
			}
		}
		if(tblChuyenPhong.getSelectedRow() == -1)
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng để chuyển!!");
		else if(tblChuyenPhong.getSelectedRowCount() > 1)
			JOptionPane.showMessageDialog(null, "Chỉ được chọn 1 phòng để chuyển!!");
		else if(txtMa.getText().equals("")) {
			
		}
		else {
			if(flag == 1) {
				JOptionPane.showMessageDialog(null, "Phòng này đã nằm trong danh sách đặt trước đó, không thể chuyển!!");
			}else {
				if (JOptionPane.showConfirmDialog(null,
						"Bạn có chắc chắn muốn chuyển sang Phòng " + model.getValueAt(tblChuyenPhong.getSelectedRow(), 0),
						"Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maPhongCu1 = txtMa.getText().trim();
					Enum_TrangThai trangThaiPhongCu = Enum_TrangThai.Trống;
					Phong phongCu = new Phong(maPhongCu1, trangThaiPhongCu);

					String maPhongMoi1 = model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString();
					Enum_TrangThai trangThaiPhongMoi = Enum_TrangThai.Đang_sử_dụng;
					Phong phongMoi = new Phong(maPhongMoi1, trangThaiPhongMoi);

					ph_dao.updatePhong(phongCu, maPhongCu1);
					ph_dao.updatePhong(phongMoi, maPhongMoi1);
					
					// update lại giờ trả phòng của phòng cũ
					tgHT = new Date();
					Timestamp ngayGioTraPhong = new Timestamp(tgHT.getTime());
					double thoiGianHat = soGioHat + soPhutHat / 60;

					ChiTietHoaDon cthd_cu = null;
					ArrayList<ChiTietHoaDon> dsCTHD1 = cthd_dao.getChiTietHoaDonTheoMaPhong(txtMa.getText());
					for (ChiTietHoaDon cthd : dsCTHD1) {
						//if(cthd.getPhong().getMaPhong().equals(txtMa.getText())`) {
							cthd_cu = cthd;
						//}
					}

					String maHD = cthd_cu.getHoaDon().getMaHoaDon();
					String maPhongCu2 = cthd_cu.getPhong().getMaPhong();
					Timestamp ngayGioNhanPhong = cthd_cu.getGioNhanPhong();
					HoaDonDatPhong hd = new HoaDonDatPhong(maHD);
					Phong ph = new Phong(maPhongCu2);
					ChiTietHoaDon cthd_cu2 = new ChiTietHoaDon(hd, ph, ngayGioNhanPhong, ngayGioTraPhong, thoiGianHat);
					cthd_dao.UpdateChiTietHD_ChuyenPhong(cthd_cu2);

					// Tạo chi tiêt hóa đơn cho phòng mới
					String maPhongMoi2 = model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString();
					Phong ph_Moi = new Phong(maPhongMoi2);
					ChiTietHoaDon cthd_moi = new ChiTietHoaDon(hd, ph_Moi, ngayGioTraPhong, null, 0);
					cthd_dao.addChiTietHD(cthd_moi);

					// Tạo Phiếu đặt phòng mới
					String maPhieu = generateRandomCode();
					String maPhong = model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString();
					Phong ph1 = new Phong(maPhong);
					String maNV = txtMaNV.getText();
					NhanVien nv = new NhanVien(maNV);
					String maKH = txtMaKH.getText();
					KhachHang kh = new KhachHang(maKH);
					ngayGioDatPhong = LocalDateTime.now();
					ngay_GioNhanPhong = LocalDateTime.now();
					int songuoiHat = Integer.parseInt(txtNguoi.getText());

					PhieuDatPhong pdp = new PhieuDatPhong(maPhieu, ph1, nv, kh, ngayGioDatPhong, ngay_GioNhanPhong, songuoiHat);
					pdp_dao.addPhieuDatPhong(pdp);
					
					JOptionPane.showMessageDialog(null,
							"Chuyển sang phòng " + model.getValueAt(tblChuyenPhong.getSelectedRow(), 0) + " thành công!!");
					DataManager.setChuyenPhong(true);	
					

					//Chuyển dịch vụ sang phòng mới
					ArrayList<ChiTietDichVu> dsChiTietDV = ctdv_dao.getChiTietDichVuTheoMaHDVaMaPhong(maHD, txtMa.getText());
					if(dsChiTietDV != null) {
						for(ChiTietDichVu ctdv : dsChiTietDV) {
							ctdv_dao.deleteChiTietDV2(maHD, ctdv.getSanPham().getMaSanPham(), txtMa.getText());
							
							Phong phongMoi2 = new Phong(model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString());
							SanPham spMoi_Item = new SanPham(ctdv.getSanPham().getMaSanPham());
							int sl = ctdv.getSoLuong();
							double giaSP = ctdv.getGia(); 
							ChiTietDichVu ct = new ChiTietDichVu(hd,phongMoi2, spMoi_Item, sl, giaSP);
							ctdv_dao.addChiTietDV(ct);
						}
					}
					
					TempPhongBiChuyen tmp_Chuyen = new TempPhongBiChuyen(lblPhongHienTai_1.getText(), model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString());
					for(TempPhongBiChuyen tmp_BiChuyen : tempChuyen_dao.getAllTemp()) {
						if(tmp_BiChuyen.getMaPhongMoi().equals(lblPhongHienTai_1.getText())) {
							tempChuyen_dao.updateTempPhongBiChuyen(tmp_BiChuyen.getMaPhong(), model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString());
						}
					}
					tempChuyen_dao.addTemp(tmp_Chuyen);
					dispose();
				}
			}
		}
	}

	// ---- Mã PhieuDatPhong phát sinh tự động tăng dần bắt đầu từ 0001
	private int ThuTuPhieuDatPhongTrongNgay() {
		int sl = 1;
		String maPDP = "";
		for (PhieuDatPhong pdp : pdp_dao.getAllsPhieuDatPhong()) {
			maPDP = pdp.getMaPhieu(); // Chạy hết vòng for sẽ lấy được mã Phiếu đặt phòng cuối danh sách
		}
		int ngayTrenMaPDPCuoiDS = Integer.parseInt(maPDP.substring(3, 9));
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd"); // Format yyMMdd sẽ so sánh ngày được
		ngayHienTai = new Date();
		int ngayHT = Integer.parseInt(dateFormat.format(ngayHienTai));
		if (ngayHT != ngayTrenMaPDPCuoiDS) {
			sl = 1;
		} else if (ngayHT == ngayTrenMaPDPCuoiDS) {
			sl = Integer.parseInt(maPDP.substring(9, 13)) + 1;
		}
		return sl;
	}

	private String generateRandomCode() {
		String prefix = "PDP";
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		date = new Date();
		String suffix = String.format("%04d", ThuTuPhieuDatPhongTrongNgay());
		return prefix + dateFormat.format(date) + suffix;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btn_QuayLai)) {
			setVisible(false);
		} else if (o.equals(btnTimKiem)) {
			timKiemPhong(); 
		} else if (o.equals(btn_ChuyenPhong)) {
			chuyenPhong();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		lblPhongHienTai_1_1.setText(model.getValueAt(tblChuyenPhong.getSelectedRow(), 0).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
