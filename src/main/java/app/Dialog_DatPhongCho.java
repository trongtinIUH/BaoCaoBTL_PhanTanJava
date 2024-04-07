package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import dao.KhachHang_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import entity.Enum_TrangThai;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class Dialog_DatPhongCho extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtSDT;
	private JButton btn_KiemTraSDT, btn_QuayLai, btn_DatPhong;
	private JPanel panel_1, panel_2;
	private JLabel lbl_GioiTinh_1, lbl_GiaTien_1, lbl_TenKH_1, lbl_sdtKH, lbl_GioiTinh, lbl_NgayDatPhong_1;
	private JTextField txtSoNguoi;

	private JLabel lbl_TenKH;
	private LocalDateTime now;

	private KhachHang_dao khachHang_dao = new KhachHang_dao();

	private JLabel lbl_Phong;
	private LocalDateTime now1;
	private DatePickerSettings dateSettings_1;
	private TimePickerSettings timeSettings_1;
	private DateTimePicker dateTimePicker_1;
	private JLabel lbl_TrangThai;
	private JLabel lbl_Loai;
	private JLabel lblTieuDe;
	private JLabel lbl_GiaTien;
	private JLabel lbl_SoNguoi;
	private JLabel lbl_TrangThai_1;
	private JLabel lbl_loai_1;
	private JLabel lbl_NgayDatPhong;
	private JLabel lbl_NgayNhanPhong;

	private Phong_dao phong_dao = new Phong_dao();
	private PhieuDatPhong_dao pdp_dao = new PhieuDatPhong_dao();
	private KhachHang kh = new KhachHang();
	private Date ngayHienTai;
	private Date date;
	private GD_TrangChu trangChu;
	private LocalDateTime ngayGioDatPhong;
	private LocalDateTime ngay_GioNhanPhong;

	public Dialog_DatPhongCho(String maPhong, Phong p, LoaiPhong lp, int songuoi, GD_TrangChu trangChu) {

		// màn
		// hình******************************************************************************
		this.trangChu = trangChu;
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setSize(800, 400);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("icon\\icon_white.png");
		this.setIconImage(icon.getImage());

		// panel chứa tiêu đề--------------------------------------
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 35);
		panel.setBackground(new Color(181, 230, 251));
		getContentPane().add(panel);
		panel.setLayout(null);

		lblTieuDe = new JLabel("Đặt Phòng Chờ");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.BLACK);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
		lblTieuDe.setBounds(0, 0, 784, 35);
		panel.add(lblTieuDe);

		// pane; chứa các phần còn lại---------------------------------
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 35, 784, 426);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lbl_TrangThai = new JLabel("Trạng thái:");
		lbl_TrangThai.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_TrangThai.setBounds(10, 5, 100, 25);
		panel_1.add(lbl_TrangThai);

		lbl_Loai = new JLabel("Loại:");
		lbl_Loai.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_Loai.setBounds(10, 40, 60, 25);
		panel_1.add(lbl_Loai);

		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(10, 100, 760, 40);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		lbl_sdtKH = new JLabel("SDT khách:");
		lbl_sdtKH.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sdtKH.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_sdtKH.setBounds(5, 5, 130, 30);
		panel_2.add(lbl_sdtKH);

		txtSDT = new JTextField();
		txtSDT.setHorizontalAlignment(SwingConstants.LEFT);
		txtSDT.setFont(new Font("Arial", Font.BOLD, 16));
		txtSDT.setText("0788343289");
		txtSDT.setBounds(140, 5, 300, 30);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);

		lbl_TenKH = new JLabel("Tên khách hàng:");
		lbl_TenKH.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_TenKH.setBounds(10, 150, 135, 30);
		panel_1.add(lbl_TenKH);

		lbl_GioiTinh = new JLabel("Giới tính:");
		lbl_GioiTinh.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_GioiTinh.setBounds(450, 150, 77, 30);
		panel_1.add(lbl_GioiTinh);

		lbl_GioiTinh_1 = new JLabel();
		lbl_GioiTinh_1.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_GioiTinh_1.setBounds(550, 150, 100, 30);
		panel_1.add(lbl_GioiTinh_1);

		lbl_GiaTien = new JLabel("Giá tiền:");
		lbl_GiaTien.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_GiaTien.setBounds(440, 40, 80, 25);
		panel_1.add(lbl_GiaTien);

		lbl_SoNguoi = new JLabel("Số người:");
		lbl_SoNguoi.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_SoNguoi.setBounds(440, 5, 80, 25);
		panel_1.add(lbl_SoNguoi);

		txtSoNguoi = new JTextField();
		txtSoNguoi.setText(songuoi + "");
		txtSoNguoi.setFont(new Font("Arial", Font.BOLD, 16));
		txtSoNguoi.setBounds(550, 5, 100, 25);
		panel_1.add(txtSoNguoi);
		lbl_GiaTien_1 = new JLabel(lp.getDonGiaTheoGio() + " VNĐ");
		lbl_GiaTien_1.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_GiaTien_1.setBounds(550, 40, 200, 25);
		panel_1.add(lbl_GiaTien_1);

		lbl_TenKH_1 = new JLabel();
		lbl_TenKH_1.setFont(new Font("Arial", Font.ITALIC, 17));
		lbl_TenKH_1.setBounds(165, 150, 180, 30);
		panel_1.add(lbl_TenKH_1);

		// các nút
		// jbutton-------------------------------------------------------------------
		btn_DatPhong = new JButton("Đặt Phòng");
		btn_DatPhong.setBackground(Color.GREEN);
		btn_DatPhong.setFont(new Font("Arial", Font.BOLD, 18));
		btn_DatPhong.setBackground(new Color(33, 167, 38, 255));
		btn_DatPhong.setBounds(10, 280, 160, 40);
		panel_1.add(btn_DatPhong);

		btn_QuayLai = new JButton("Quay Lại");
		btn_QuayLai.setFont(new Font("Arial", Font.BOLD, 18));
		btn_QuayLai.setBackground(new Color(255, 83, 83, 255));
		btn_QuayLai.setBounds(605, 280, 170, 40);
		panel_1.add(btn_QuayLai);

		btn_KiemTraSDT = new JButton("Kiểm Tra");
		btn_KiemTraSDT.setBackground(UIManager.getColor("Button.background"));
		btn_KiemTraSDT.setFont(new Font("Arial", Font.BOLD, 17));
		btn_KiemTraSDT.setBounds(480, 5, 200, 30);
		panel_2.add(btn_KiemTraSDT);

		lbl_TrangThai_1 = new JLabel(p.getTrangThai() + "");
		lbl_TrangThai_1.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_TrangThai_1.setBounds(140, 5, 250, 25);
		panel_1.add(lbl_TrangThai_1);

		lbl_loai_1 = new JLabel(lp.getTenLoaiPhong());
		lbl_loai_1.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_loai_1.setBounds(140, 40, 200, 25);
		panel_1.add(lbl_loai_1);

		lbl_NgayDatPhong = new JLabel("Ngày đặt  phòng:");
		lbl_NgayDatPhong.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_NgayDatPhong.setBounds(10, 190, 135, 25);
		panel_1.add(lbl_NgayDatPhong);

		now = LocalDateTime.now();
		// Tạo một DateTimeFormatter với định dạng mong muốn
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd    hh:mm a");

		// Định dạng LocalDateTime của bạn thành chuỗi
		String formattedDateTime = now.format(formatter);

		// Đặt chuỗi này làm văn bản cho JLabel của bạn
		lbl_NgayDatPhong_1 = new JLabel();
		lbl_NgayDatPhong_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_NgayDatPhong_1.setText(formattedDateTime);
		lbl_NgayDatPhong_1.setBounds(200, 190, 260, 25);
		panel_1.add(lbl_NgayDatPhong_1);

		lbl_NgayNhanPhong = new JLabel("Ngày nhận phòng:");
		lbl_NgayNhanPhong.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_NgayNhanPhong.setBounds(10, 230, 180, 25);
		panel_1.add(lbl_NgayNhanPhong);

		lbl_Phong = new JLabel(maPhong);
		lbl_Phong.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_Phong.setForeground(Color.BLACK);
		lbl_Phong.setBounds(550, 70, 150, 25);
		panel_1.add(lbl_Phong);

		now1 = LocalDateTime.now();

		dateSettings_1 = new DatePickerSettings();
		dateSettings_1.setLocale(new Locale("vi", "VN")); // Set the locale to English
		dateSettings_1.setFormatForDatesCommonEra("yyyy-MM-dd"); // Set the date format

		timeSettings_1 = new TimePickerSettings();
		timeSettings_1.setDisplaySpinnerButtons(true);

		dateTimePicker_1 = new DateTimePicker(dateSettings_1, timeSettings_1);
		dateTimePicker_1.getDatePicker().getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateTimePicker_1.getTimePicker().getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateTimePicker_1.getTimePicker().getComponentSpinnerPanel().setBounds(80, 0, 0, 25);
		dateTimePicker_1.getTimePicker().getComponentToggleTimeMenuButton().setBounds(75, 0, 26, 25);
		dateTimePicker_1.getTimePicker().getComponentTimeTextField().setBounds(0, 0, 70, 25);
		dateTimePicker_1.getTimePicker().getComponentToggleTimeMenuButton().setFont(new Font("Tahoma", Font.BOLD, 12));
		dateTimePicker_1.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.BOLD, 12));
		dateTimePicker_1.timePicker.setBounds(141, 0, 80, 25);
		dateTimePicker_1.datePicker.setBounds(0, 0, 136, 25);
		dateTimePicker_1.getTimePicker().setBounds(150, 0, 110, 25);
		dateTimePicker_1.getTimePicker().setLayout(null);
		dateTimePicker_1.getDatePicker().setBounds(0, 0, 136, 25);
		dateTimePicker_1.setDateTimePermissive(now1);
		dateTimePicker_1.setBounds(200, 230, 260, 25);
		panel_1.add(dateTimePicker_1);
		dateTimePicker_1.setLayout(null);

		JLabel lbl_MaPhong = new JLabel("Phòng:");
		lbl_MaPhong.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_MaPhong.setBounds(440, 70, 80, 25);
		panel_1.add(lbl_MaPhong);

		// thêm sự kiện button
		btn_DatPhong.addActionListener(this);
		btn_KiemTraSDT.addActionListener(this);
		btn_QuayLai.addActionListener(this);
		if (!DataManager.getSoDienThoaiKHDatCho().equals("")) {
			txtSDT.setText(DataManager.getSoDienThoaiKHDatCho());
			khachHang_dao = new KhachHang_dao();
			String sdt = txtSDT.getText();
			KhachHang khachHang = khachHang_dao.getKhachHangTheoSDT(sdt);
			if (khachHang != null) {
				String hoTen = khachHang.getHoTen();
				boolean gioiTinh = khachHang.isGioiTinh();
				String gioiTinhStr = gioiTinh ? "Nam" : "Nữ";
				lbl_GioiTinh_1.setText(gioiTinhStr);
				lbl_TenKH_1.setText(hoTen);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btn_QuayLai)) {
			setVisible(false);
		}

		if (o.equals(btn_DatPhong)) {
			khachHang_dao = new KhachHang_dao();
			String sdt = txtSDT.getText();
			KhachHang khachHang = khachHang_dao.getKhachHangTheoSDT(sdt);
			if (khachHang != null && khachHang.getHoTen().equals(lbl_TenKH_1.getText())) {
				JOptionPane.showMessageDialog(this, "Đặt phòng thành công, thời gian bắt đầu được tính !");
				DataManager.setSoDienThoaiKHDat("");
				DataManager.setDatPhongCho(true);
				DataManager.setSoDienThoaiKHDatCho("");
				DataManager.setMaPhongDatCho("");
				DataManager.setSoNguoiHatDatCho("");

				Enum_TrangThai trangThai = Enum_TrangThai.Chờ;
				Phong phong = new Phong(lbl_Phong.getText(), trangThai);
				phong_dao.updatePhong(phong, lbl_Phong.getText());

				// Tạo Phiếu đặt phòng mới
				String maPhieu = generateRandomCode();
				String maPhong = lbl_Phong.getText();
				Phong ph1 = new Phong(maPhong);
				String maNV = DataManager.getUserName();
				NhanVien nv = new NhanVien(maNV);
				kh = khachHang_dao.getKhachHangTheoSDT(sdt);
				String maKH = kh.getMaKhachHang();
				KhachHang kh2 = new KhachHang(maKH);
				ngayGioDatPhong = LocalDateTime.now();
				ngay_GioNhanPhong = dateTimePicker_1.getDateTimePermissive();
				int songuoiHat = Integer.parseInt(txtSoNguoi.getText());

				PhieuDatPhong pdp = new PhieuDatPhong(maPhieu, ph1, nv, kh2, ngayGioDatPhong, ngay_GioNhanPhong,
						songuoiHat);
				pdp_dao.addPhieuDatPhong(pdp);

				setVisible(false);
			} else if (khachHang != null && !khachHang.getHoTen().equals(lbl_TenKH_1.getText())) {
				JOptionPane.showMessageDialog(this, "Bạn chưa kiểm tra số điện thoại khách hàng");
			} else {
				JOptionPane.showMessageDialog(this,
						"Khách hàng phải có thông tin trên hệ thống mới được phép đặt phòng trước!");
			}

		}

		// kiem tra khach hang
		if (o.equals(btn_KiemTraSDT))

		{
			khachHang_dao = new KhachHang_dao();
			String sdt = txtSDT.getText();
			KhachHang khachHang = khachHang_dao.getKhachHangTheoSDT(sdt);
			if (khachHang != null) {
				String hoTen = khachHang.getHoTen();
				boolean gioiTinh = khachHang.isGioiTinh();
				String gioiTinhStr = gioiTinh ? "Nam" : "Nữ";
				lbl_GioiTinh_1.setText(gioiTinhStr);
				lbl_TenKH_1.setText(hoTen);
			} else {
				int checkCustomer = JOptionPane.showConfirmDialog(this,
						"Khách hàng chưa có trên hệ thống! Bạn có muốn thêm khách hàng không?");
				DataManager.setSoDienThoaiKHDatCho(sdt);
				DataManager.setMaPhongDatCho(lbl_Phong.getText());
				DataManager.setSoNguoiHatDatCho(txtSoNguoi.getText());
				DataManager.setLoadSDTCho(true);
				if (checkCustomer == JOptionPane.YES_OPTION) {
					trangChu.showKhachHangCard();
					setVisible(false);
				}
			}
		}

	}

	// ---- Mã PhieuDatPhong phát sinh tự động tăng dần bắt đầu từ 0001
	private int ThuTuPDPTrongNgay() {
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
		String suffix = String.format("%04d", ThuTuPDPTrongNgay());
		return prefix + dateFormat.format(date) + suffix;
	}
}
