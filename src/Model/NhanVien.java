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

public class NhanVien {
	
	private int MaNV;
	private String TenNV;
	private Date NgaySinh;
	private String GioiTinh;
	private ChucVu chucvu;
	private String UserName;
	private String Password;
	
	
	public NhanVien() {}


	public NhanVien(int maNV, String tenNV, Date ngaySinh, String gioiTinh, ChucVu chucvu, String userName,
			String password) {
		MaNV = maNV;
		TenNV = tenNV;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		this.chucvu = chucvu;
		UserName = userName;
		Password = password;
	}


	public int getMaNV() {
		return MaNV;
	}


	public void setMaNV(int maNV) {
		MaNV = maNV;
	}


	public String getTenNV() {
		return TenNV;
	}


	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}


	public Date getNgaySinh() {
		return NgaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}


	public String getGioiTinh() {
		return GioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}


	public ChucVu getChucvu() {
		return chucvu;
	}


	public void setChucvu(ChucVu chucvu) {
		this.chucvu = chucvu;
	}


	public String getUserName() {
		return UserName;
	}


	public void setUserName(String userName) {
		UserName = userName;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	@Override
	public String toString() {
		return  TenNV ;
	}

	
	
	public static List<NhanVien> getALL() throws ClassNotFoundException, SQLException{
		List<NhanVien> list = new ArrayList<NhanVien>();
		try  
		{
			Connection conn = DBConnection.getMySQLConnection();
			Statement statement = conn.createStatement();
			
			String query = "Select * from NhanVien where TrangThai = 1";
			
			ResultSet rs = statement.executeQuery(query);
			 
			while(rs.next())
			{ 
			
	           list.add(new NhanVien(	rs.getInt(1),
	        		   					rs.getString(2),
	        		   					rs.getDate(3),
	        		   					rs.getString(4),
	        		   					ChucVu.getbyID(rs.getInt(5)),
	        		   					rs.getString(6),
	        		   					rs.getString(7)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public static NhanVien getbyID(int id) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getMySQLConnection();
		
		try  
		{	
			String query = "Select *from NhanVien where MaNV = ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				return new NhanVien(	rs.getInt(1),
					   					rs.getString(2),
					   					rs.getDate(3),
					   					rs.getString(4),
					   					ChucVu.getbyID(rs.getInt(5)),
					   					rs.getString(6),
					   					rs.getString(7));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static NhanVien getbyUsername(String username, String password) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getMySQLConnection();
		
		try  
		{	
			String query = "Select *from NhanVien where UserName = ? and PassWord = ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				return new NhanVien(	rs.getInt(1),
					   					rs.getString(2),
					   					rs.getDate(3),
					   					rs.getString(4),
					   					ChucVu.getbyID(rs.getInt(5)),
					   					rs.getString(6),
					   					rs.getString(7));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<NhanVien> getbyName(String name) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getMySQLConnection();
		List<NhanVien> list = new ArrayList<NhanVien>();
		try  
		{	
			String query = "Select *from NhanVien where TenNV like '%' ? '%' and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				list.add( new NhanVien(	rs.getInt(1),
					   					rs.getString(2),
					   					rs.getDate(3),
					   					rs.getString(4),
					   					ChucVu.getbyID(rs.getInt(5)),
					   					rs.getString(6),
					   					rs.getString(7)));
			}
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int addnhanvien(NhanVien nv) {
		String sql = "insert into nhanvien values (?,?,?,?,?,?,?,1)";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, nv.getMaNV());
			ps.setString(2, nv.getTenNV());
			if(nv.getNgaySinh() != null)
				ps.setDate(3, nv.getNgaySinh());
			else
				ps.setNull(3, java.sql.Types.DATE);
			
			if(nv.getGioiTinh().isEmpty() == false)
				ps.setString(4, nv.getGioiTinh());
			else
				ps.setNull(4, java.sql.Types.NVARCHAR);
			
			ps.setInt(5, nv.getChucvu().getMaCV());
			
			if(nv.getUserName().isEmpty() == false)
				ps.setString(6, nv.getUserName());
			else
				ps.setNull(6, java.sql.Types.NVARCHAR);
			
			if(nv.getPassword().isEmpty() == false)
				ps.setString(7, nv.getPassword());
			else
				ps.setNull(7, java.sql.Types.NVARCHAR);
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static int updatenhanhvien(NhanVien nv ) {
		String sql="update nhanvien set TenNV=?, NgaySinh=?, GioiTinh=?, ChucVu=?, UserName=?, PassWord=? where MaNV=?";
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			
			
			ps.setString(1, nv.getTenNV());
			if(nv.getNgaySinh() != null)
				ps.setDate(2, nv.getNgaySinh());
			else
				ps.setNull(2, java.sql.Types.DATE);
			
			if(nv.getGioiTinh().isEmpty() == false)
				ps.setString(3, nv.getGioiTinh());
			else
				ps.setNull(3, java.sql.Types.NVARCHAR);
			ps.setInt(4, nv.getChucvu().getMaCV());
			
			if(nv.getUserName().isEmpty() == false)
				ps.setString(5, nv.getUserName());
			else
				ps.setNull(5, java.sql.Types.NVARCHAR);
			
			if(nv.getPassword().isEmpty() == false)
				ps.setString(6, nv.getPassword());
			else
				ps.setNull(6, java.sql.Types.NVARCHAR);
			
			ps.setInt(7, nv.getMaNV());
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static int deletenhanvien(int MaNV) {
		String sql = "Update nhanvien set TrangThai = 0 where MaNV=?";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, MaNV);
			ps.execute();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static List<NhanVien> findbysex(String GioiTinh) {
		String sql = "select * from nhanvien where (GioiTinh like '%' ? '%') and TrangThai = 1";
		List<NhanVien> listnhanvien = new ArrayList<NhanVien>();
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, GioiTinh);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listnhanvien.add(new NhanVien(rs.getInt(1),
						rs.getString(2), 
						rs.getDate(3),
						rs.getString(4),
						ChucVu.getbyID(rs.getInt(5)),
						rs.getString(6),
						rs.getString(7)));
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return listnhanvien;
		}
	public static List<NhanVien> findbyposition(int cv) {
		String sql = "select * from nhanvien where ChucVu = ?  and TrangThai = 1";
		List<NhanVien> listnhanvien = new ArrayList<NhanVien>();
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, cv);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listnhanvien.add(new NhanVien(rs.getInt(1),
						rs.getString(2), 
						rs.getDate(3),
						rs.getString(4),
						ChucVu.getbyID(rs.getInt(5)),
						rs.getString(6),
						rs.getString(7)));
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return listnhanvien;
	}
}
