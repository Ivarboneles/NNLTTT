package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;

public class DichVu {
	private int MaDV;
	private String TenDV;
	private int GiaDV;
	
	public DichVu() {}

	public DichVu(int maDV, String tenDV, int giaDV) {
		MaDV = maDV;
		TenDV = tenDV;
		GiaDV = giaDV;
	}

	public int getMaDV() {
		return MaDV;
	}

	public void setMaDV(int maDV) {
		MaDV = maDV;
	}

	public String getTenDV() {
		return TenDV;
	}

	public void setTenDV(String tenDV) {
		TenDV = tenDV;
	}

	public int getGiaDV() {
		return GiaDV;
	}

	public void setGiaDV(int giaDV) {
		GiaDV = giaDV;
	}

	@Override
	public String toString() {
		return  TenDV ;
	}
	
	
	public static List<DichVu> getALL() throws ClassNotFoundException, SQLException{
		List<DichVu> list = new ArrayList<DichVu>();
		try  
		{
			Connection conn = DBConnection.getMySQLConnection();
			Statement statement = conn.createStatement();
			
			String query = "Select * from DichVu where TrangThai = 1";
			
			ResultSet rs = statement.executeQuery(query);
			 
			while(rs.next())
			{ 
	           list.add(new DichVu(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public static DichVu getbyID(int id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getMySQLConnection();
		try  
		{	
			String query = "Select *from DichVu where MaDV = ? and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				return new DichVu(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<DichVu> getbyName(String name) throws ClassNotFoundException, SQLException {
		
		List<DichVu> list = new ArrayList<DichVu>();
		try  
		{	
			Connection conn = DBConnection.getMySQLConnection();
			String query = "Select *from DichVu where TenDV like N'%"+name+"%' and TrangThai = 1";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{ 
				list.add(new DichVu(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int adddichvu(DichVu dv) {
		String sql ="insert into dichvu values(?,?,?,1)";
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, dv.getMaDV());
			ps.setString(2, dv.getTenDV());
			ps.setInt(3, dv.getGiaDV());
			ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static int deletedichvu(int MaDV) {
		String sql ="update dichvu set TrangThai = 0 where MaDV=?";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaDV);
			ps.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static int updatedichvu(DichVu dv) {
		String sql = "update dichvu set TenDV=?, GiaDV=? where MaDV=?";
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dv.getTenDV());
			ps.setInt(2, dv.getGiaDV());
			ps.setInt(3, dv.getMaDV());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static List<DichVu> findbyprice(String GiaDV) {
		String sql ="select * from dichvu where (GiaDV like '%' ? '%') and TrangThai = 1";
		List<DichVu> listdichvu = new ArrayList<DichVu>();
		
		
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, GiaDV);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listdichvu.add(new DichVu(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listdichvu;
		
	}
	
	
}
