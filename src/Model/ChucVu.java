package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;

public class ChucVu {
	private int MaCV;
	private String TenCV;

	public ChucVu() {
	}

	public ChucVu(int maCV, String tenCV) {
		MaCV = maCV;
		TenCV = tenCV;
	}

	public int getMaCV() {
		return MaCV;
	}

	public void setMaCV(int maCV) {
		MaCV = maCV;
	}

	public String getTenCV() {
		return TenCV;
	}

	public void setTenCV(String tenCV) {
		TenCV = tenCV;
	}

	@Override
	public String toString() {
		return TenCV;
	}

	public static List<ChucVu> getALL() throws ClassNotFoundException, SQLException {
		List<ChucVu> list = new ArrayList<ChucVu>();
		try {
			Connection conn = DBConnection.getMySQLConnection();
			Statement statement = conn.createStatement();

			String query = "Select * from ChucVu where TrangThai = 1";

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {

				list.add(new ChucVu(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static ChucVu getbyID(int id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getMySQLConnection();

		try {
			String query = "Select *from ChucVu where MaCV = ? and TrangThai = 1";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new ChucVu(rs.getInt(1), rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int addchucvu(ChucVu cv) {
		String sql = "insert into ChucVu values (?,?,1)";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cv.getMaCV());
			ps.setString(2, cv.getTenCV());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	public static int updatechucvu(ChucVu cv) {
		String sql = "update ChucVu set TenCV=? where MaCV=?";

		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, cv.getTenCV());
			ps.setInt(2, cv.getMaCV());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	public static int deletechucvu(int MaCV) {
		String sql = "update ChucVu set TrangThai = 0 where MaCV=?";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaCV);
			ps.execute();

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	public static List<ChucVu> findByName(String TenCV) {
		String sql = "select * from ChucVu where (TenCV like '%' ? '%') and TrangThai = 1 ";
		List<ChucVu> listcv = new ArrayList<ChucVu>();

		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, TenCV);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listcv.add(new ChucVu(rs.getInt(1), rs.getString(2)));

			}

		} catch (Exception e) {
			// TODO: handle exception

		}
		return listcv;
	}
	
	

}
