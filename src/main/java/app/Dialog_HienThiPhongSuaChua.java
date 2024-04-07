package app;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import dao.Phong_dao;
import entity.LoaiPhong;
import entity.Phong;
import dao.LoaiPhong_dao;

public class Dialog_HienThiPhongSuaChua extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblPhong, lblLoai, lblSucChua, lblTrangThai, lblGia, lblLoai_1, lblSucChua_1, lbltrangthai_1,
			lblgia_1, lblPhong_1;
	private Phong_dao p_dao = new Phong_dao();
	private LoaiPhong_dao lp_dao = new LoaiPhong_dao();

	private Phong p;
	private LoaiPhong lp;

	public Dialog_HienThiPhongSuaChua(String maPhong) {
		// kích thước
		// dialog--------------*****************************************************************
		getContentPane().setBackground(Color.WHITE);
		setSize(300, 260);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		ImageIcon icon = new ImageIcon("icon\\icon_white.png");
	    this.setIconImage(icon.getImage());

		// các
		// lbl-----------------------------------------------------------------------
		lblPhong = new JLabel("Phòng:");
		lblPhong.setFont(new Font("Arial", Font.BOLD, 18));
		lblPhong.setBounds(20, 10, 100, 30);
		getContentPane().add(lblPhong);

		lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Arial", Font.BOLD, 18));
		lblLoai.setBounds(20, 50, 100, 30);
		getContentPane().add(lblLoai);

		lblSucChua = new JLabel("Sức chứa:");
		lblSucChua.setFont(new Font("Arial", Font.BOLD, 18));
		lblSucChua.setBounds(20, 90, 100, 30);
		getContentPane().add(lblSucChua);

		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Arial", Font.BOLD, 18));
		lblTrangThai.setBounds(20, 130, 100, 30);
		getContentPane().add(lblTrangThai);

		lblGia = new JLabel("Giá:");
		lblGia.setFont(new Font("Arial", Font.BOLD, 18));
		lblGia.setBounds(20, 170, 100, 30);
		getContentPane().add(lblGia);

		lblPhong_1 = new JLabel(maPhong);
		lblPhong_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblPhong_1.setBounds(130, 10, 120, 30);
		getContentPane().add(lblPhong_1);

		p = p_dao.getPhongTheoMaPhong(maPhong);
		lp = lp_dao.getLoaiPhongTheoMaLoaiPhong(p.getLoaiPhong().getMaLoaiPhong());

		lblLoai_1 = new JLabel(lp.getTenLoaiPhong());
		lblLoai_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblLoai_1.setBounds(130, 50, 120, 30);
		getContentPane().add(lblLoai_1);

		lblSucChua_1 = new JLabel(lp.getSucChua() + "");
		lblSucChua_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblSucChua_1.setBounds(130, 90, 120, 30);
		getContentPane().add(lblSucChua_1);

		lbltrangthai_1 = new JLabel(p.getTrangThai() + "");
		lbltrangthai_1.setFont(new Font("Arial", Font.BOLD, 15));
		lbltrangthai_1.setBounds(130, 130, 130, 30);
		getContentPane().add(lbltrangthai_1);

		lblgia_1 = new JLabel(lp.getDonGiaTheoGio() + "VNĐ");
		lblgia_1.setBackground(Color.WHITE);
		lblgia_1.setForeground(Color.RED);
		lblgia_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblgia_1.setBounds(130, 170, 120, 30);
		getContentPane().add(lblgia_1);
	}
}
