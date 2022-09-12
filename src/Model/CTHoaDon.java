package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;

public class CTHoaDon {
	private HoaDon hoadon;
	private DichVu dichvu;
	private NhanVien NVPhuTrach;

	public CTHoaDon() {
	}

	public CTHoaDon(HoaDon hoadon, DichVu dichvu, NhanVien nVPhuTrach) {
		this.hoadon = hoadon;
		this.dichvu = dichvu;
		NVPhuTrach = nVPhuTrach;
	}

	public HoaDon getHoadon() {
		return hoadon;
	}

	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}

	public DichVu getDichvu() {
		return dichvu;
	}

	public void setDichvu(DichVu dichvu) {
		this.dichvu = dichvu;
	}

	public NhanVien getNVPhuTrach() {
		return NVPhuTrach;
	}

	public void setNVPhuTrach(NhanVien nVPhuTrach) {
		NVPhuTrach = nVPhuTrach;
	}

	@Override
	public String toString() {
		return "CTHoaDon [hoadon=" + hoadon + ", dichvu=" + dichvu + ", NVPhuTrach=" + NVPhuTrach + "]";
	}

	public static List<CTHoaDon> getALL() throws ClassNotFoundException, SQLException {
		List<CTHoaDon> list = new ArrayList<CTHoaDon>();
		try {
			Connection conn = DBConnection.getMySQLConnection();
			Statement statement = conn.createStatement();

			String query = "Select * from CTHoaDon where TrangThai = 1 order by HoaDon asc ";

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				list.add(new CTHoaDon(HoaDon.getbyID(rs.getInt(1)), DichVu.getbyID(rs.getInt(2)),
						NhanVien.getbyID(rs.getInt(3))));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<CTHoaDon> getALLbyID(int id) throws ClassNotFoundException, SQLException {
		List<CTHoaDon> list = new ArrayList<CTHoaDon>();
		try {
			Connection conn = DBConnection.getMySQLConnection();

			String query = "Select * from CTHoaDon where HoaDon = ? and TrangThai = 1";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new CTHoaDon(HoaDon.getbyID(rs.getInt(1)), DichVu.getbyID(rs.getInt(2)),
						NhanVien.getbyID(rs.getInt(3))));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static int Insert(CTHoaDon cthd) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = null;
			String query = "Insert into CTHoaDon values ( ? , ? , ? ,1);";

			ps = conn.prepareStatement(query);

			ps.setInt(1, cthd.getHoadon().getMaHD());
			ps.setInt(2, cthd.getDichvu().getMaDV());
			ps.setInt(3, cthd.getNVPhuTrach().getMaNV());

			rs = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static int updatecthd(CTHoaDon cthd) {
		String sql = "update CTHoaDon set NVPhuTrach=? where HoaDon=? and DichVu = ?";

		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cthd.getNVPhuTrach().getMaNV());
			ps.setInt(2, cthd.getHoadon().getMaHD());
			ps.setInt(3, cthd.getDichvu().getMaDV());
			
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	public static int deletecthd(int MaHD, int MaDV) {
		String sql = "Update CTHoaDon set TrangThai = 0 where HoaDon=? and DichVu = ?";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaHD);
			ps.setInt(2, MaDV);
			ps.execute();

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public static int deletecthdbyhd(int MaHD) {
		String sql = "Update CTHoaDon set TrangThai = 0 where HoaDon=?";
		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaHD);
			ps.execute();

		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	public static List<CTHoaDon> findBYHD(int MaHD) {
		String sql = "select * from CTHoaDon where HoaDon = ? and TrangThai = 1";
		List<CTHoaDon> list = new ArrayList<CTHoaDon>();

		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaHD);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new CTHoaDon(HoaDon.getbyID(rs.getInt(1)), DichVu.getbyID(rs.getInt(2)),
						NhanVien.getbyID(rs.getInt(3))));
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
		return list;
	}

	public static List<CTHoaDon> findBYDV(int MaDV) {
		String sql = "select * from CTHoaDon where DichVu = ? and TrangThai = 1 ";
		List<CTHoaDon> list = new ArrayList<CTHoaDon>();

		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, MaDV);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new CTHoaDon(HoaDon.getbyID(rs.getInt(1)), DichVu.getbyID(rs.getInt(2)),
						NhanVien.getbyID(rs.getInt(3))));
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
		return list;
	}

	public static List<CTHoaDon> findBYNV(int NVLapHD) {
		
		String sql = "select * from CTHoaDon where NVPhuTrach = ? and TrangThai = 1";
		List<CTHoaDon> list = new ArrayList<CTHoaDon>();

		try {
			Connection conn = DBConnection.getMySQLConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NVLapHD);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new CTHoaDon(HoaDon.getbyID(rs.getInt(1)), DichVu.getbyID(rs.getInt(2)),
						NhanVien.getbyID(rs.getInt(3))));
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
		return list;
	}
}
