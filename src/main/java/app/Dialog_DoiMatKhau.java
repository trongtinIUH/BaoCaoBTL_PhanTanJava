package app;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DangNhap_dao;

import javax.swing.SwingConstants;

public class Dialog_DoiMatKhau extends JDialog implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_mkCu,lblMatKhauMoi,lblNhapLaiMatKhauMoi;
	private JTextField txtPassword,txtPassword_New,txtpass_cu;
	private JLabel lblTitle;
	private JButton btnHuy,btnXacNhan;
	private DangNhap_dao dangNhap_dao = new DangNhap_dao();
	private String manv="";
	public  Dialog_DoiMatKhau(String ma) {
		setTitle("Đổi Mật Khẩu");
		setSize(400, 300);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("icon\\icon_white.png");
	    this.setIconImage(icon.getImage());
	    
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		manv=ma;
		lblTitle = new JLabel("ĐỔI MẬT KHẨU");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(70, 5, 250, 40);
		lblTitle.setForeground(Color.BLACK);
		getContentPane().add(lblTitle);
	        
		//OTP
		    lbl_mkCu = new JLabel("Nhập MK Cũ");
		    lbl_mkCu.setFont(new Font("Arial", Font.BOLD, 14));
		    lbl_mkCu.setBounds(10, 60, 120, 30);
		    lbl_mkCu.setForeground(Color.BLACK);
		    getContentPane().add(lbl_mkCu);
			
		//MK mới
		    lblMatKhauMoi = new JLabel("Nhập MK Mới");
		    lblMatKhauMoi.setFont(new Font("Arial", Font.BOLD, 14));
		    lblMatKhauMoi.setBounds(10, 100, 120, 30);
		    lblMatKhauMoi.setForeground(Color.BLACK);
		    getContentPane().add(lblMatKhauMoi); 
	        
	        txtPassword = new JPasswordField(20);
	        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
	        txtPassword.setBounds(150, 100, 200, 30);
	        getContentPane().add(txtPassword);
			
		    lblNhapLaiMatKhauMoi = new JLabel("Nhập Lại MK Mới");
		    lblNhapLaiMatKhauMoi.setFont(new Font("Arial", Font.BOLD, 14));
		    lblNhapLaiMatKhauMoi.setBounds(10, 140, 130, 30);
		    lblNhapLaiMatKhauMoi.setForeground(Color.BLACK);
		    getContentPane().add(lblNhapLaiMatKhauMoi); 
	        
	        txtPassword_New= new JPasswordField(20);
	        txtPassword_New.setFont(new Font("Arial", Font.PLAIN, 14));
	        txtPassword_New.setBounds(150, 140, 200, 30);
	        getContentPane().add(txtPassword_New);
	        
	       
	         btnXacNhan = new JButton("Xác Nhận");
	        btnXacNhan.setBounds(30, 190, 140, 35);
	        btnXacNhan.setFont(new Font("Arial", Font.BOLD, 18));
	        btnXacNhan.setForeground(Color.black);
	        btnXacNhan.setBackground(new Color(064,224,208));
	        btnXacNhan.setBorder(new RoundedBorder(5));
	        getContentPane().add(btnXacNhan);
			
	         btnHuy = new JButton("Hủy");
	        btnHuy.setBounds(210, 190, 140, 35);
	        btnHuy.setFont(new Font("Arial", Font.BOLD, 18));
	        btnHuy.setForeground(Color.black);
	        btnHuy.setBackground(Color.white);
	        btnHuy.setBorder(new RoundedBorder(5));
	        getContentPane().add(btnHuy);
			ImageIcon hinhgt= new ImageIcon("image\\quenmk.jpg");
			Image image = hinhgt.getImage();
			Image newImage = image.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
			hinhgt = new ImageIcon(newImage);
	        txtpass_cu = new JPasswordField(20);
	        txtpass_cu.setFont(new Font("Arial", Font.PLAIN, 14));
	        txtpass_cu.setBounds(150, 60, 200, 30);
	        getContentPane().add(txtpass_cu);
	        
	        
	        btnHuy.addActionListener(this);
	        btnXacNhan.addActionListener(this);
	        

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnXacNhan)) {
		    char[] mk_cuu = ((JPasswordField) txtpass_cu).getPassword();
		    char[] mk1 = ((JPasswordField) txtPassword).getPassword();
		    char[] mk2 = ((JPasswordField) txtPassword_New).getPassword();
		    String mk_cu=new String(mk_cuu);
		    String mk_moi=new String(mk1);
		    String mk_nhaplaimoi=new String(mk2);
		    if(dangNhap_dao.LayMatKhauTheoMaNhanVien(manv).getMatKhau()!=null&&dangNhap_dao.LayMatKhauTheoMaNhanVien(manv).getMatKhau().equals(mk_cu)) {
		        if(!mk_cu.equals(mk_moi)) {
		            if(mk_moi.isEmpty() || mk_nhaplaimoi.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Mật khẩu mới không được để trống !");
		            } else if(mk_moi.equals(mk_nhaplaimoi)) {
		                JOptionPane.showMessageDialog(null, "Mật khẩu mới của bạn đã được cập nhật !");
		                dangNhap_dao.doiMatKhauTheoMaNV(manv, mk_moi);
		                this.setVisible(false);	
		            } else {
		                JOptionPane.showMessageDialog(null, "Mật khẩu mới và nhập lại mật khẩu mới không trùng nhau !");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Mật khẩu mới không được trùng với mật khẩu cũ !");
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "MK cũ không chính xác hoặc không tồn tại!");
		    }
		}

		else if(o.equals(btnHuy)) {
			this.setVisible(false);	
		}		
	}
}
