package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.ThongKeServices;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import utils.ModelThongKe;
import utils.ModelThongKeDTNhieuNam;
import utils.ModelThongKeKH;

public class ThongKeImpl extends UnicastRemoteObject implements ThongKeServices {
	/**
	 * 
	 */
	private static final long serialVersionUID = -472678758433174912L;
	private EntityManager em;

    public ThongKeImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("BaiTapLonPTUD").createEntityManager();
    }

    @Override
    public ArrayList<ModelThongKe> thongKeTheoNam(String yearStart, String yearEnd) throws RemoteException {
        String sql = "DECLARE @namBatDau int = "+yearStart+" "
				+ "DECLARE @namKetThuc int = "+yearEnd+" "
				+ "SELECT "
				+ "    YEAR(ngayLapHoaDon) AS nam,  "
				+ "    SUM(tongTienSauKhuyenMai) AS tongDoanhThu, "
				+ "    SUM(tienPhong) AS tongTienPhong, "
				+ "    SUM(tienDichVu) AS tongTienDichVu "
				+ "FROM "
				+ "("
				+ "    SELECT  "
				+ "    hd.ngayLapHoaDon,  "
				+ "    (ISNULL(tienPhong, 0) + ISNULL(tienDichVu, 0)) * (1 - COALESCE(km.phanTramKhuyenMai, 0) / 100) AS tongTienSauKhuyenMai,  "
				+ "    tienPhong,  "
				+ "    tienDichVu  "
				+ "FROM "
				+ "    HoaDonDatPhong hd  "
				+ "LEFT JOIN  "
				+ "    ("
				+ "        SELECT  "
				+ "            cthd.maHoaDon,  "
				+ "            SUM(lp.donGiaTheoGio * cthd.soGioHat) AS tienPhong  "
				+ "        FROM  "
				+ "            ChiTietHoaDon cthd "
				+ "        LEFT JOIN  "
				+ "            Phong p ON p.maPhong = cthd.maPhong "
				+ "        LEFT JOIN "
				+ "            LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong "
				+ "        GROUP BY "
				+ "            cthd.maHoaDon "
				+ "    ) cthd ON cthd.maHoaDon = hd.maHoaDon "
				+ "LEFT JOIN "
				+ "    ("
				+ "        SELECT  "
				+ "            ctdv.maHoaDon,  "
				+ "            SUM(ctdv.giaBan * ctdv.soLuong) AS tienDichVu  "
				+ "        FROM  "
				+ "            ChiTietDichVu ctdv "
				+ "        GROUP BY  "
				+ "            ctdv.maHoaDon "
				+ "    ) ctdv ON ctdv.maHoaDon = hd.maHoaDon  "
				+ "LEFT JOIN "
				+ "    KhuyenMai km ON km.maKhuyenMai = hd.maKhuyenMai "
				+ ") AS KQ "
				+ "WHERE  "
				+ "    YEAR(ngayLapHoaDon) BETWEEN @namBatDau AND @namKetThuc  "
				+ "GROUP BY  "
				+ "    YEAR(ngayLapHoaDon)";

        Query query = em.createNativeQuery(sql);
        query.setParameter("yearStart", yearStart);
        query.setParameter("yearEnd", yearEnd);

        @SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKe> lists = new ArrayList<ModelThongKe>();

        for (Object[] result : results) {
            ModelThongKe model = new ModelThongKe();
            model.setYear((String) result[0]);
            model.setTongDoanhThu((Double) result[1]);
            model.setDoanhThuPhong((Double) result[2]);
            model.setDoanhThuDichVu((Double) result[3]);
            lists.add(model);
        }

        return lists;
    }

    @Override
    public ArrayList<ModelThongKeDTNhieuNam> thongKeTheoNhieuNam(int yearStart, int yearEnd) throws RemoteException {
    	String sql = "DECLARE @namBatDau int = "+yearStart+ "DECLARE @namKetThuc int = "+yearEnd+" "
				+ "SELECT "
				+ "    YEAR(ngayLapHoaDon) AS nam, "
				+ "    COUNT(DISTINCT maHoaDon) AS tongSoHoaDon,"
				+ "    SUM(tongTienSauKhuyenMai) AS tongDoanhThu, "
				+ "    SUM(tienPhongThuong) AS tongDoanhThuPhongThuong,"
				+ "    SUM(tienPhongVIP) AS tongDoanhThuPhongVIP,"
				+ "    SUM(tienPhong) AS tongTienPhong,"
				+ "    SUM(tienDichVu) AS tongTienDichVu,"
				+ "    SUM(soGioHat) AS tongSoGioHat "
				+ "FROM "
				+ "("
				+ "    SELECT "
				+ "        hd.ngayLapHoaDon, "
				+ "        hd.maHoaDon, "
				+ "        (ISNULL(tienPhong, 0) + ISNULL(tienDichVu, 0)) * (1 - COALESCE(km.phanTramKhuyenMai, 0) / 100) AS tongTienSauKhuyenMai, "
				+ "        tienPhongThuong, "
				+ "        tienPhongVIP, "
				+ "        tienPhong, "
				+ "        tienDichVu, "
				+ "        soGioHat "
				+ "    FROM  "
				+ "        HoaDonDatPhong hd  "
				+ "    LEFT JOIN "
				+ "        ("
				+ "            SELECT  "
				+ "                cthd.maHoaDon,  "
				+ "                SUM(lp.donGiaTheoGio * cthd.soGioHat) AS tienPhong, "
				+ "                SUM(CASE WHEN lp.maLoaiPhong LIKE 'PT%' THEN lp.donGiaTheoGio * cthd.soGioHat ELSE 0 END) AS tienPhongThuong, "
				+ "                SUM(CASE WHEN lp.maLoaiPhong LIKE 'PV%' THEN lp.donGiaTheoGio * cthd.soGioHat ELSE 0 END) AS tienPhongVIP, "
				+ "                SUM(cthd.soGioHat) AS soGioHat "
				+ "            FROM  "
				+ "                ChiTietHoaDon cthd "
				+ "            LEFT JOIN  "
				+ "                Phong p ON p.maPhong = cthd.maPhong  "
				+ "            LEFT JOIN  "
				+ "                LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong  "
				+ "            GROUP BY  "
				+ "                cthd.maHoaDon "
				+ "        ) cthd ON cthd.maHoaDon = hd.maHoaDon "
				+ "    LEFT JOIN  "
				+ "        ( "
				+ "            SELECT  "
				+ "                ctdv.maHoaDon,  "
				+ "                SUM(ctdv.giaBan * ctdv.soLuong) AS tienDichVu  "
				+ "            FROM "
				+ "                ChiTietDichVu ctdv "
				+ "            GROUP BY "
				+ "                ctdv.maHoaDon "
				+ "        ) ctdv ON ctdv.maHoaDon = hd.maHoaDon "
				+ "    LEFT JOIN  "
				+ "        KhuyenMai km ON km.maKhuyenMai = hd.maKhuyenMai "
				+ ") AS KQ  "
				+ "WHERE  "
				+ "    YEAR(ngayLapHoaDon) BETWEEN @namBatDau AND @namKetThuc  "
				+ "GROUP BY "
				+ "    YEAR(ngayLapHoaDon)";

        Query query = em.createNativeQuery(sql);
        query.setParameter("yearStart", yearStart);
        query.setParameter("yearEnd", yearEnd);

        @SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKeDTNhieuNam> lists = new ArrayList<ModelThongKeDTNhieuNam>();

        for (Object[] result : results) {
            ModelThongKeDTNhieuNam model = new ModelThongKeDTNhieuNam();
            model.setNam((String) result[0]);
            model.setTongSoHoaDon((Integer) result[1]);
            model.setTongDoanhThu((Double) result[2]);
            model.setTongDoanhThuPhongThuong((Double) result[3]);
            model.setTongDoanhThuPhongVIP((Double) result[4]);
            model.setTongTienPhong((Double) result[5]);
            model.setTongTienDichVu((Double) result[6]);
            model.setTongSoGioHat((Double) result[7]);
            lists.add(model);
        }

        return lists;
    }

    @Override
    public ArrayList<ModelThongKe> updateCboYear() throws RemoteException {
    	String sql = "select YEAR(ngayLapHoaDon) as nam "
				+ "from HoaDonDatPhong "
				+ "group by YEAR(ngayLapHoaDon)";
        Query query = em.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKe> yearLists = new ArrayList<ModelThongKe>();

        for (Object result : results) {
            ModelThongKe model = new ModelThongKe();
            model.setYear((String) result);
            yearLists.add(model);
        }

        return yearLists;
    }

    @Override
    public ArrayList<ModelThongKe> updateCboMonth() throws RemoteException {
        String sql = "SELECT MONTH(ngayLapHoaDon) AS thang "
        		+ "FROM HoaDonDatPhong GROUP BY MONTH(ngayLapHoaDon)";

        Query query = em.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKe> lists = new ArrayList<ModelThongKe>();

        for (Object result : results) {
            ModelThongKe modelTK = new ModelThongKe();
            modelTK.setMonth((String) result);
            lists.add(modelTK);
        }

        return lists;
    }

    @Override
    public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhat() throws RemoteException {
    	String sql = "SELECT TOP 10 "
				+ "kh.maKhachHang, "
				+ "kh.hoTen, "
				+ "kh.soDienThoai, "
				+ "kh.gioiTinh, "
				+ "SUM(cthd.soGioHat) AS TongSoGioHat "
				+ "FROM "
				+ "KhachHang kh "
				+ "JOIN "
				+ "HoaDonDatPhong hddp ON kh.maKhachHang = hddp.maKhachHang "
				+ "JOIN "
				+ "ChiTietHoaDon cthd ON hddp.maHoaDon = cthd.maHoaDon "
				+ "GROUP BY "
				+ "kh.maKhachHang, "
				+ "kh.hoTen, "
				+ "kh.soDienThoai, "
				+ "kh.gioiTinh "
				+ "ORDER BY "
				+ "TongSoGioHat DESC";

        Query query = em.createNativeQuery(sql);
        query.setMaxResults(10);

        @SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKeKH> lists = new ArrayList<ModelThongKeKH>();

        for (Object[] result : results) {
            ModelThongKeKH modelTK = new ModelThongKeKH((String) result[0], (String) result[1], (String) result[2], (Boolean) result[3], (Double) result[4]);
            lists.add(modelTK);
        }

        return lists;
    }

    @Override
    public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhatTheoNam(String year) throws RemoteException {
    	String sql = "SELECT TOP 10 "
				+ "kh.maKhachHang, "
				+ "kh.hoTen, "
				+ "kh.soDienThoai, "
				+ "kh.gioiTinh, "
				+ "SUM(cthd.soGioHat) as TongSoGioHat "
				+ "FROM "
				+ "KhachHang kh "
				+ "JOIN "
				+ "HoaDonDatPhong hddp ON kh.maKhachHang = hddp.maKhachHang "
				+ "JOIN "
				+ "ChiTietHoaDon cthd ON hddp.maHoaDon = cthd.maHoaDon "
				+ "where YEAR(hddp.ngayLapHoaDon) = "+year+" "
				+ "GROUP BY  "
				+ "YEAR(hddp.ngayLapHoaDon), "
				+ "kh.maKhachHang, "
				+ "kh.hoTen, "
				+ "kh.soDienThoai, "
				+ "kh.gioiTinh "
				+ "ORDER BY "
				+ "TongSoGioHat DESC";

        Query query = em.createNativeQuery(sql);
        query.setParameter("year", year);
        query.setMaxResults(10);

        @SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKeKH> lists = new ArrayList<ModelThongKeKH>();

        for (Object[] result : results) {
            ModelThongKeKH modelTK = new ModelThongKeKH((String) result[0], (String) result[1], (String) result[2], (Boolean) result[3], (Double) result[4]);
            lists.add(modelTK);
        }

        return lists;
    }

    @Override
    public ArrayList<ModelThongKeKH> getTop10KhachHangHatNhieuNhatTheoThang(String year, String month) throws RemoteException {
    	String sql = "SELECT TOP 10 "
				+ "kh.maKhachHang, "
				+ "kh.hoTen, "
				+ "kh.soDienThoai, "
				+ "kh.gioiTinh, "
				+ "SUM(cthd.soGioHat) as TongSoGioHat "
				+ "FROM "
				+ "KhachHang kh "
				+ "JOIN "
				+ "HoaDonDatPhong hddp ON kh.maKhachHang = hddp.maKhachHang "
				+ "JOIN "
				+ "ChiTietHoaDon cthd ON hddp.maHoaDon = cthd.maHoaDon "
				+ "where YEAR(hddp.ngayLapHoaDon) = "+year+" and MONTH(hddp.ngayLapHoaDon) = "+month+ "GROUP BY  "
				+ "YEAR(hddp.ngayLapHoaDon), "
				+ "MONTH(hddp.ngayLapHoaDon), "
				+ "kh.maKhachHang, "
				+ "kh.hoTen, "
				+ "kh.soDienThoai, "
				+ "kh.gioiTinh "
				+ "ORDER BY "
				+ "TongSoGioHat DESC";

        Query query = em.createNativeQuery(sql);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setMaxResults(10);

        List<Object[]> results = query.getResultList();
        ArrayList<ModelThongKeKH> lists = new ArrayList<ModelThongKeKH>();

        for (Object[] result : results) {
            ModelThongKeKH modelTK = new ModelThongKeKH((String) result[0], (String) result[1], (String) result[2], (Boolean) result[3], (Double) result[4]);
            lists.add(modelTK);
        }

        return lists;
    }

}
