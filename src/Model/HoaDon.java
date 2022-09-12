package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;

public class HoaDon {
	private int MaHD;
	private KhachHang khachhang;
	private Date NgayGD;
	private int ThanhTien;
	private NhanVien NVLapHD;
	
	
	public HoaDon() {}


	public HoaDon(int maHD, KhachHang khachhang, Date ngayGD, int thanhTien, NhanVien NVLapHD) {
		MaHD = maHD;
		this.khachhang = khachhang;
		NgayGD = ngayGD;
		ThanhTien = thanhTien;
		this.NVLapHD = NVLapHD;
	}


	public int getMaHD() {
		return MaHD;
	}


	public void setMaHD(int maHD) {
		MaHD = maHD;
	}


	public KhachHang getKhachhang() {
		return khachhang;
	}


	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}


	public Date getNgayGD() {
		return NgayGD;
	}


	public void setNgayGD(Date ngayGD) {
		NgayGD = ngayGD;
	}


	public int getThanhTien() {
		return ThanhTien;
	}


	public void setThanhTien(int thanhTien) {
		ThanhTien = thanhTien;
	}

	

	public NhanVien getNVLapHD() {
		return NVLapHD;
	}


	public void setNVLapHD(NhanVien nVLapHD) {
		NVLapHD = nVLapHD;
	}


	@Override
	public String toString() {
		return  String.valueOf(MaHD);
	}
	
	
	public static List<HoaDon> getALL() throws ClassNotFoundException, SQLException{
		List<HoaDon> list = new ArrayList<HoaDon>();
		try  
		{
			Connection conn = DBConnection.getMySQLConnection();
			Statement statement = conn.createStatement();
			
			String query = "Select * from HoaDon where TrangThai = 1";
			
			ResultSet rs = statement.executeQuery(query);
			 
			while(rs.next())
			{ 
	           list.add(new HoaDon(rs.getInt(1), KhachHang.getbyID(rs.getInt(2)), rs.getDate(3), rs.getInt(4), NhanVien.getbyID(rs.getInt(5))));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public static HoaDon getbyID(int id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getMySQLConnection();
		try  
		{	
			String query = "Select *from HoaDon where MaHD = ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				return new HoaDon(rs.getInt(1), KhachHang.getbyID(rs.getInt(2)), rs.getDate(3), rs.getInt(4),  NhanVien.getbyID(rs.getInt(5)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static int Insert(HoaDon hd) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = null;
			String query = "Insert into HoaDon values ( ? , ? , ? , ?, ?, 1);";
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, hd.getMaHD());
			ps.setInt(2, hd.getKhachhang().getMaKH());
			ps.setDate(3, hd.getNgayGD());
			ps.setInt(4, hd.getThanhTien());
			ps.setInt(5, hd.getNVLapHD().getMaNV());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int updatenhoadon(HoaDon hd) {
		String sql="update HoaDon set KhachHang=?, NgayGD=?, ThanhTien=?, NVLapHD = ? where MaHD=?";
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			
			ps.setInt(1, hd.getKhachhang().getMaKH());
			ps.setDate(2, hd.getNgayGD());
			ps.setInt(3, hd.getThanhTien());
			ps.setInt(4, hd.getNVLapHD().getMaNV());
			ps.setInt(5, hd.getMaHD());
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}	
	public static int deletehoadon(int MaHD) {
		String sql = "update HoaDon set TrangThai = 0 where MaHD=?";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, MaHD);
			ps.execute();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static int[] BaoCao(Date start, Date end) throws ClassNotFoundException {
		
		int[] ketqua = {0,0};
		
		try  
		{	Connection conn = DBConnection.getMySQLConnection();
			String query = "SELECT SUM(ThanhTien), Count(*) FROM HoaDon Where NgayGD >= ? and NgayGD <= ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, start);
			ps.setDate(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				ketqua[0] = rs.getInt(1);
				ketqua[1] = rs.getInt(2);
				break;
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return ketqua;
	} 
	
	public static int[] BaoCaoDichVu(int id) throws ClassNotFoundException {
		
		int[] ketqua = {0,0};
		
		try  
		{	Connection conn = DBConnection.getMySQLConnection();
			String query = "SELECT SUM(DichVu.GiaDV), Count(*) FROM (DoAnNNLTTT.CTHoaDon left join DoAnNNLTTT.DichVu "
					+ "on CTHoaDon.DichVu = DichVu.MaDV) where CTHoaDon.DichVu = ? and CTHoaDon.TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				ketqua[0] = rs.getInt(1);
				ketqua[1] = rs.getInt(2);
				break;
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return ketqua;
	} 
	
	public static int[] BaoCaoNhanVienDaLap(int id) throws ClassNotFoundException {
		
		int[] ketqua = {0,0};
		
		try  
		{	Connection conn = DBConnection.getMySQLConnection();
			String query = "SELECT SUM(ThanhTien), Count(*) FROM HoaDon Where NVLapHD = ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				ketqua[0] = rs.getInt(1);
				ketqua[1] = rs.getInt(2);
				break;
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return ketqua;
	} 
	
	public static int[] BaoCaoNhanVienDaLam(int id) throws ClassNotFoundException {
		
		int[] ketqua = {0,0};
		
		try  
		{	Connection conn = DBConnection.getMySQLConnection();
			String query = "SELECT SUM(DichVu.GiaDV), Count(*) FROM (DoAnNNLTTT.CTHoaDon left join DoAnNNLTTT.DichVu "
					+ "on CTHoaDon.DichVu = DichVu.MaDV) where CTHoaDon.NVPhuTrach = ? and CTHoaDon.TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				ketqua[0] = rs.getInt(1);
				ketqua[1] = rs.getInt(2);
				break;
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return ketqua;
	} 
	
	public static int CountDate(Date start, Date end) throws ClassNotFoundException {
		
		int ketqua = 0;
		
		try  
		{	Connection conn = DBConnection.getMySQLConnection();
			String query = "SELECT Count(*) FROM ( SELECT Distinct NgayGD from HoaDon Where NgayGD >= ? "
					+ "and NgayGD <= ? and TrangThai = 1) as T";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, start);
			ps.setDate(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				ketqua = rs.getInt(1);
				break;
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return ketqua;
	} 
	
	public static List<HoaDon> findbynhanvien(int NVLapHD) {
		String sql = "select * from HoaDon where NVLapHD = ? and TrangThai = 1";
		
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NVLapHD);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listHoaDon.add( new HoaDon(
									rs.getInt(1),
		        		   KhachHang.getbyID(rs.getInt(2)),
   		   							rs.getDate(3),
   		   							rs.getInt(4),
   		   					NhanVien.getbyID(rs.getInt(5))
   		   				));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return listHoaDon;
}
	
	public static List<HoaDon> findbykhachhang(int khachhang) {
		String sql = "select * from HoaDon where KhachHang = ? and TrangThai = 1";
		
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, khachhang);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listHoaDon.add( new HoaDon(
						rs.getInt(1),
    		   KhachHang.getbyID(rs.getInt(2)),
  							rs.getDate(3),
  							rs.getInt(4),
  					NhanVien.getbyID(rs.getInt(5))
  				));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return listHoaDon;
}
	
	
}
