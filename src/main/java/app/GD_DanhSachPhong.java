package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.Phong_dao;
import entity.Enum_TrangThai;
import entity.LoaiPhong;
import entity.Phong;
import dao.LoaiPhong_dao;

public class GD_DanhSachPhong extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("Arial", Font.BOLD, 16); // khung tittle
	Font font2 = new Font("Arial", Font.BOLD, 18); // thuộc tính
	Font font3 = new Font("Arial", Font.PLAIN, 18); // jtexfield
	private String col[] = { "STT", "Mã phòng", "Loại phòng", "Trạng thái", "Sức chứa", "Đơn giá" };
	private JPanel pnNorth;
	private JLabel lblTitle;
	private JComboBox<String> cbLoaiTim;
	private JTextField txtTuKhoaTim;
	private JButton btnTimKiem;
	private JButton btnXuatExcel;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private JComboBox<String> cbLoaiPhong;
	private JComboBox<String> cbTrangThai;
	private JTextField txtSucChua;
	private JTextField txtDonGia;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private Phong_dao p_dao;
	private LoaiPhong_dao lp_dao = new LoaiPhong_dao();
	private XSSFWorkbook wordbook;
	private JComboBox<String> cbLau;
	private JTextField txtMa;
	private JComboBox<String> cbSoPhong;

	private JButton btnUser;
	private Dialog_User dialog_user = new Dialog_User();

	public GD_DanhSachPhong() {
		setBackground(new Color(246, 245, 255));
		setLayout(null);

		pnNorth = new JPanel();
		pnNorth.setLayout(null);
		pnNorth.setBounds(0, 0, 1078, 60);
		pnNorth.setBackground(new Color(187, 231, 252));
		add(pnNorth);
		lblTitle = new JLabel("PHÒNG");
		pnNorth.add(lblTitle);

		// ---nút user
		btnUser = new JButton();
		btnUser.setBackground(Color.decode("#B5E6FB"));
		btnUser.setBorderPainted(false);
		btnUser.setIcon(new ImageIcon("icon\\icon_profile.png"));
		btnUser.setBounds(1020, 7, 45, 45);
		ImageIcon iconProfile = new ImageIcon("icon\\icon_profile.png");
		iconProfile = new ImageIcon(iconProfile.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
		btnUser.setIcon(iconProfile);
		pnNorth.add(btnUser);

		// căn giữa title
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);

		// Đặt kích thước và tọa độ cho lblTitle
		int labelWidth = 500; // Thay đổi kích thước theo ý muốn
		int labelHeight = 40; // Thay đổi kích thước theo ý muốn
		int labelX = (pnNorth.getWidth() - labelWidth) / 2; // Căn giữa theo chiều ngang
		int labelY = (pnNorth.getHeight() - labelHeight) / 2; // Căn giữa theo chiều dọc
		lblTitle.setBounds(labelX, labelY, labelWidth, labelHeight);
		pnNorth.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));

		// Khung thông tin phòng
		JPanel pnSouth = new JPanel();
		pnSouth.setBackground(new Color(255, 255, 255));
		pnSouth.setBounds(7, 80, 690, 300);
		pnSouth.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Thông tin phòng",
						TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 16), Color.blue));
		TitledBorder titlethongtin = (TitledBorder) pnSouth.getBorder();
		titlethongtin.setTitleColor(Color.blue);
		titlethongtin.setTitleFont(font);
		pnSouth.setLayout(null);
		add(pnSouth);

		JLabel lblMa = new JLabel("Mã phòng");
		pnSouth.add(lblMa);
		lblMa.setBounds(20, 20, 150, 100);
		lblMa.setFont(font2);

		int x = 160, y = 55, w = 180, h = 28;
		txtMa = new JTextField();
		txtMa.setEditable(false);
		txtMa.setBounds(x, y, w, h);
		pnSouth.add(txtMa);
		txtMa.setHorizontalAlignment(JTextField.RIGHT);
		txtMa.setFont(font3);

		JLabel lblLoaiPhong = new JLabel("Loại phòng");
		lblLoaiPhong.setBounds(20, 70, 120, 100);
		lblLoaiPhong.setFont(font2);
		pnSouth.add(lblLoaiPhong);

		cbLoaiPhong = new JComboBox<String>();
		cbLoaiPhong.setFont(font3);
		y += 50;
		cbLoaiPhong.setBounds(x, y, w, h);
		cbLoaiPhong.addItem("Phòng thường");
		cbLoaiPhong.addItem("Phòng VIP");
		pnSouth.add(cbLoaiPhong);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setBounds(20, 120, 150, 100);
		lblTrangThai.setFont(font2);
		pnSouth.add(lblTrangThai);

		cbTrangThai = new JComboBox<String>();
		cbTrangThai.setFont(font3);
		y += 50;
		cbTrangThai.setBounds(x, y, w, h);
		cbTrangThai.addItem("Đang sử dụng");
		cbTrangThai.addItem("Trống");
		cbTrangThai.addItem("Đang sửa chữa");
		cbTrangThai.addItem("Chờ");
		pnSouth.add(cbTrangThai);

		JLabel lblSucChua = new JLabel("Sức chứa");
		y += 50;
		lblSucChua.setBounds(20, y, w, h);
		lblSucChua.setFont(font2);
		pnSouth.add(lblSucChua);

		txtSucChua = new JTextField();
		txtSucChua.setBounds(x, y, w, h);
		txtSucChua.setFont(font3);
		pnSouth.add(txtSucChua);

		x = 380;
		y = 55;
		w = 100;
		h = 30;
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setBounds(x, y, w, h);
		lblDonGia.setFont(font2);
		pnSouth.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(x + 110, y, w + 80, h);
		pnSouth.add(txtDonGia);
		txtDonGia.setHorizontalAlignment(JTextField.RIGHT);
		txtDonGia.setFont(font3);

		JLabel lblLau = new JLabel("Lầu");
		y += 50;
		lblLau.setBounds(x, y, w, h);
		lblLau.setFont(font2);
		pnSouth.add(lblLau);

		cbLau = new JComboBox<String>();
		cbLau.setFont(font3);
		cbLau.setBounds(x + 110, y, w + 80, h);
		cbLau.addItem("1");
		cbLau.addItem("2");
		cbLau.addItem("3");
		cbLau.addItem("4");
		cbLau.addItem("5");
		cbLau.addItem("6");
		cbLau.addItem("7");
		cbLau.addItem("8");
		cbLau.addItem("9");
		cbLau.setMaximumRowCount(4);
		pnSouth.add(cbLau);

		JLabel lblSoPhong = new JLabel("Số phòng");
		lblSoPhong.setFont(font2);
		y += 50;
		lblSoPhong.setBounds(x, y, w, h);
		pnSouth.add(lblSoPhong);

		String[] items = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20" };
		cbSoPhong = new JComboBox<>(items);
		cbSoPhong.setFont(font3);
		cbSoPhong.setBounds(x + 110, y, w + 80, h);
		cbSoPhong.setMaximumRowCount(4);
		pnSouth.add(cbSoPhong);

		// Các nút
		pnSouth.add(btnThem = new JButton("THÊM", new ImageIcon("icon\\Add_icon.png")));
		btnThem.setFont(font);
		btnThem.setBounds(60, 250, 130, 35);
		btnThem.setBackground(new Color(109, 197, 112));
		btnThem.setBorder(new RoundedBorder(5));
		pnSouth.add(btnXoa = new JButton("XÓA", new ImageIcon("icon\\Delete_icon.png")));
		btnXoa.setFont(font);
		btnXoa.setBounds(208, 250, 130, 35);
		btnXoa.setBackground(new Color(228, 50, 51));
		btnXoa.setBorder(new RoundedBorder(5));
		pnSouth.add(btnSua = new JButton("SỬA", new ImageIcon("icon\\Edit_icon.png")));
		btnSua.setFont(font);
		btnSua.setBounds(356, 250, 130, 35);
		btnSua.setBackground(new Color(74, 131, 215));
		btnSua.setBorder(new RoundedBorder(5));
		pnSouth.add(btnLamMoi = new JButton("LÀM MỚI", new ImageIcon("icon\\Refresh_icon.png")));
		btnLamMoi.setFont(font);
		btnLamMoi.setBounds(504, 250, 130, 35);
		btnLamMoi.setBackground(new Color(104, 211, 211));
		btnLamMoi.setBorder(new RoundedBorder(5));

		// khung tìm kiếm
		JPanel pnEast = new JPanel();
		pnEast.setBackground(new Color(255, 255, 255));
		pnEast.setBounds(707, 80, 364, 300);
		pnEast.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
				"Tìm kiếm", TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 16), Color.blue));
		TitledBorder titleTimKiem = (TitledBorder) pnEast.getBorder();
		titleTimKiem.setTitleColor(Color.blue);
		titleTimKiem.setTitleFont(font);
		pnEast.setLayout(null);
		add(pnEast);

		JLabel lblLoaiTim = new JLabel("Tìm kiếm theo");
		lblLoaiTim.setFont(font2);
		pnEast.add(lblLoaiTim);
		lblLoaiTim.setBounds(30, 55, 130, 30);

		cbLoaiTim = new JComboBox<String>();
		cbLoaiTim.setFont(font3);
		cbLoaiTim.setBounds(170, 55, 170, 30);
		cbLoaiTim.addItem("Mã phòng");
		cbLoaiTim.addItem("Sức chứa");
		pnEast.add(cbLoaiTim);

		JLabel lblTuKhoaTim = new JLabel("Nhập từ khóa tìm kiếm");
		lblTuKhoaTim.setFont(font2);
		pnEast.add(lblTuKhoaTim);
		lblTuKhoaTim.setBounds(30, 105, 300, 30);

		txtTuKhoaTim = new JTextField();
		txtTuKhoaTim.setFont(font3);
		txtTuKhoaTim.setBounds(30, 160, 310, 30);
		pnEast.add(txtTuKhoaTim);

		btnTimKiem = new JButton("Tìm kiếm", new ImageIcon("icon\\Research_icon.png"));
		btnTimKiem.setFont(font);
		btnTimKiem.setBounds(112, 250, 140, 35);
		btnTimKiem.setBackground(new Color(238, 233, 233));
		btnTimKiem.setBorder(new RoundedBorder(5));
		pnEast.add(btnTimKiem);

		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(null);
		pnCenter.setBackground(new Color(246, 245, 255));
		pnCenter.setBounds(0, 380, 1078, 500);

		JLabel lblBang = new JLabel("DANH SÁCH PHÒNG");
		lblBang.setFont(new Font("Arial", Font.BOLD, 20));
		pnCenter.add(lblBang);
		lblBang.setForeground(Color.blue);
		lblBang.setBounds(20, 15, 500, 30);

		pnCenter.add(btnXuatExcel = new JButton("Xuất danh sách các phòng", new ImageIcon("icon\\Excel_icon.png")));
		btnXuatExcel.setFont(font);
		btnXuatExcel.setBorder(new RoundedBorder(5));
		btnXuatExcel.setBounds(798, 14, 270, 30);
		add(pnCenter);

		model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		table.setSelectionBackground(Color.pink);
		table.getTableHeader().setBackground(new Color(238, 233, 233));
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		scroll = new JScrollPane(table);
		scroll.setBounds(9, 50, 1060, 290);
		pnCenter.add(scroll);

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXuatExcel.addActionListener(this);
		cbLau.addActionListener(this);
		cbSoPhong.addActionListener(this);
		loadData();
		table.addMouseListener(this);
		btnUser.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMa.setText(model.getValueAt(row, 1).toString());
		cbLoaiPhong.setSelectedItem(model.getValueAt(row, 2));
		cbTrangThai.setSelectedItem(model.getValueAt(row, 3));
		txtSucChua.setText(model.getValueAt(row, 4).toString());
		txtDonGia.setText(model.getValueAt(row, 5).toString());
		cbLau.setSelectedItem(model.getValueAt(row, 1).toString().substring(0, 1));
		cbSoPhong.setSelectedItem(model.getValueAt(row, 1).toString().substring(1, 3));
		loadMa();
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

	private void loadData() {
		int i = 0;
		p_dao = new Phong_dao();
		for (Phong p : p_dao.getallPhongs()) {
			i++;
			LoaiPhong loaiPhong = lp_dao.getLoaiPhongTheoMaLoaiPhong(p.getLoaiPhong().getMaLoaiPhong());
			String trThai;
			if (p.getTrangThai().toString().equals("Đang_sử_dụng")) {
				trThai = "Đang sử dụng";
			} else if (p.getTrangThai().toString().contentEquals("Đang_sửa_chữa"))
				trThai = "Đang sửa chữa";
			else
				trThai = p.getTrangThai().toString();
			Object[] row = { i, p.getMaPhong(), loaiPhong.getTenLoaiPhong(), trThai, loaiPhong.getSucChua(),
					loaiPhong.getDonGiaTheoGio() };
			model.addRow(row);
		}
	}

	private String generateRandomCode() {
		String lau;
		String soPhong;
		lau = (String) cbLau.getSelectedItem();
		soPhong = (String) cbSoPhong.getSelectedItem();
		return lau + soPhong;
	}

	private void loadMa() {
		String code;
		code = generateRandomCode();
		txtMa.setText(code);
	}

	private String loadMaLP() {
		String loaiPhong;
		if (cbLoaiPhong.getSelectedItem().equals("Phòng thường"))
			loaiPhong = "PT";
		else
			loaiPhong = "PV";
		String sucChua = txtSucChua.getText();
		return loaiPhong + sucChua;
	}

	private void xoaTrang() {
		txtDonGia.setText("");
		txtSucChua.setText("");
		txtTuKhoaTim.setText("");
		cbLau.setSelectedItem("1");
		cbSoPhong.setSelectedItem("01");
	}

	private void clearTable() {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	private void them() {
		if (txtSucChua.getText().trim().equals("") || txtDonGia.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!!");
		} else {
			loadMa();
			String maP = txtMa.getText();
			String maLP = loadMaLP();
			Enum_TrangThai trangThai = null;
			if (cbTrangThai.getSelectedItem().equals("Trống"))
				trangThai = Enum_TrangThai.Trống;
			if (cbTrangThai.getSelectedItem().equals("Chờ"))
				trangThai = Enum_TrangThai.Chờ;
			if (cbTrangThai.getSelectedItem().equals("Đang sử dụng"))
				trangThai = Enum_TrangThai.Đang_sử_dụng;
			if (cbTrangThai.getSelectedItem().equals("Đang sửa chữa"))
				trangThai = Enum_TrangThai.Đang_sửa_chữa;
			int sucChua = Integer.parseInt(txtSucChua.getText());
			String tenLoaiPhong = (String) cbLoaiPhong.getSelectedItem();
			double donGia = Double.parseDouble(txtDonGia.getText());
			LoaiPhong lp = new LoaiPhong(maLP, tenLoaiPhong, sucChua, donGia);
			Phong p = new Phong(maP, lp, trangThai);
			lp_dao.addLoaiPhong(lp);
			if (p_dao.addPhong(p)) {
				clearTable();
				loadData();
				xoaTrang();
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Phòng đã tồn tại. Thêm không thành công");
			}
		}
	}

	private void xoa() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa!!");
		} else if (table.getSelectedRowCount() > 1) {
			JOptionPane.showMessageDialog(null, "Chỉ được chọn 1 phòng để xóa!!");
		} else {
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phòng này không?", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				int row = table.getSelectedRow();
				p_dao.deletePhong(model.getValueAt(row, 1).toString());
				model.removeRow(row);
				clearTable();
				loadData();
				JOptionPane.showMessageDialog(this, "Xóa thành công!!");

			}
		}
	}

	private void sua() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để sửa!!");
		} else if (table.getSelectedRowCount() > 1) {
			JOptionPane.showMessageDialog(null, "Chỉ được chọn 1 phòng để sửa!!");
		} else {
			String maP = txtMa.getText();
			String maLP = loadMaLP();
			Enum_TrangThai trangThai = null;
			if (cbTrangThai.getSelectedItem().equals("Trống"))
				trangThai = Enum_TrangThai.Trống;
			if (cbTrangThai.getSelectedItem().equals("Chờ"))
				trangThai = Enum_TrangThai.Chờ;
			if (cbTrangThai.getSelectedItem().equals("Đang sử dụng"))
				trangThai = Enum_TrangThai.Đang_sử_dụng;
			if (cbTrangThai.getSelectedItem().equals("Đang sửa chữa"))
				trangThai = Enum_TrangThai.Đang_sửa_chữa;
			int sucChua = Integer.parseInt(txtSucChua.getText());
			String tenLoaiPhong = (String) cbLoaiPhong.getSelectedItem();
			double donGia = Double.parseDouble(txtDonGia.getText());
			LoaiPhong lp = new LoaiPhong(maLP, tenLoaiPhong, sucChua, donGia);
			Phong p = new Phong(maP, lp, trangThai);
			lp_dao.addLoaiPhong(lp);
			if (p_dao.updatePhong(p, generateRandomCode())) {
//				if(txtMa.getText().trim() != model.getValueAt(table.getSelectedRow(), 1).toString()){
//					JOptionPane.showMessageDialog(null, "Không được sửa mã phòng!!");
//				}else if(txtMa.getText().trim() == model.getValueAt(table.getSelectedRow(), 1).toString()){
//					System.out.println( model.getValueAt(table.getSelectedRow(), 1).toString());

				clearTable();
				loadData();
				xoaTrang();
				JOptionPane.showMessageDialog(this, "Sửa thành công!");
//				}
			} else {
				JOptionPane.showMessageDialog(null, "Không được sửa mã phòng!!");
			}
		}
	}

	private void tim() {
		int i = 1;
		if (btnTimKiem.getText().equals("Tìm kiếm")) {
			if (cbLoaiTim.getSelectedItem().equals("Mã phòng")) {
				Phong p = null;
				p = p_dao.getPhongTheoMaPhong(txtTuKhoaTim.getText());
				if (p != null) {
					btnTimKiem.setText("Hủy tìm");
					clearTable();
					LoaiPhong loaiPhong = lp_dao.getLoaiPhongTheoMaLoaiPhong(p.getLoaiPhong().getMaLoaiPhong());
					Object[] row = { i++, p.getMaPhong(), loaiPhong.getTenLoaiPhong(), p.getTrangThai(),
							loaiPhong.getSucChua(), loaiPhong.getDonGiaTheoGio() };
					model.addRow(row);
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin!!");
				}
			} else if (cbLoaiTim.getSelectedItem().equals("Sức chứa")) {
				ArrayList<Phong> dsPhong = p_dao.getPhongTheoSucChua(txtTuKhoaTim.getText());
				if (dsPhong != null) {
					btnTimKiem.setText("Hủy tìm");
					clearTable();
					for (Phong p : dsPhong) {
						LoaiPhong loaiPhong = lp_dao.getLoaiPhongTheoMaLoaiPhong(p.getLoaiPhong().getMaLoaiPhong());
						Object[] row = { i++, p.getMaPhong(), loaiPhong.getTenLoaiPhong(), p.getTrangThai(),
								loaiPhong.getSucChua(), loaiPhong.getDonGiaTheoGio() };
						model.addRow(row);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin!!");
				}
			}
		} else {
			clearTable();
			loadData();
			btnTimKiem.setText("Tìm kiếm");
		}
	}

	private void xuatExcel() {
		try {
			wordbook = new XSSFWorkbook();
			XSSFSheet sheet = wordbook.createSheet("Danh sách phòng");

			XSSFRow row = null;
			Cell cell = null;
			row = sheet.createRow(2);// Tạo 2 dòng trống trong excel
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Mã phòng");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Loại phòng");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Trạng thái");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Sức chứa");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Đơn giá");

			for (int i = 0; i < p_dao.getallPhongs().size(); i++) {
				row = sheet.createRow(3 + i); // Bỏ qua 2 dòng trống

				cell = row.createCell(0, CellType.NUMERIC);
				cell.setCellValue(i + 1);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p_dao.getallPhongs().get(i).getMaPhong());
				cell = row.createCell(2, CellType.STRING);
				LoaiPhong loaiPhong = lp_dao
						.getLoaiPhongTheoMaLoaiPhong(p_dao.getallPhongs().get(i).getLoaiPhong().getMaLoaiPhong());
				cell.setCellValue(loaiPhong.getTenLoaiPhong());
				cell = row.createCell(3, CellType.STRING);
				String trThai;
				if (p_dao.getallPhongs().get(i).getTrangThai().toString().equals("Đang_sử_dụng")) {
					trThai = "Đang sử dụng";
				} else if (p_dao.getallPhongs().get(i).getTrangThai().toString().contentEquals("Đang_sửa_chữa"))
					trThai = "Đang sửa chữa";
				else
					trThai = p_dao.getallPhongs().get(i).getTrangThai().toString();
				cell.setCellValue(trThai);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(loaiPhong.getSucChua());
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue(loaiPhong.getDonGiaTheoGio());
			}

			File file = new File("LuuFile_Excel\\DanhSachPhong.xlsx");
			try {
				FileOutputStream file_out = new FileOutputStream(file);
				wordbook.write(file_out);
				file_out.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(this, "In file danh sách thành công!!");
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Không in được");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnUser)) {
			dialog_user.setVisible(true);
		}
		if (obj.equals(btnThem)) {
			them();
		} else if (obj.equals(btnXoa)) {
			xoa();
		} else if (obj.equals(btnSua)) {
			sua();
		} else if (obj.equals(btnLamMoi)) {
			xoaTrang();
			clearTable();
			loadData();
			loadMa();
		} else if (obj.equals(btnTimKiem)) {
			tim();
		} else if (obj.equals(btnXuatExcel)) {
			xuatExcel();
		} else if (obj.equals(cbLau)) {
			loadMa();
		} else if (obj.equals(cbSoPhong)) {
			loadMa();
		}
	}
}
