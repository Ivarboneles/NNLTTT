package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;

public class KhachHang {
	private int MaKH;
	private String TenKH;
	private String DiaChi;
	private String SDT;
	
	
	public KhachHang() {}


	public KhachHang(int maKH, String tenKH, String diaChi, String sDT) {
		MaKH = maKH;
		TenKH = tenKH;
		DiaChi = diaChi;
		SDT = sDT;
	}


	public int getMaKH() {
		return MaKH;
	}


	public void setMaKH(int maKH) {
		MaKH = maKH;
	}


	public String getTenKH() {
		return TenKH;
	}


	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}


	public String getDiaChi() {
		return DiaChi;
	}


	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}


	public String getSDT() {
		return SDT;
	}


	public void setSDT(String sDT) {
		SDT = sDT;
	}


	@Override
	public String toString() {
		return TenKH;
	}
	
	public static List<KhachHang> getALL() throws ClassNotFoundException, SQLException{
		List<KhachHang> list = new ArrayList<KhachHang>();
		try  
		{
			Connection conn = DBConnection.getMySQLConnection();
			Statement statement = conn.createStatement();
			
			String query = "Select * from KhachHang where TrangThai = 1";
			
			ResultSet rs = statement.executeQuery(query);
			 
			while(rs.next())
			{ 
	           list.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public static KhachHang getbyID(int id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getMySQLConnection();
		
		try  
		{	
			String query = "Select *from KhachHang where MaKH = ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				return new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<KhachHang> getbyPhone(String phone) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getMySQLConnection();
		List<KhachHang> list = new ArrayList<KhachHang>();
		try  
		{	
			String query = "Select *from KhachHang where SDT like N'%"+phone+"%' and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
	           list.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<KhachHang> getbyName(String Name) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getMySQLConnection();
		List<KhachHang> list = new ArrayList<KhachHang>();
		try  
		{	
			String query = "Select *from KhachHang where TenKH like N'%"+Name+"%' and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
	           list.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int Insert(KhachHang kh) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = null;
			String query = "Insert into KhachHang values ( ? , ? , ? , ?, 1);";
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, kh.getMaKH());
			ps.setString(2, kh.getTenKH());
			
			if(kh.getDiaChi().isEmpty() == true) 
				ps.setNull(3, java.sql.Types.NVARCHAR);
			else
				ps.setString(3, kh.getDiaChi());
			
			if(kh.getSDT().isEmpty() == true)
				ps.setNull(4, java.sql.Types.NVARCHAR);
			else
				ps.setString(4, kh.getSDT());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int deletekhachhang(int MaKH) {
		String sql = "update khachhang set TrangThai = 0 where MaKH=?";
		try {
			Connection conn =DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaKH);
			ps.execute();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
		return 1;
	}
	
	public static int updatekhachhang(KhachHang kh) {
		String sql = "update khachhang set TenKH=?, DiaChi=?,SDT=? where MaKH=?";
		try {
			Connection conn  =DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			
			ps.setString(1, kh.getTenKH());
			if(kh.getDiaChi().isEmpty() == true) 
				ps.setNull(2, java.sql.Types.NVARCHAR);
			else
				ps.setString(2, kh.getDiaChi());
			
			if(kh.getSDT().isEmpty() == true)
				ps.setNull(3, java.sql.Types.NVARCHAR);
			else
				ps.setString(3, kh.getSDT());
			
			ps.setInt(4, kh.getMaKH());
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	
}
