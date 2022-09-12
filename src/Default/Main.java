package Default;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.FormAdmin;
import GUI.FormDangNhap;
import GUI.FormNhanVien;
import GUI.FormReportDate;
import GUI.ListChiTietCuaHD;
import GUI.ListCustomer;
import GUI.ListService;
import Model.NhanVien;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//new FormAdmin(NhanVien.getbyID(1));
		new FormDangNhap();
		//new FormNhanVien(NhanVien.getbyID(1));
		//new ListChiTietCuaHD();
		//new ListCustomer();
		//new ListService();
		//new FormReport();	
	}
}
