package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ChiTietDichVu_dao;
import dao.SanPham_dao;
import entity.ChiTietDichVu;
import entity.HoaDonDatPhong;
import entity.Phong;
import entity.SanPham;



public class Dialog_TraSanPham extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSLDaDat;
	private Font font = new Font("Arial", Font.BOLD, 14);
	private JTextField txtSLDat;
	private JLabel lblSLTra;
	private JTextField txtSLTra;
	private JButton btnDongY;
	private JButton btnHuy;
	private ChiTietDichVu_dao ctdv_dao;
	private SanPham_dao sp_dao;
	private String tenSp;
	private String maHD;
	private Dialog_ThanhToan thanhToan;
	private String maPhong;

	public Dialog_TraSanPham(int soLuong, String tenSp, String maHD, String maPhong, Dialog_ThanhToan thanhToan) {
		getContentPane().setBackground(Color.WHITE);
		setSize(300, 180);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.thanhToan = thanhToan;
		this.maHD = maHD;
		this.tenSp = tenSp;
		this.maPhong = maPhong;
		ctdv_dao = new ChiTietDichVu_dao();
		sp_dao = new SanPham_dao();
		
		getContentPane().add(lblSLDaDat = new JLabel("Số lượng đã đặt"));
		lblSLDaDat.setBounds(10, 10, 200, 30);
		lblSLDaDat.setFont(font);
		
		getContentPane().add(txtSLDat = new JTextField(soLuong+""));
		txtSLDat.setBounds(210,10,60,30);
		txtSLDat.setEditable(false);
		txtSLDat.setFont(font);
		
		getContentPane().add(lblSLTra = new JLabel("Nhập số lượng muốn trả lại:"));
		lblSLTra.setBounds(10, 50, 200, 30);
		lblSLTra.setFont(font);
		
		getContentPane().add(txtSLTra = new JTextField());
		txtSLTra.setBounds(210,50,60,30);
		txtSLTra.setFont(font);
		
		getContentPane().add(btnDongY = new JButton("Đồng ý"));
		btnDongY.setBounds(40, 100, 100, 35);
		btnDongY.setForeground(Color.WHITE);
		btnDongY.setFont(new Font("Arial", Font.BOLD, 16));
		btnDongY.setBorder(new RoundedBorder(10));
		btnDongY.setBackground(new Color(46, 204, 113));
		
		getContentPane().add(btnHuy = new JButton("Hủy"));
		btnHuy.setBounds(150, 100, 100, 35);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBorder(new RoundedBorder(10));
		btnHuy.setBackground(Color.red);
		
		btnDongY.addActionListener(this);
		btnHuy.addActionListener(this);
		
	}
	
	public void dongY() {
		HoaDonDatPhong hd = new HoaDonDatPhong(maHD);
		Phong ph = new Phong(maPhong);
		SanPham s = sp_dao.getSanPhamTheoTen(tenSp);
		SanPham sp = new SanPham(s.getMaSanPham());
		if(Integer.parseInt(txtSLTra.getText()) < Integer.parseInt(txtSLDat.getText())
				 && Integer.parseInt(txtSLTra.getText()) >= 0) {
			int soLuong = Integer.parseInt(txtSLDat.getText()) - Integer.parseInt(txtSLTra.getText());
			double donGia = 0;
			if(s.getloaiSanPham().equals("Thức ăn")) {
				donGia = s.getDonGia() * 1.03;
			}
			else if(s.getloaiSanPham().equals("Đồ uống")) {
				donGia = s.getDonGia() * 1.02;
			}else {
				donGia = s.getDonGia() * 1.01;
			}
			ChiTietDichVu ctdv = new ChiTietDichVu(hd,ph,sp,soLuong,donGia);
			
			for(SanPham sanPham : sp_dao.getallSanPhams()) {
				if(sanPham.getMaSanPham().equals(s.getMaSanPham())) {
					sp_dao.updateSLTon(sanPham.getSoLuongTon() + Integer.parseInt(txtSLTra.getText()), sanPham.getMaSanPham());
					break;
				}
			}
			if(ctdv_dao.UpdateChiTietDV(ctdv)) {
				thanhToan.clearTable();
				thanhToan.loadData();
				thanhToan.clear_Tien();
				thanhToan.load_Tien();
				JOptionPane.showMessageDialog(this, "Trả thành công!!!");
				setVisible(false);
			}
		}else if(Integer.parseInt(txtSLTra.getText()) == Integer.parseInt(txtSLDat.getText())){
			if(ctdv_dao.deleteChiTietDV2(maHD, s.getMaSanPham(), maPhong)) {
				thanhToan.clearTable();
				thanhToan.loadData();
				thanhToan.clear_Tien();
				thanhToan.load_Tien();
				JOptionPane.showMessageDialog(this, "Trả thành công!!!");
				setVisible(false);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Nhập không hợp lệ!!!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnDongY)){
			dongY();
		}else if(o.equals(btnHuy)) {
			setVisible(false);
		}
	}
}
