package app;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import dao.ChiTietDichVu_dao;
import dao.ChiTietHoaDon_dao;
import dao.HoaDonDatPhong_dao;
import dao.KhachHang_dao;
import dao.KhuyenMai_dao;
import dao.LoaiPhong_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import entity.ChiTietHoaDon;
import entity.HoaDonDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;


public class Dialog_TimPDP_DaThanhToan extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblPhong,lblGia,lblTrangThai, lblThoiGianHat, lblSoNguoi, lblLoai,lblLoai_1,lblPhong_1,lblgia_1,lbltrangthai_1,lblSoNguoi_1, lblTenKH, lblTenKH_1;


	private Phong_dao p_dao = new Phong_dao();
	private LoaiPhong_dao lp_dao = new LoaiPhong_dao();
	private Phong p;
	private LoaiPhong lp;
	private PhieuDatPhong_dao phieuDatPhong_dao;
	private Phong_dao phong_dao= new Phong_dao();
	private ChiTietHoaDon_dao cthd_dao;
	private Date gioHienTai;
	private Date phutHienTai;
	private double soGioHat;
	private double soPhutHat;
	private KhachHang_dao kh_dao;
	private JLabel lbl_ngayThanhToan;
	private JLabel lbl_TongTien;
		
	private HoaDonDatPhong hd= new HoaDonDatPhong();
	private HoaDonDatPhong_dao hd_dao= new HoaDonDatPhong_dao();
	private JLabel lblngaytt;
	private JLabel lbl_Tongtien_1;
	private KhuyenMai_dao khuyenmai_dao= new KhuyenMai_dao();

	private ChiTietDichVu_dao chitietdichvu_dao= new ChiTietDichVu_dao();
	

	public Dialog_TimPDP_DaThanhToan(String maPhong, String maPDP) {
		//kích thước giao diện
		getContentPane().setBackground(Color.WHITE);
		setSize(335, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		ImageIcon icon = new ImageIcon("icon\\icon_white.png");
	    this.setIconImage(icon.getImage());
	    
		phieuDatPhong_dao = new PhieuDatPhong_dao();
		cthd_dao = new ChiTietHoaDon_dao();
		kh_dao = new KhachHang_dao();
				
		//các lbl góc trái-----------------------------------------------------------------------
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
		
		//các lbl góc phải---------------------------------------------------------------------
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
		
		PhieuDatPhong pdp_of_room = null;
		ArrayList<PhieuDatPhong> dsPDP = phieuDatPhong_dao.getDanhsachPhieuDatPhongTheoMaPhong(lblPhong_1.getText().trim());
		for(PhieuDatPhong pdp : dsPDP) {
			pdp_of_room = pdp;
		}
		lblSoNguoi_1 = new JLabel();
		lblSoNguoi_1.setText(pdp_of_room.getSoNguoiHat()+"");
		lblSoNguoi_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblSoNguoi_1.setBounds(150, 90, 120, 30);
		getContentPane().add(lblSoNguoi_1);
		
		ChiTietHoaDon cthd_hienTaiCuaPhong = null;
		ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getChiTietHoaDonTheoMaPhong(lblPhong_1.getText().trim());
		for(ChiTietHoaDon cthd: dsCTHD) {
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
		lblThoiGianHat = new JLabel();
		lblThoiGianHat.setText(df.format(soGioHat) + " giờ " + df.format(soPhutHat) + " phút");
		lblThoiGianHat.setFont(new Font("Arial", Font.BOLD, 15));
		lblThoiGianHat.setBounds(150, 130, 120, 30);
		getContentPane().add(lblThoiGianHat);
		
		lbltrangthai_1 = new JLabel();
		lbltrangthai_1.setText("Đã Thanh Toán");
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
		
		lbl_ngayThanhToan = new JLabel("Ngày thanh toán:");
      
		lbl_ngayThanhToan.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_ngayThanhToan.setBounds(10, 290, 150, 30);
		getContentPane().add(lbl_ngayThanhToan);
		//tín sữa lại
        String maHoaDon = hd_dao.getMaHDTheoMaPhieuDP(maPDP);
		hd = hd_dao.getHoaDonDatPhongTheoMaHD(maHoaDon);
        String ngayGioNhan = hd.getNgayLapHoaDon().toString();
		
		lblngaytt = new JLabel();
		lblngaytt.setText(ngayGioNhan);
		lblngaytt.setFont(new Font("Arial", Font.BOLD, 18));
		lblngaytt.setBounds(170, 291, 140, 30);
		getContentPane().add(lblngaytt);
		
		lbl_TongTien = new JLabel("Tổng tiền:");
		lbl_TongTien.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_TongTien.setBounds(10, 370, 95, 30);
		
		getContentPane().add(lbl_TongTien);
		
		
		lbl_Tongtien_1 = new JLabel();
		df= new DecimalFormat("#,###,### VNĐ");
		lbl_Tongtien_1.setText(df.format(hd.tinhTongTienThanhToan(phong_dao.tinhTongTienPhongTheoMaHoaDon(hd.getMaHoaDon()),
				chitietdichvu_dao.tinhTongTienDVTheoMaHoaDon(hd.getMaHoaDon()),
				khuyenmai_dao.getPhanTramKhuyenMaiTheoMaKM(hd.getKhuyenMai().getMaKhuyenMai()))));
		lbl_Tongtien_1.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_Tongtien_1.setBounds(115, 370, 190, 30);
		getContentPane().add(lbl_Tongtien_1);
		
	}



	//hàm cập nhật các Jlabel góc phải
	  public void updateLabel(String newText) {
	        lblPhong_1.setText(newText);
	    }
	
	@Override
	public void actionPerformed(ActionEvent e) {}
}
